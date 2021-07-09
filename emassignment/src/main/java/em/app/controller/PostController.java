package em.app.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import em.app.dto.PostDto;
import em.app.model.Post;
import em.app.service.PostService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@RestController
@RequestMapping("/api/posts")
@Api(value="Post-Custom", description="Custom Description")
public class PostController {

	@Autowired
	private ModelMapper modelMapper;

	private PostService postService;

	public PostController(PostService postService) {
		super();
		this.postService = postService;
	}
//	@ApiResponses(value = {
//	        @ApiResponse(code = 200, message = "Success", response = YourObject.class),
//	        @ApiResponse(code = 401, message = "Unauthorized"),
//	        @ApiResponse(code = 403, message="Forbidden"),
//	        @ApiResponse(code = 404, message = "Not Found"),
//	        @ApiResponse(code = 500, message = "Failure")
//	})
	@ApiOperation(value = "Get All Post-Custom")
	@GetMapping
	public List<PostDto> getAllPosts(
			@RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy) 
	{

		return postService.getAllPosts(pageNo, pageSize, sortBy).stream().map(post -> modelMapper.map(post, PostDto.class))
				.collect(Collectors.toList());
	}
	
	@ApiOperation(value = "Find Post-Custom")
	@GetMapping("/{id}")
	public ResponseEntity<PostDto> getPostById(@PathVariable(name = "id") Long id) {
		Post post = postService.getPostById(id);
			if(post==null)
			return new ResponseEntity<PostDto>(HttpStatus.NOT_FOUND);
		else
		{
		// convert entity to DTO
		PostDto postResponse = modelMapper.map(post, PostDto.class);

		return ResponseEntity.ok().body(postResponse);
		}
		
	}
	
	@ApiOperation(value = "Create Post-Custom")
	@PostMapping
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto) {

		// convert DTO to entity
		Post postRequest = modelMapper.map(postDto, Post.class);

		Post post = postService.createPost(postRequest);

		// convert entity to DTO
		PostDto postResponse = modelMapper.map(post, PostDto.class);

		return new ResponseEntity<PostDto>(postResponse, HttpStatus.CREATED);
	}

	// change the request for DTO
	// change the response for DTO
	@ApiOperation(value = "Update Post-Custom")
	@PutMapping("/{id}")
	public ResponseEntity<PostDto> updatePost(@PathVariable long id, @RequestBody PostDto postDto) {

		// convert DTO to Entity
		Post postRequest = modelMapper.map(postDto, Post.class);

		Post post = postService.updatePost(id, postRequest);

		// entity to DTO
		PostDto postResponse = modelMapper.map(post, PostDto.class);

		return ResponseEntity.ok().body(postResponse);
	}
	 @ApiOperation(value = "Delete Post-Custom")
	@DeleteMapping("/{id}")
	public ResponseEntity<HttpStatus> deletePost(@PathVariable(name = "id") Long id) {
		postService.deletePost(id);
		return  new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
	}
	@PostMapping("/logout")
	public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	if (auth != null){
	new SecurityContextLogoutHandler().logout(request, response, auth);
	}
	return "ANKUR you have logged out successfully";
	}

}