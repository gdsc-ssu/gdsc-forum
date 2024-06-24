package com.example.gdscforum.domain.post.controller;

import com.example.gdscforum.common.dto.Response;
import com.example.gdscforum.domain.post.dto.PostDto;
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
    public Response<List<PostDto>> getAllPosts() {
        List<PostDto> posts = postService.getAllPosts();
        return Response.data(posts);
    }

    @GetMapping("/{id}")
    public Response<PostDto> getPostById(@PathVariable Integer id) {
        Optional<PostDto> postDTO = postService.getPostById(id);
        return postDTO.map(Response::data)
                .orElseGet(() -> Response.of(404, "Post not found", null));
    }

    @PostMapping
    public Response<PostDto> createPost(@RequestBody PostDto postDTO) {
        PostDto createdPost = postService.createPost(postDTO);
        return Response.data(createdPost);
    }

    @PutMapping("/{id}")
    public Response<PostDto> updatePost(@PathVariable Integer id, @RequestBody PostDto postDTO) {
        Optional<PostDto> updatedPost = postService.updatePost(id, postDTO);
        return updatedPost.map(Response::data)
                .orElseGet(() -> Response.of(404, "Post not found", null));
    }

    @DeleteMapping("/{id}")
    public Response<Void> deletePost(@PathVariable Integer id) {
        postService.deletePost(id);
        return Response.of(200, "Post deleted successfully", null);
    }
}
