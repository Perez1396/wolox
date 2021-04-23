/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tech.wolox.controller;

import com.tech.wolox.dto.UserDTO;
import com.tech.wolox.model.Permissions;
import com.tech.wolox.service.UsersService;
import java.util.List;
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
@RestController
@RequestMapping("/users")
public class UsersController {
 
    @Autowired
    private UsersService usersServices;
 
    @GetMapping()
    public ResponseEntity <UserDTO[]>  getUsers() {
        UserDTO[] respuesta = usersServices.getUsers();    
        return ResponseEntity.ok(respuesta);
    }
    
    @GetMapping("/byAlbum")
    public ResponseEntity<List<UserDTO>> getUsersByAlbumAndType(
            @RequestBody Permissions permissions){
        
        List<UserDTO> response = usersServices.getUsersByAlbumAndType(permissions);
        if (response.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(response);
    }
     
 
}
