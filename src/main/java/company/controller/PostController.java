package company.controller;

import company.model.Label;
import company.model.Post;
import company.repository.PostRepository;
import company.repository.gson.GsonPostRepository;

import java.time.LocalDateTime;
import java.util.List;

public class PostController {
    private final PostRepository postRepository = new GsonPostRepository();

    public Post createPost(String content, LocalDateTime start, LocalDateTime finish, List<Label> labels) {
        Post post = new Post(0, content, start, finish, labels);
        return postRepository.save(post);
    }

    public List<Post> getList() {
        return postRepository.getAll();
    }

    public Post update(Post post) {
        return postRepository.update(post);
    }

    public void delete(Integer id) {
        postRepository.deleteById(id);
    }

    public Post getById(Integer id) {
        return postRepository.getById(id);
    }

}
