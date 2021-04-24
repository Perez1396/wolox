/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tech.wolox.controller;

import com.tech.wolox.dto.CommentDTO;
import com.tech.wolox.dto.PostDTO;
import com.tech.wolox.service.PostsService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Usuario
 */
@RestController
@RequestMapping("/posts")
public class PostsController {
    
    @Autowired
    private PostsService postsService;
    
    @GetMapping()
    public ResponseEntity<PostDTO[]> getPhotos() {
        PostDTO[] respuesta = postsService.getPosts();
        return ResponseEntity.ok(respuesta);
    }
    
    @GetMapping("/byUser/{userId}")
    public ResponseEntity<List<CommentDTO>> getPhotosByUser(@PathVariable("userId") Integer userId) {
        List<CommentDTO> respuesta = postsService.getPostsByUser(userId);
        return ResponseEntity.ok(respuesta);
    }
}
