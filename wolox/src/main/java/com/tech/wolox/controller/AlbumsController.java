/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tech.wolox.controller;

import com.tech.wolox.dto.AlbumDTO;
import com.tech.wolox.service.AlbumsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
@RestController
@RequestMapping("/albums")
@Api(tags = "Albums consultation controller")
public class AlbumsController {

    @Autowired
    private AlbumsService albumServices;

    @ApiOperation(value = "Retrieve all the albums from the JSON.")
    @GetMapping()
    public ResponseEntity<AlbumDTO[]> getAlbums() {
        log.info("With the endpoint /albums is called the service to retrieve the data.");
        AlbumDTO[] response = albumServices.getAlbums();
        return ResponseEntity.ok(response);
    }

    @ApiOperation(value = "Obtain all the albums that are associated to an especific user.")
    @GetMapping("/{userId}")
    public ResponseEntity<AlbumDTO[]> getAlbumsByUser(
            @ApiParam(value = "ID corresponding to a user who has an album associated.", required = true)
            @PathVariable("userId") Integer userId) {
        log.info("The service is called to consult all the albums by user");
        AlbumDTO[] response = albumServices.getAlbumsbyUser(userId);
        return ResponseEntity.ok(response);
    }

}
