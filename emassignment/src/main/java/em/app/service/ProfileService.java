package em.app.service;

import em.app.model.Profile;

public interface ProfileService {
	void saveProfile(Profile profile);
	Profile getProfileById(long id);
}
