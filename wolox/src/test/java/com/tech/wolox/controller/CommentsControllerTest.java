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
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author Usuario
 */
@RunWith(SpringRunner.class)
public class CommentsControllerTest {
    private static CommentDTO comment = new CommentDTO(1,1,"Juan","juan@something", "www.something.com");
    
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
        List<CommentDTO> listComments = new ArrayList<>();
        listComments.add(comment);
        CommentDTO[] listCommentDTO = new CommentDTO[1];
        listComments.toArray(listCommentDTO);
        
        when(commentService.getComments()).thenReturn(listCommentDTO);
        ResponseEntity<CommentDTO[]> testing = commentController.getComments(); 
        assertEquals(ResponseEntity.ok(listCommentDTO), testing);
    }
    
    @Test
    public void testGetCommentsByUser() {
        CommentDTO comment2 = new CommentDTO();
        comment2.setId(comment.getId());
        comment2.setPostId(comment.getPostId());
        comment2.setName(comment.getName());
        comment2.setEmail(comment.getEmail());
        comment2.setBody(comment.getBody());
        List<CommentDTO> listComments = new ArrayList<>();
        listComments.add(comment2);
        CommentDTO[] listCommentDTO = new CommentDTO[1];
        listComments.toArray(listCommentDTO);
        
        when(commentService.getCommentsByName(comment.getName())).thenReturn(listComments);
        ResponseEntity<List<CommentDTO>> testing = commentController.getCommentsByUser(comment.getName()); 
        assertEquals(ResponseEntity.ok(listComments), testing);
    }
    
    @Test
    public void testGetCommentsByUserNotFound() {
        List<CommentDTO> listComments = new ArrayList<>();
        
        when(commentService.getCommentsByName(comment.getName())).thenReturn(listComments);
        ResponseEntity<List<CommentDTO>> testing = commentController.getCommentsByUser(comment.getName()); 
        assertEquals(ResponseEntity.status(HttpStatus.NOT_FOUND).build(), testing);
    }
    
}
