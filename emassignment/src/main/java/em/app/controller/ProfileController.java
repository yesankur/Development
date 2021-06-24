package em.app.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import em.app.model.FileUploadUtil;
import em.app.model.Profile;
import em.app.model.Skills;
import em.app.repository.SkillsRepo;
import em.app.service.ProfileService;

@Controller
public class ProfileController {
	@Autowired
	private ProfileService profileService;
	
	@Autowired
    private SkillsRepo skillsRepo;
	
	@GetMapping("/")
	public String showNewProfileForm(Model model) {
		// create model attribute to bind form data
		Profile profile = new Profile();
		model.addAttribute("profile", profile);
		
		List<Skills> skills = (List<Skills>) skillsRepo.findAll();
        model.addAttribute("skills",skills);
       
		return "new_profile";
	}
	
	
	@PostMapping("/saveProfile")
	public String saveProfile(@ModelAttribute("profile") Profile profile, @RequestParam("image") MultipartFile multipartFile) throws IOException {
		
		String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        profile.setPhotos(fileName);
		profileService.saveProfile(profile);
		
		String uploadDir = "user-photos/" + profile.getId(); 
        FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
		return "saveSuccess";
	}
	
	@GetMapping("/showFormForUpdate/{id}")
	public String showFormForUpdate(@PathVariable ( value = "id") long id, Model model) {

		Profile profile = profileService.getProfileById(id);
		
		model.addAttribute("profile", profile);
		
		List<Skills> skills = (List<Skills>) skillsRepo.findAll();
        model.addAttribute("skills",skills);
		return "new_profile";
	}
}
