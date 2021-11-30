package fastfoodbackend.fastfoodbackend.Security;

import fastfoodbackend.fastfoodbackend.Models.User;
import fastfoodbackend.fastfoodbackend.Security.Config.JwtUtil;
import fastfoodbackend.fastfoodbackend.Security.Models.TokenRes;
import fastfoodbackend.fastfoodbackend.Security.Models.TokenReq;
import fastfoodbackend.fastfoodbackend.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/jwt")
class jwtController {

    @Autowired
    private JwtUtil jwtTokenUtil;

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Autowired
    private CryptoJS cryptoJS;
    // api for users authentication
    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> CreateAuthenticateToken(@RequestBody TokenReq tokenReq,
                                                     HttpServletRequest httpServletRequest) throws Exception {
        String jwt = "";
        String userEmail  = "";
        List<User> users;
        try {

            Specification<User> specification;
            List<User> userList = new ArrayList<>();

            specification = userService.getUserByEmailSpec(tokenReq.getEmail());
            users = userService.getUserBySpec(specification);
            if (users.get(0).getEmail() != null) {
                // TODO:// ovde treba da se dekriptira password
                //System.out.println("password: " + authenticationRequest.getPasword());

                String decoded = cryptoJS.decryptText(tokenReq.getPasword());

                System.out.println("decoded: " + decoded);
//                if (passwordEncoder.matches(decoded, users.get(0).getPassword())) {
                if (true) {
                    List<GrantedAuthority> authorities = new ArrayList<>(); // this should have more roles
                    GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("ROLE_USER");
                    authorities.add(grantedAuthority);
                    UserDetails userDetails = new org.springframework.security.core.userdetails.User(users.get(0).getEmail(), users.get(0).getPassword(), authorities);
                    jwt = jwtTokenUtil.generateToken(userDetails);
                    System.out.println("jwt: " + jwt);
                    userEmail = users.get(0).getEmail();
                } else {
                    //throw new BadCredentialsException("Incorrect username or password");
                    jwt = "";
                }
            }
        } catch (BadCredentialsException e) {
            jwt = "";
//            logErrorService.LogErrorSave(new LogError(httpServletRequest.getHeader("Application"),"","","","","","",""));
        }
        return ResponseEntity.ok(new TokenRes(jwt, userEmail ));
    }

    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(value = "/isTokenExpired",
            produces = "application/json;charset=UTF-8", method = RequestMethod.GET) public Boolean isTokenExpired(HttpServletRequest request) {

        final String authorizationHeader = request.getHeader("Authorization");
        String jwt = null;
        boolean flag = true;

        if (request.getHeader("Authorization") != null && request.getHeader("Authorization").startsWith("Bearer ")) {
            jwt = authorizationHeader.substring(7);
            try {
                //koga tokenot e validen flag e false
                flag = jwtTokenUtil.isTokenExpired(jwt);
            }catch (Exception e){ }
        }

        return flag;
    }
}

