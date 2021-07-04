package em.app.service;

import java.util.List;

import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

import em.app.model.Post;

public interface PostService {
	List<Post> getAllPosts();

	Post createPost(Post post);

	Post updatePost(long id, Post post);

	void deletePost(long id);

	Post getPostById(long id);

	void addResourceHandlers(ResourceHandlerRegistry registry);
}
