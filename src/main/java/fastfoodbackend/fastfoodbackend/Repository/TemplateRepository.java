package fastfoodbackend.fastfoodbackend.Repository;
import fastfoodbackend.fastfoodbackend.Models.Template;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Transactional
@Repository
public interface TemplateRepository extends JpaRepository<Template, String>, JpaSpecificationExecutor<Template> {
}
