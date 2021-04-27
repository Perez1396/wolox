/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tech.wolox.controller;

import com.tech.wolox.dto.PermissionDTO;
import com.tech.wolox.service.PermissionsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Usuario
 */
@Slf4j
@Api(tags = "Permissions management controller")
@RestController
@RequestMapping("/permission")
public class PermissionsController {

    @Autowired
    private PermissionsService permissionsService;

    @ApiOperation(value = "Retrieve all the permissions from the H2 database.")
    @GetMapping()
    public ResponseEntity<List<PermissionDTO>> getPermission() {
        log.info("With the endpoint /permission is called the service to retrieve the data.");
        List<PermissionDTO> response = permissionsService.getPermissions();
        if (response.isEmpty()) {
            log.info("No data found in the H2 database.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(response);
    }

    @ApiOperation(value = "Updates the type of permission(READ/WRITE) who has an especific user over an especific album.")
    @PutMapping("/byUser/{userId}/byAlbum/{albumId}")
    public ResponseEntity<PermissionDTO> updatePermission(
            @ApiParam(value = "ID corresponding to an user", required = true)
            @PathVariable("userId") Integer userId,
            @ApiParam(value = "ID corresponding to an album", required = true)
            @PathVariable("albumId") Integer albumId,
            @ApiParam(value = "json with the type of permission we want to modify corresponding to the userId and albumId entered.", required = true)
            @RequestBody PermissionDTO permissionDTO) {
        log.info("Is called the service to update a new permission in the H2 database.");
        PermissionDTO response = permissionsService.updatePermission(userId, albumId, permissionDTO);
        if (response == null) {
            log.info("Missmatchong data, returned null");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        return ResponseEntity.ok(response);
    }

    @ApiOperation(value = "Creates the type of permission(READ/WRITE) to an especific user over an especific album")
    @PostMapping()
    public ResponseEntity<PermissionDTO> createPermission(
            @ApiParam(value = "JSON With the information I want to create in the Database", required = true)
            @RequestBody PermissionDTO permissionDTO) {
        if (permissionDTO == null) {
            log.info("DTO null to create new data.");
            return ResponseEntity.badRequest().build();
        }
        log.info("Is called the service to create a new permission in the H2 database.");
        PermissionDTO response = permissionsService.createPermission(permissionDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

}
