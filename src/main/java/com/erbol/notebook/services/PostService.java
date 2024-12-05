package com.erbol.notebook.services;

import com.erbol.notebook.entities.Post;
import com.erbol.notebook.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public Post createPost(Post post) {
        return postRepository.save(post);
    }

    public Post updatePost(Long id, Post updatedPost) {
        // Check if the post exists
        return postRepository.findById(id).map(existingPost -> {
            // Update fields only if new values are provided
            if (updatedPost.getTitle() != null) {
                existingPost.setTitle(updatedPost.getTitle());
            }
            if (updatedPost.getContent() != null) {
                existingPost.setContent(updatedPost.getContent());
            }
            if (updatedPost.getStatus() != null) {
                existingPost.setStatus(updatedPost.getStatus());
            }
            if (updatedPost.getImages() != null) {
                existingPost.setImages(updatedPost.getImages());
            }
            // Save updated post
            return postRepository.save(existingPost);
        }).orElseThrow(() -> new RuntimeException("Post not found"));
    }


    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }
}

