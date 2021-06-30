package em.app.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import em.app.model.Profile;
import em.app.model.Skills;
import em.app.repository.ProfileRepo;
import em.app.repository.SkillsRepo;
import em.app.util.FileUploadUtil;

@Service
public class ProfileServiceImpl implements ProfileService {

	@Autowired
	private ProfileRepo profileRepo;
	
	@Autowired
    private SkillsRepo skillsRepo;
	
	@Autowired
	private ProfileService profileService;
	
	@Override
	public void saveProfile(Profile profile,MultipartFile multipartFile) {
		
		String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        profile.setPhotos(fileName);
        String uploadDir = "user-photos/" + profile.getId(); 
        try {
			FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
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
	
	@Override
	public String showNewProfileForm(Model model) {
		// create model attribute to bind form data
		Profile profile = new Profile();
		model.addAttribute("profile", profile);
		
		List<Skills> skills = (List<Skills>) skillsRepo.findAll();
        model.addAttribute("skills",skills);
       
		return "new_profile";
	}
	@Override
	public String showFormForUpdate(@PathVariable ( value = "id") long id, Model model) {

		Profile profile = profileService.getProfileById(id);
		
		model.addAttribute("profile", profile);
		
		List<Skills> skills = (List<Skills>) skillsRepo.findAll();
        model.addAttribute("skills",skills);
		return "new_profile";
	}

}
