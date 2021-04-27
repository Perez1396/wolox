/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tech.wolox.controller;

import com.tech.wolox.dto.PhotoDTO;
import com.tech.wolox.service.PhotosService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
@Api(tags = "Photos consultation controller")
@RestController
@RequestMapping("/photos")
public class PhotosController {

    @Autowired
    private PhotosService photosServices;

    @ApiOperation(value = "Retrieve all the photos from the JSON data.")
    @GetMapping()
    public ResponseEntity<PhotoDTO[]> getPhotos() {
        log.info("With the endpoint /photos is called the service to retrieve the data.");
        PhotoDTO[] response = photosServices.getPhotos();
        return ResponseEntity.ok(response);
    }

    @ApiOperation(value = "Retrieve all the photos that belongs to a user from the JSON data.")
    @GetMapping("/byUser/{userId}")
    public ResponseEntity<List<PhotoDTO>> getPhotosByUser(
            @ApiParam(value = "ID corresponding to a user", required = true)
            @PathVariable("userId") Integer userId) {
        log.info("Is called the service to retrieve the data with the specific user ID.");
        List<PhotoDTO> response = photosServices.getPhotosByUser(userId);
        return ResponseEntity.ok(response);
    }
}
