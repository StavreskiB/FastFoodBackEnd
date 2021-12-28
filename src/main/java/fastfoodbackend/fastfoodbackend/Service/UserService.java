package fastfoodbackend.fastfoodbackend.Service;

import fastfoodbackend.fastfoodbackend.Models.User;
import fastfoodbackend.fastfoodbackend.Repository.UserRepository;
import fastfoodbackend.fastfoodbackend.Repository.UserTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserTypeRepository userTypeRepository;

    public static Specification<User> getUserByEmailSpec(String Email) {
        return (Specification<User>) (root, criteriaQuery, cb) -> {
            final List<Predicate> predicates = new ArrayList<>();

            if (Email != "" || Email != null) {
                predicates.add(cb.equal(root.get("Email"), Email));
            }

            return cb.and(predicates.toArray(new Predicate[predicates.size()]));
        };
    }


    public static Specification<User> getUserByCompanyId(Integer companyId) {
        return (Specification<User>) (root, criteriaQuery, cb) -> {
            final List<Predicate> predicates = new ArrayList<>();

            if (companyId != null || companyId > 0) {
                predicates.add(cb.equal(root.get("CompanyId"), companyId));
            }
            return cb.and(predicates.toArray(new Predicate[predicates.size()]));
        };
    }

    public static Specification<User> getUserById(Integer userId) {
        return (Specification<User>) (root, criteriaQuery, cb) -> {
            final List<Predicate> predicates = new ArrayList<>();

            if (userId != null || userId > 0) {
                predicates.add(cb.equal(root.get("IdUser"), userId));
            }
            return cb.and(predicates.toArray(new Predicate[predicates.size()]));
        };
    }

    public List<User> getUserBySpec(Specification<User> spec){
        return userRepository.findAll(spec);
    }

    public List<User> getAllUser(){
        return userRepository.findAll();
    }

    public User saveNewUser(User user){
        return userRepository.saveAndFlush(user);
    }

    public User update(User user){
        return userRepository.save(user);
    }

}
