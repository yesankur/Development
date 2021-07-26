package em.app.dto;

import javax.validation.constraints.NotBlank;

import em.app.validation.ValidContent;
import lombok.Data;

@Data
public class PostDto {
	private long id;
	@NotBlank(message = "Can not be Blank")
	private String title;
	private String description;
	@ValidContent(message = "Atleast one number and charavter required")
	private String content;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	
}
