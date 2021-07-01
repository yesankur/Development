package em.app.controller;

import java.io.IOException;


import javax.validation.Valid;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.multipart.MultipartFile;
import em.app.model.Profile;

import em.app.service.ProfileService;

@Controller
@Validated
public class ProfileController {
	@Autowired
	private ProfileService profileService;
	
	@GetMapping("/")
	  public String showNewProfileForm(Model model) { 
	return profileService.showNewProfileForm(model); 
	}
	
	@PostMapping("/saveProfile")
	ResponseEntity<?> addUser(@Valid @RequestBody @ModelAttribute("profile") Profile profile, @RequestParam("image") MultipartFile multipartFile) throws IOException {
		profileService.saveProfile(profile,multipartFile);
		return new ResponseEntity<String>(HttpStatus.ACCEPTED);
    }
//	public String saveProfile(@ModelAttribute("profile") Profile profile, @RequestParam("image") MultipartFile multipartFile) throws IOException {
//		profileService.saveProfile(profile,multipartFile);
//		return "saveSuccess";
//	}
	
	@GetMapping("/showFormForUpdate/{id}")
	public String showFormForUpdate(@PathVariable ( value = "id")  long id, Model model) {
		return profileService.showFormForUpdate(id, model);
	}
}
