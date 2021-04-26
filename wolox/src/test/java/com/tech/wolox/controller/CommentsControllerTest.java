/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tech.wolox.controller;

import com.tech.wolox.dto.CommentDTO;
import com.tech.wolox.service.implementation.CommentsServiceImpl;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.http.ResponseEntity;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author Usuario
 */
@RunWith(SpringRunner.class)
public class CommentsControllerTest {
    @Mock
    private CommentsServiceImpl commentService;
    
    @InjectMocks
    private CommentsController commentController;
        
    @Before
    public void setUp() {
    }

    /**
     * Test of getComments method, of class CommentsController.
     */
    @Test
    public void testGetComments() {
        Integer postId = 1; 
        Integer id = 1;
        String name= "Juan";
        String email = "juan@something.com";
        String body = "www.something.com";
        
        CommentDTO comment = new CommentDTO(postId,id,name,email,body);
        List<CommentDTO> listComments = new ArrayList<>();
        listComments.add(comment);
        CommentDTO[] listCommentDTO = new CommentDTO[1];
        listComments.toArray(listCommentDTO);
        
        when(commentService.getComments()).thenReturn(listCommentDTO);
        ResponseEntity<CommentDTO[]> testing = commentController.getComments(); 
        assertEquals(ResponseEntity.ok(listCommentDTO), testing);
    }
    
}
