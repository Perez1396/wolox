/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tech.wolox.service.implementation;

import com.tech.wolox.dto.CommentDTO;
import com.tech.wolox.dto.PostDTO;
import com.tech.wolox.dto.UserDTO;
import com.tech.wolox.service.CommentsService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author Usuario
 */
@Slf4j
@Service
public class CommentsServiceImpl implements CommentsService {
    
    private static final String URL = "https://jsonplaceholder.cypress.io/comments";
    private static final String PARAM_COMMENTS = "?postId=";
    
    @Autowired
    private RestTemplate restTemplate;
    
    @Autowired
    private PostsServiceImpl postsService;

    @Override
    public CommentDTO[] getComments() {
        log.info("Consult whole comments json data");
        log.info("Http headers used for the exchange method of the restTemplate");
        HttpHeaders headers = new HttpHeaders();
        headers.add("user-agent", "Application");
	HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<CommentDTO[]> response = restTemplate.exchange(URL, HttpMethod.GET, entity, CommentDTO[].class);
        return response.getBody();
    }

    @Override
    public List<CommentDTO> getCommentsByPost(PostDTO[] resp) {
        log.info("Consult whole comments json data which belongs to a specific post.");
        
        HttpHeaders headers = new HttpHeaders();
        headers.add("user-agent", "Application");
	HttpEntity<String> entity = new HttpEntity<>(headers);
        List<CommentDTO> commentResponse = new ArrayList<>();
        log.info("Iteration to save in an Array the comments that a user made in a post.");
        for (PostDTO postDTO : resp) {
          ResponseEntity<CommentDTO[]> respComment = restTemplate.exchange(URL+PARAM_COMMENTS+postDTO.getId(), HttpMethod.GET, entity, CommentDTO[].class);
          commentResponse.addAll(Arrays.asList(respComment.getBody()));
        }
        return commentResponse;
    }

   @Override
    public List<CommentDTO> getCommentsByName(String name) {
        log.info("Filter the names of the comments using a string text.");
        CommentDTO[] commentFiltered = this.getComments();
        List<CommentDTO> commentResponse = new ArrayList<>();
        for (CommentDTO commentDTO : commentFiltered) {
            log.info("Using the iterator is asked if the comments contains the name that was entered.");
            if(commentDTO.getName().contains(name)){
                commentResponse.add(commentDTO);
            }
        }
        return commentResponse;
    }

        

    
    
    
}
