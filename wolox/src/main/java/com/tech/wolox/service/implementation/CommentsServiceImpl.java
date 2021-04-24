/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tech.wolox.service.implementation;

import com.tech.wolox.dto.CommentDTO;
import com.tech.wolox.dto.PostDTO;
import com.tech.wolox.service.CommentsService;
import java.util.ArrayList;
import java.util.Arrays;
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
public class CommentsServiceImpl implements CommentsService {
    
    private static final String URL = "https://jsonplaceholder.cypress.io/comments";
    private static final String PARAM_COMMENTS = "?postId=";
    
    @Autowired
    private RestTemplate restTemplate;

    @Override
    public CommentDTO[] getComments() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("user-agent", "Application");
	HttpEntity<String> entity = new HttpEntity<>(headers);
        return restTemplate.exchange(URL, HttpMethod.GET, entity, CommentDTO[].class).getBody();
    }

    @Override
    public List<CommentDTO> getCommentsByPost(PostDTO[] resp) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("user-agent", "Application");
	HttpEntity<String> entity = new HttpEntity<>(headers);
        List<CommentDTO> commentResponse = new ArrayList<>();
        for (PostDTO postDTO : resp) {
          CommentDTO[] respComment = restTemplate.exchange(URL+PARAM_COMMENTS+postDTO.getId(), HttpMethod.GET, entity, CommentDTO[].class).getBody();
          commentResponse.addAll(Arrays.asList(respComment));
        }
        return commentResponse;
    }
    
    
    
}
