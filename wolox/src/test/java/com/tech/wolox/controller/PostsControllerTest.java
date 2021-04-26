/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tech.wolox.controller;

import com.tech.wolox.dto.CommentDTO;
import com.tech.wolox.dto.PostDTO;
import com.tech.wolox.service.implementation.PostsServiceImpl;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author Usuario
 */
@RunWith(SpringRunner.class)
public class PostsControllerTest {
    private static PostDTO postDTO = new PostDTO(1,1, "cat.jpg", "google.com");
    private static CommentDTO commentDTO = new CommentDTO(1,1, "juan", "google.com", "body");
    
    @Mock
    private PostsServiceImpl postService;
    
    @InjectMocks
    private PostsController postController;
    
    @Before
    public void setUp() {
    }

    /**
     * Test of getPosts method, of class PostsController.
     */
    @Test
    public void testGetPosts() {
        List<PostDTO> listPosts = new ArrayList<>();
        listPosts.add(postDTO);
        PostDTO[] listPostDTO = new PostDTO[1];
        listPosts.toArray(listPostDTO);
        
        when(postService.getPosts()).thenReturn(listPostDTO);
        ResponseEntity<PostDTO[]> testing = postController.getPosts(); 
        assertEquals(ResponseEntity.ok(listPostDTO), testing);
    }

    /**
     * Test of getPostsByUser method, of class PostsController.
     */
    @Test
    public void testGetPostsByUser() {
        List<CommentDTO> listPosts = new ArrayList<>();
        listPosts.add(commentDTO);
        
        when(postService.getPostsByUser(postDTO.getId())).thenReturn(listPosts);
        ResponseEntity<List<CommentDTO>> testing = postController.getPostsByUser(postDTO.getId()); 
        assertEquals(ResponseEntity.ok(listPosts), testing);
    }
    
}
