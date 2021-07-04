package em.app.service;
import java.util.List;
import java.util.Optional;


import javax.transaction.Transactional;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

import em.app.model.Post;
import em.app.repository.PostRepository;
import em.app.validation.ResourceNotFoundException;
@Service
public class PostServiceImpl implements PostService{

	private final PostRepository postRepository;
	
	public PostServiceImpl(PostRepository postRepository) {
		super();
		this.postRepository = postRepository;
	}

	
	@Override
	@Transactional
	public List<Post> getAllPosts() {
		return postRepository.findAll();
	}
	
	
	@Override
	@Transactional
	public Post createPost(Post post) {
		return postRepository.save(post);
	}

	@Override
	@Transactional
	public Post updatePost(long id, Post postRequest) {
		Post post = postRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
		
		post.setTitle(postRequest.getTitle());
		post.setDescription(postRequest.getDescription());
		post.setContent(postRequest.getContent());
		return postRepository.save(post);
	}

	@Override
	@Transactional
	public void deletePost(long id) {
		Post post = postRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
		
		postRepository.delete(post);
	}

	@Override
	@Transactional
	public Post getPostById(long id) {
		Optional<Post> result = postRepository.findById(id);
		if(result.isPresent()) {
			return result.get();
		}else {
			//throw new ResourceNotFoundException("Post", "id", id);
			return null;
		}
		
//		Post post = postRepository.findById(id)
//				.orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
		//return post;
	}
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
	    registry.addResourceHandler("swagger-ui.html")
	      .addResourceLocations("classpath:/META-INF/resources/");

	    registry.addResourceHandler("/webjars/**")
	      .addResourceLocations("classpath:/META-INF/resources/webjars/");
	}
	

}
