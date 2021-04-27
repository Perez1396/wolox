/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tech.wolox.controller;

import com.tech.wolox.dto.CommentDTO;
import com.tech.wolox.service.CommentsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Usuario
 */
@Slf4j
@Api(tags = "Comments consultation controller")
@RestController
@RequestMapping("/comments")
public class CommentsController {

    @Autowired
    private CommentsService commentsService;

    @ApiOperation(value = "Retrieve all the comments from the JSON.")
    @GetMapping()
    public ResponseEntity<CommentDTO[]> getComments() {
        log.info("With the endpoint /comments is called the service to retrieve the data.");
        CommentDTO[] response = commentsService.getComments();
        return ResponseEntity.ok(response);
    }

    @ApiOperation(value = "Retrieve all the comments  filtered by name.")
    @GetMapping("/byName/{name}")
    public ResponseEntity<List<CommentDTO>> getCommentsByUser(@PathVariable("name") String name) {
        List<CommentDTO> response = commentsService.getCommentsByName(name);
        if (response.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(response);
    }

}
