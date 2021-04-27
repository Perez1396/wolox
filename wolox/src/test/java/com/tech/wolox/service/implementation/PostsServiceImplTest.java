/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tech.wolox.service.implementation;

import com.tech.wolox.dto.CommentDTO;
import com.tech.wolox.dto.PhotoDTO;
import com.tech.wolox.dto.PostDTO;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
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
public class PostsServiceImplTest {
    private static PostDTO postDTO = new PostDTO(1,1, "cat.jpg", "google.com");
    private static CommentDTO commentDTO = new CommentDTO(1,1, "juan", "google.com", "body");
    private static final String URL = "https://jsonplaceholder.cypress.io/posts";
    private static final String PARAM_POSTS = "?userId=";
    private static final String URL_COMMENTS = "https://jsonplaceholder.cypress.io/comments";
    private static final String PARAM_COMMENTS = "?postId=";
    
    @Mock
    private RestTemplate restTemplate;
    
    @Mock
    private CommentsServiceImpl commentService;
    
    @InjectMocks
    private PostsServiceImpl postsService;
    
    @Before
    public void setUp() {
    }

    /**
     * Test of getPosts method, of class PostsServiceImpl.
     */
    @Test
    public void testGetPosts() {
        List<PostDTO> listPosts = new ArrayList<>();
        listPosts.add(postDTO);
        PostDTO[] listPostsDTO = new PostDTO[1];
        listPosts.toArray(listPostsDTO);
        
        HttpHeaders headers = new HttpHeaders();
        headers.add("user-agent", "Application");
	HttpEntity<String> entity = new HttpEntity<>(headers);
        
        when(restTemplate.exchange(URL, HttpMethod.GET, entity, PostDTO[].class))
                .thenReturn(new ResponseEntity<>(listPostsDTO,HttpStatus.OK));
        PostDTO[] response = postsService.getPosts();
        Assert.assertNotNull(response);
        Assert.assertEquals(response.length, listPostsDTO.length);
    }

    /**
     * Test of getPostsByUser method, of class PostsServiceImpl.
     */
    @Test
    public void testGetPostsByUser() {
        List<PostDTO> listPosts = new ArrayList<>();
        List<CommentDTO> listComments = new ArrayList<>();
        PostDTO[] listPostsDTO = new PostDTO[1];
        CommentDTO[] listCommentDTO = new CommentDTO[1];
        
        listComments.add(commentDTO);
        listPosts.add(postDTO);
        listPosts.toArray(listPostsDTO);
        listComments.toArray(listCommentDTO);
        
        HttpHeaders headers = new HttpHeaders();
        headers.add("user-agent", "Application");
	HttpEntity<String> entity = new HttpEntity<>(headers);
        
        when(restTemplate.exchange(URL+PARAM_POSTS+postDTO.getId(), HttpMethod.GET, entity, PostDTO[].class))
                .thenReturn(new ResponseEntity<>(listPostsDTO,HttpStatus.OK));
        when(commentService.getCommentsByPost(listPostsDTO)).thenReturn(listComments);
        List<CommentDTO> response = postsService.getPostsByUser(postDTO.getId());
        Assert.assertNotNull(response);
        Assert.assertEquals(response.size(), listPostsDTO.length);
    }
    
}
