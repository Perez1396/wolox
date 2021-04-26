/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tech.wolox.controller;

import com.tech.wolox.dto.CommentDTO;
import com.tech.wolox.dto.PostDTO;
import com.tech.wolox.service.PostsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
@Api(tags = "Posts consultation controller")
@RestController
@RequestMapping("/posts")
public class PostsController {
    
    @Autowired
    private PostsService postsService;
    
    @ApiOperation(value = "Retrieve all the posts from the JSON data.")
    @GetMapping()
    public ResponseEntity<PostDTO[]> getPosts() {
        PostDTO[] respuesta = postsService.getPosts();
        return ResponseEntity.ok(respuesta);
    }
    
    @ApiOperation(value = "Retrieve all the posts that belongs to a user from the JSON data.")
    @GetMapping("/byUser/{userId}")
    public ResponseEntity<List<CommentDTO>> getPostsByUser(
            @ApiParam(value = "ID corresponding to a user", required = true)
            @PathVariable("userId") Integer userId) {
        List<CommentDTO> respuesta = postsService.getPostsByUser(userId);
        return ResponseEntity.ok(respuesta);
    }
}
