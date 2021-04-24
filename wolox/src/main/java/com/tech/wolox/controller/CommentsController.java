/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tech.wolox.controller;

import com.tech.wolox.dto.CommentDTO;
import com.tech.wolox.service.CommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Usuario
 */
@RestController
@RequestMapping("/comments")
public class CommentsController {
    
    @Autowired
    private CommentsService commentsService;
    
    @GetMapping()
    public ResponseEntity<CommentDTO[]> getComments() {
        CommentDTO[] respuesta = commentsService.getComments();
        return ResponseEntity.ok(respuesta);
    }
    
}
