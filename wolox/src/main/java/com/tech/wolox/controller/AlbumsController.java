/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tech.wolox.controller;

import com.tech.wolox.dto.AlbumDTO;
import com.tech.wolox.service.AlbumsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Usuario
 */
@RestController
@RequestMapping("/albums")
public class AlbumsController {
    
    @Autowired
    private AlbumsService albumServices;
    
    @GetMapping()
    public ResponseEntity <AlbumDTO[]>  getAlbums() {
        AlbumDTO[] respuesta = albumServices.getAlbums();    
        return ResponseEntity.ok(respuesta);
    }
    
    @GetMapping("/{userId}")
    public ResponseEntity <AlbumDTO[]>  getAlbumsByUser(@PathVariable("userId") Long userId) {
        AlbumDTO[] respuesta = albumServices.getAlbumsbyUser(userId);    
        return ResponseEntity.ok(respuesta);
    }
    
    
    
}
