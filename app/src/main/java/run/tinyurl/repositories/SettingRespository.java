package run.tinyurl.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import run.tinyurl.domains.Settings;

import java.util.Optional;

@Repository
public interface SettingRespository extends JpaRepository<Settings, Long> {
    Optional<Settings> getByName(String name);
}
