/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tech.wolox.controller;

import com.tech.wolox.dto.UserDTO;
import com.tech.wolox.model.Permissions;
import com.tech.wolox.service.UsersService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Usuario
 */
@Slf4j
@Api(tags = "Users consultation controller")
@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private UsersService usersServices;

    @ApiOperation(value = "Retrieve all the users from the JSON data.")
    @GetMapping()
    public ResponseEntity<UserDTO[]> getUsers() {
        log.info("With the endpoint /users is called the service to retrieve the data.");
        UserDTO[] respuesta = usersServices.getUsers();
        return ResponseEntity.ok(respuesta);
    }

    @ApiOperation(value = "Bring in all the users by type of permission and ID of an album.")
    @GetMapping("/byAlbum")
    public ResponseEntity<List<UserDTO>> getUsersByAlbumAndType(
            @ApiParam(value = "Json object with the albumId and typeId to bring the users with it.", required = true)
            @RequestBody Permissions permissions) {
        log.info("With the endpoint /photos/byAlbum is called the service to retrieve the data with the sent data.");
        List<UserDTO> response = usersServices.getUsersByAlbumAndType(permissions);
        if (response.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(response);
    }

}
