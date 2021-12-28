package fastfoodbackend.fastfoodbackend;

import fastfoodbackend.fastfoodbackend.Class.LogError;
import fastfoodbackend.fastfoodbackend.Models.User;
import fastfoodbackend.fastfoodbackend.Service.ErrorService;
import fastfoodbackend.fastfoodbackend.Service.TokenService;
import fastfoodbackend.fastfoodbackend.Models.TokenRes;
import fastfoodbackend.fastfoodbackend.Models.TokenReq;
import fastfoodbackend.fastfoodbackend.Service.CryptoService;
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
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/jwt")
class SecurityController {

    @Autowired
    private TokenService jwtTokenUtil;

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CryptoService cryptoService;

    @Autowired
    private ErrorService logErrorService;

    @RequestMapping(value = "/testApiBoll", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public Boolean testapiBool(HttpServletRequest httpServletRequest) throws Exception {

        return true;
    }

    @RequestMapping(value = "/testApiBollPost", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public Boolean testApiBollPost(HttpServletRequest httpServletRequest) throws Exception {

        return true;
    }

    @RequestMapping(value = "/testApi", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
        public List<User> testapi(HttpServletRequest httpServletRequest) throws Exception {

            List<User> userList = null;

            userList = userService.getAllUser();

            return userList;
    }

    @RequestMapping(value = "/testApiPOST", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public List<User> testApiPOST(HttpServletRequest httpServletRequest) throws Exception {
        List<User> userList = null;

        userList = userService.getAllUser();
        return userList;
    }

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> CreateAuthenticateToken(@RequestBody TokenReq tokenReq,
                                                     HttpServletRequest httpServletRequest) throws Exception {
        String jwt = "";
        String userEmail  = "";
        String userType = "";
        Integer companyId = null;
        List<User> users;
        try {

            Specification<User> specification;

            specification = userService.getUserByEmailSpec(tokenReq.getEmail());
            users = userService.getUserBySpec(specification);
            if (users.get(0).getEmail() != null) {

                String decoded = cryptoService.decryptText(tokenReq.getPasword());

                System.out.println("decoded: " + decoded);
                if (passwordEncoder.matches(decoded, users.get(0).getPassword())) {

                    List<GrantedAuthority> authorities = new ArrayList<>(); // this should have more roles
                    GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("ROLE_USER");
                    authorities.add(grantedAuthority);
                    UserDetails userDetails = new org.springframework.security.core.userdetails.User(users.get(0).getEmail(), users.get(0).getPassword(), authorities);
                    jwt = jwtTokenUtil.generateToken(userDetails);
                    System.out.println("jwt: " + jwt);
                    userEmail = users.get(0).getEmail();
                    userType = users.get(0).getIdUserType().getName();
                    companyId = users.get(0).getCompanyId();
                } else {
                    jwt = "";

                }
            }

        } catch (BadCredentialsException e) {
            jwt = "";
        }
        return ResponseEntity.ok(new TokenRes(jwt, userEmail, userType, companyId));
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

    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(value = "/saveNewEmployee", produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
        public Boolean saveNewEmployee(@RequestBody User user, HttpServletRequest request){
        Boolean flag = false;
        List<User> userList;
        Specification<User> spec;
        String decode;
        String encoded;
        try{

            spec = userService.getUserByEmailSpec(user.getEmail());
            userList = userService.getUserBySpec(spec);

                if(userList.size() > 0 && userList.get(0).getIdUserType().getIdUserType() == 2){
                    flag = false;
                } else {
                    decode = cryptoService.decryptText(user.getPassword());
                    encoded = passwordEncoder.encode(decode);
                    user.setPassword(encoded);
                    userService.saveNewUser(user);
                    flag = true;
                }

            } catch (Exception e){
                e.printStackTrace();
            }

        return flag;
    }

    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(value = "/getAllEmployee", produces = "application/json;charset=UTF-8", method = RequestMethod.GET)
    public List<User> getAllEmployee(@RequestHeader (value="companyId") Integer companyId, HttpServletRequest request){
        Boolean flag = false;
        List<User> userList = null;
        Specification<User> spec;
        try{
           spec = userService.getUserByCompanyId(companyId);
           userList = userService.getUserBySpec(spec);
        } catch (Exception e){
            e.printStackTrace();
        }

        return userList.stream().filter(o -> o.getIdUserType().getIdUserType() == 2).collect(Collectors.toList());
    }

    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(value = "/getManager", produces = "application/json;charset=UTF-8", method = RequestMethod.GET)
    public List<User> getManager(@RequestHeader (value="companyId") Integer companyId, HttpServletRequest request){
        List<User> userList = null;
        Specification<User> spec;
        try{
            spec = userService.getUserByCompanyId(companyId);
            userList = userService.getUserBySpec(spec);
        } catch (Exception e){
            e.printStackTrace();
        }

        return userList.stream().filter(o -> o.getIdUserType().getIdUserType() == 1).collect(Collectors.toList());
    }

    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(value = "/getEmpById", produces = "application/json;charset=UTF-8", method = RequestMethod.GET)
    public List<User> getEmpById(@RequestHeader (value="companyId") Integer companyId,
                                 @RequestHeader (value="employeeId") Integer employeeId,
                                 HttpServletRequest request){
        Boolean flag = false;
        List<User> userList = null;
        Specification<User> spec;
        try{
            spec = userService.getUserByCompanyId(companyId);
            userList = userService.getUserBySpec(spec);
        } catch (Exception e){
            e.printStackTrace();
        }

        return userList.stream().filter(o -> o.getIdUser() == employeeId).collect(Collectors.toList());
    }

    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(value = "/updateEmployee", produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
    public Boolean updateEmployee(@RequestBody User user, @RequestHeader (value="updateId") String updateId,  HttpServletRequest request){
        Boolean flag = false;
        List<User> userList;
        Specification <User> specification;
        String decode ;
        String encoded ;
        try {

          specification = userService.getUserById(Integer.parseInt(updateId));
          userList = userService.getUserBySpec(specification);

          if(user.getPassword() != "" && user.getPassword() != null){
              decode = cryptoService.decryptText(user.getPassword());
              encoded = passwordEncoder.encode(decode);
              userList.get(0).setPassword(encoded);
          }

          if(user.getName() != "" && user.getName() != null ){
              userList.get(0).setName(user.getName());
          }

          if(user.getSurname() != "" && user.getSurname() != null ){
              userList.get(0).setSurname(user.getSurname());
          }

          if(user.getEmail() != "" && user.getEmail() != null ) {
              userList.get(0).setEmail(user.getEmail());
          }

          userService.update(userList.get(0));

          flag = true;

        } catch (Exception e){
            e.printStackTrace();
        }

      return flag;
    }



}

