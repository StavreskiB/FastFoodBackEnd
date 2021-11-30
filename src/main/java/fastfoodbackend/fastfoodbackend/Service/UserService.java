package fastfoodbackend.fastfoodbackend.Service;

import fastfoodbackend.fastfoodbackend.Models.User;
import fastfoodbackend.fastfoodbackend.Models.UserType;
import fastfoodbackend.fastfoodbackend.Repository.UserRepository;
import fastfoodbackend.fastfoodbackend.Repository.UserTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.Access;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserTypeRepository userTypeRepository;


    public User loadUserByUsername(String email){
        User user = new User();


        user.setEmail("Stavreskibojan@gmail.com");
        user.setName("Bojan");
        user.setSurname("Stavreski");
        user.setPassword("admin");
        return user;
    }

    public static Specification<User> getUserByEmailSpec(String Email) {
        return (Specification<User>) (root, criteriaQuery, cb) -> {
            final List<Predicate> predicates = new ArrayList<>();

            if (Email != "" || Email != null) {
                predicates.add(cb.equal(root.get("Email"), Email));
            }

            return cb.and(predicates.toArray(new Predicate[predicates.size()]));
        };
    }

    public List<User> getUserBySpec(Specification<User> spec){
        return userRepository.findAll(spec);
    }

    public List<User> getAll(){
        return userRepository.findAll();
    }

    public List<UserType> getA(){
        return userTypeRepository.findAll();
    }

}
