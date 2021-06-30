package em.app.service;

import java.io.IOException;

import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import em.app.model.Profile;

public interface ProfileService {
	void saveProfile(Profile profile,MultipartFile multipartFile) throws IOException;
	Profile getProfileById(long id);
	String showNewProfileForm(Model model);
	String showFormForUpdate(long id, Model model);
}
