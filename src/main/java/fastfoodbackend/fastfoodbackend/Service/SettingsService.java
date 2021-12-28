package fastfoodbackend.fastfoodbackend.Service;

import fastfoodbackend.fastfoodbackend.Models.Settings;
import fastfoodbackend.fastfoodbackend.Repository.SettingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Service
public class SettingsService {

    @Autowired
    private SettingsRepository settingsRepository;

    public static Specification<Settings> getSettingsByCompanyId(Integer companyId) {
        return (Specification<Settings>) (root, criteriaQuery, cb) -> {
            final List<Predicate> predicates = new ArrayList<>();

            if (companyId > 0 || companyId != null) {
                predicates.add(cb.equal(root.get("CompanyId"), companyId));
            }

            return cb.and(predicates.toArray(new Predicate[predicates.size()]));
        };
    }

    public List<Settings> getInvItemBySpec (Specification<Settings> spec){
        return settingsRepository.findAll(spec);
    }

    public Settings saveSettings(Settings settings){return settingsRepository.saveAndFlush(settings);}





}


