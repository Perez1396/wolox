/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tech.wolox.service.implementation;

import com.tech.wolox.dto.CommentDTO;
import com.tech.wolox.dto.PostDTO;
import com.tech.wolox.service.CommentsService;
import com.tech.wolox.service.PostsService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author Usuario
 */
@Service
public class PostsServiceImpl implements PostsService{
    
    private static final String URL = "https://jsonplaceholder.cypress.io/posts";
    private static final String PARAM_POSTS = "?userId=";
    
    @Autowired
    private RestTemplate restTemplate;
    
    @Autowired
    private CommentsService commentsService;

    @Override
    public PostDTO[] getPosts() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("user-agent", "Application");
	HttpEntity<String> entity = new HttpEntity<>(headers);
        return restTemplate.exchange(URL, HttpMethod.GET, entity, PostDTO[].class).getBody();
    }

    @Override
    public List<CommentDTO> getPostsByUser(Integer userId) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("user-agent", "Application");
	HttpEntity<String> entity = new HttpEntity<>(headers);
        PostDTO[] resp = restTemplate.exchange(URL+PARAM_POSTS+userId, HttpMethod.GET, entity, PostDTO[].class).getBody();
        List<CommentDTO> postResponse = commentsService.getCommentsByPost(resp);
        return postResponse;
    }
    
}
