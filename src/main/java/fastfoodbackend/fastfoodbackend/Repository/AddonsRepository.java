package fastfoodbackend.fastfoodbackend.Repository;

import fastfoodbackend.fastfoodbackend.Models.Addons;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Transactional
@Repository
public interface AddonsRepository extends JpaRepository<Addons, String>, JpaSpecificationExecutor<Addons> {
}
