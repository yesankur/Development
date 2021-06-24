package em.app.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import em.app.model.Profile;
import em.app.repository.ProfileRepo;

@Service
public class ProfileServiceImpl implements ProfileService {

	@Autowired
	private ProfileRepo profileRepo;
	@Override
	public void saveProfile(Profile profile) {
		
		this.profileRepo.save(profile);
		
	}
	
	@Override
	public Profile getProfileById(long id) {
		Optional<Profile> optional = profileRepo.findById(id);
		Profile profile = null;
		if (optional.isPresent()) {
			profile = optional.get();
		} else {
			throw new RuntimeException(" Profile not found for id :: " + id);
		}
		return profile;
	}

}
