package com.example.gdscforum.domain.post.controller;

import com.example.gdscforum.common.dto.Response;
import com.example.gdscforum.domain.post.dto.PostDto;
import com.example.gdscforum.domain.post.service.PostService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {
    @Autowired
    private PostService postService;

    @GetMapping("/{id}")
    public Response<PostDto> getPost(@PathVariable Long id) {
        return Response.data(postService.getPost(id));
    }

    @GetMapping
    public Response<List<PostDto>> getAllPosts() {
        return Response.data(postService.getAllPosts());
    }

    @PostMapping
    public Response<PostDto> createPost(@RequestBody @Valid PostDto postDto) {
        return Response.data(postService.createPost(postDto));
    }
}
