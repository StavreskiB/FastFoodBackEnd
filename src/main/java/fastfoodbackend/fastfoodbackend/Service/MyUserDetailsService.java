package fastfoodbackend.fastfoodbackend.Service;

import fastfoodbackend.fastfoodbackend.Models.User;
import fastfoodbackend.fastfoodbackend.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Specification<User> specification;
        specification = userService.getUserByEmailSpec(email);
           List<User> users = new ArrayList<>();
           users = userService.getUserBySpec(specification);

        if(users.get(0).getEmail() != null) {
            return new org.springframework.security.core.userdetails.User(users.get(0).getEmail(), users.get(0).getPassword(), new ArrayList<>());
        } else {
            throw new UsernameNotFoundException("User Name is not Found");
       }
    }
}
