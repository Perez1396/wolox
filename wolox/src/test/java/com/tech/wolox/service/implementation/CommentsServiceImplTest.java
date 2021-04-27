/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tech.wolox.service.implementation;

import com.tech.wolox.dto.CommentDTO;
import com.tech.wolox.dto.PostDTO;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author Usuario
 */
@RunWith(SpringRunner.class)
public class CommentsServiceImplTest {
    
    private static final String URL = "https://jsonplaceholder.cypress.io/comments";
    private static final String PARAM_COMMENTS = "?postId=";
    private static final CommentDTO comment = new CommentDTO(1,1,"labore","juan@something.com","www.something.com");
    private static final PostDTO post = new PostDTO(1,1,"Post two","Wolox test");
    
    @Mock
    private RestTemplate restTemplate;
       
    @InjectMocks
    private CommentsServiceImpl commentService;
    
    
    @Before
    public void setUp(){
        
    }

    /**
     * Test of getComments method, of class CommentsServiceImpl.
     */
    @Test
    public void testGetComments() {
        List<CommentDTO> listComments = new ArrayList<>();
        listComments.add(comment);
        CommentDTO[] listCommentDTO = new CommentDTO[1];
        listComments.toArray(listCommentDTO);
        
        HttpHeaders headers = new HttpHeaders();
        headers.add("user-agent", "Application");
	HttpEntity<String> entity = new HttpEntity<>(headers);
        
        when(restTemplate.exchange(URL, HttpMethod.GET, entity, CommentDTO[].class))
                .thenReturn(new ResponseEntity<>(listCommentDTO,HttpStatus.OK));
        CommentDTO[] response = commentService.getComments();
        Assert.assertNotNull(response);
        Assert.assertEquals(response.length, listCommentDTO.length);
    }

    /**
     * Test of getCommentsByPost method, of class CommentsServiceImpl.
     */
    @Test
    public void testGetCommentsByPost() {
        List<CommentDTO> listComments = new ArrayList<>();
        listComments.add(comment);
        CommentDTO[] listCommentDTO = new CommentDTO[1];
        listComments.toArray(listCommentDTO);
        
        List<PostDTO> listPosts = new ArrayList<>();
        listPosts.add(post);
        PostDTO[] listPostDTO = new PostDTO[1];
        listPosts.toArray(listPostDTO);
        
        HttpHeaders headers = new HttpHeaders();
        headers.add("user-agent", "Application");
	HttpEntity<String> entity = new HttpEntity<>(headers);
        
        when(restTemplate.exchange(URL+PARAM_COMMENTS+post.getId(), HttpMethod.GET, entity, CommentDTO[].class))
                .thenReturn(new ResponseEntity<>(listCommentDTO,HttpStatus.OK));
        List<CommentDTO> response = commentService.getCommentsByPost(listPostDTO);
        Assert.assertNotNull(response);
        Assert.assertEquals(response.size(), listCommentDTO.length);
    }
    
    @Test
    public void testGetCommentsByUser() {
        List<CommentDTO> listComments = new ArrayList<>();
        listComments.add(comment);
        CommentDTO[] listCommentDTO = new CommentDTO[1];
        listComments.toArray(listCommentDTO);
        
        
        HttpHeaders headers = new HttpHeaders();
        headers.add("user-agent", "Application");
	HttpEntity<String> entity = new HttpEntity<>(headers);
        
        when(restTemplate.exchange(URL, HttpMethod.GET, entity, CommentDTO[].class))
                .thenReturn(new ResponseEntity<>(listCommentDTO,HttpStatus.OK));
        
        List<CommentDTO> response = commentService.getCommentsByName("labore");
        Assert.assertNotNull(response);
        Assert.assertEquals(response.size(), listComments.size());
    }
    
}
