package em.app.model;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import java.util.*;

@Entity
@Table(name = "profile")
public class Profile {
	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	private long id;
	
	@NotBlank(message = "Name is mandatory")
	@Column(name = "name")
	private String name;
	
	@Email(message = "Emai-Id not valid")
	@Column(name = "email")
	private String email;
	
	@NotBlank
	@Pattern(regexp="(^$|[0-9]{10})")
	@Column(name = "mobile")
	private String mobile;
	
	@NotBlank(message = "State is mandatory")
	@Column(name = "state")
	private String state;
	
	@NotBlank(message = "Gender is mandatory")
	@Column(name = "gender")
	private String gender;
	

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Set<Skills> getSkills() {
		return skills;
	}

	public void setSkills(Set<Skills> skills) {
		this.skills = skills;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	@ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinTable(
            name = "profile_skills",
            joinColumns = @JoinColumn(name = "profile_id"),
            inverseJoinColumns = @JoinColumn(name = "skills_id")
            )
    private Set<Skills> skills = new HashSet<>();
	
	
	 @Column(nullable = true, length = 64)
	    private String photos;

	public String getPhotos() {
		return photos;
	}

	public void setPhotos(String photos) {
		this.photos = photos;
	}
	@Transient
    public String getPhotosImagePath() {
		String src= "/user-photos/";
        if (photos == null || id == 0) return null;
         
        return src + id + "/" + photos;
    }

}
