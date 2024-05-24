package com.example.gdscforum.domain.post.controller;

import com.example.gdscforum.common.dto.Response;
import com.example.gdscforum.domain.post.dto.PostDTO;
import com.example.gdscforum.domain.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping
    public Response<List<PostDTO>> getAllPosts() {
        List<PostDTO> posts = postService.getAllPosts();
        return Response.data(posts);
    }

    @GetMapping("/{id}")
    public Response<PostDTO> getPostById(@PathVariable Integer id) {
        Optional<PostDTO> postDTO = postService.getPostById(id);
        return postDTO.map(Response::data)
                .orElseGet(() -> Response.of(404, "Post not found", null));
    }

    @PostMapping
    public Response<PostDTO> createPost(@RequestBody PostDTO postDTO) {
        PostDTO createdPost = postService.createPost(postDTO);
        return Response.data(createdPost);
    }

    @PutMapping("/{id}")
    public Response<PostDTO> updatePost(@PathVariable Integer id, @RequestBody PostDTO postDTO) {
        Optional<PostDTO> updatedPost = postService.updatePost(id, postDTO);
        return updatedPost.map(Response::data)
                .orElseGet(() -> Response.of(404, "Post not found", null));
    }

    @DeleteMapping("/{id}")
    public Response<Void> deletePost(@PathVariable Integer id) {
        postService.deletePost(id);
        return Response.of(200, "Post deleted successfully", null);
    }
}
