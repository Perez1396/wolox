/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tech.wolox.controller;

import com.tech.wolox.dto.PermissionDTO;
import com.tech.wolox.service.implementation.PermissionsServiceImpl;
import java.util.List;
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
@RestController
@RequestMapping("/permission")
public class PermissionsController {
    
    @Autowired
    private PermissionsServiceImpl permissionsServiceImpl;
    
    @GetMapping()
    public ResponseEntity<List<PermissionDTO>> getPermission(){
        List<PermissionDTO> response = permissionsServiceImpl.getPermissions();
        if (response.isEmpty()) {
           ResponseEntity.noContent();
        }
        return ResponseEntity.ok(response);
    }
    
    @PutMapping("/byUser/{userId}/byAlbum/{albumId}")
    public ResponseEntity<PermissionDTO> updatePermission(
            @PathVariable("userId") Integer userId,
            @PathVariable("userId") Integer albumId,
            @RequestBody PermissionDTO permissionDTO){
    PermissionDTO response = permissionsServiceImpl.updatePermission(userId, albumId, permissionDTO);
        if (response == null) {
            ResponseEntity.notFound();
        }
        
        return ResponseEntity.ok(response);
    }
    
    @PostMapping()
    public ResponseEntity<PermissionDTO> createPermission(@RequestBody PermissionDTO permissionDTO){
        if (permissionDTO == null) {
            return ResponseEntity.badRequest().build();
        }
        
        PermissionDTO response = permissionsServiceImpl.createPermission(permissionDTO);
        if (response == null) {
            return ResponseEntity.status(HttpStatus.FOUND).build();
        }
        
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}