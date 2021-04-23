/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tech.wolox.controller;

import com.tech.wolox.dto.PhotoDTO;
import com.tech.wolox.service.PhotosService;
import java.util.List;
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
@RequestMapping("/photos")
public class PhotosController {

    @Autowired
    private PhotosService photosServices;

    @GetMapping()
    public ResponseEntity<PhotoDTO[]> getPhotos() {
        PhotoDTO[] respuesta = photosServices.getPhotos();
        return ResponseEntity.ok(respuesta);
    }
    
    @GetMapping("/byUser/{userId}")
    public ResponseEntity<List<PhotoDTO[]>> getPhotosByUser(@PathVariable("userId") Integer userId) {
        List<PhotoDTO[]> respuesta = photosServices.getPhotosByUser(userId);
        return ResponseEntity.ok(respuesta);
    }
}
