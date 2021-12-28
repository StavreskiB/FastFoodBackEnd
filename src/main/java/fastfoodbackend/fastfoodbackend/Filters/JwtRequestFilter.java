package fastfoodbackend.fastfoodbackend.Filters;

import fastfoodbackend.fastfoodbackend.Service.TokenService;
import fastfoodbackend.fastfoodbackend.Service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {
    @Autowired
    private MyUserDetailsService userDetailsService;

    @Autowired
    private TokenService tokenService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        try {
            final String authorizationHeader = request.getHeader("Authorization");

            String username = null;
            String jwt = null;

            if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
                jwt = authorizationHeader.substring(7);
                try {
                    boolean flag = tokenService.isTokenExpired(jwt);
                    System.out.println("request: " + request);
                    System.out.println("flagError: " + flag);

                    if (flag) {
                        System.out.println("flag = true : " + flag);
                        username = tokenService.extractUsername(jwt);
                    }
                }catch (Exception exception){ System.out.println("exception error: "); }
            }

            System.out.println("okkk: " + request);
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                System.out.println("generate token: ");

                UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
                if (tokenService.validateToken(jwt, userDetails)) {
                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities());
                    usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                }
            }
            chain.doFilter(request, response);
        } catch (ServletException e){
            System.out.println(e.getMessage());
        }
    }
}
