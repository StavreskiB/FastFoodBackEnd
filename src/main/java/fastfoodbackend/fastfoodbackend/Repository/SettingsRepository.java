package fastfoodbackend.fastfoodbackend.Repository;

import fastfoodbackend.fastfoodbackend.Models.Settings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Transactional
@Repository
public interface SettingsRepository extends JpaRepository<Settings, String>, JpaSpecificationExecutor<Settings> {
}
