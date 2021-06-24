package em.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import em.app.model.Profile;

@Repository
public interface ProfileRepo extends JpaRepository<Profile, Long>{

}
