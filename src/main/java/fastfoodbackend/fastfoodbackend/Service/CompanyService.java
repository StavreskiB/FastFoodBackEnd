package fastfoodbackend.fastfoodbackend.Service;
import fastfoodbackend.fastfoodbackend.Models.Company;
import fastfoodbackend.fastfoodbackend.Repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;


    public static Specification<Company> getCompanyById(String companyId) {
        return (Specification<Company>) (root, criteriaQuery, cb) -> {
            final List<Predicate> predicates = new ArrayList<>();

            if (companyId != "" || companyId != null) {
                predicates.add(cb.equal(root.get("IdCompany"), Integer.parseInt(companyId)));
            }
            return cb.and(predicates.toArray(new Predicate[predicates.size()]));
        };
    }

    public List<Company> getCompanyBySpec(Specification<Company> spec){
        return companyRepository.findAll(spec);
    }
}

