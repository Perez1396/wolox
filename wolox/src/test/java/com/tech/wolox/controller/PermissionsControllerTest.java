/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tech.wolox.controller;

import com.tech.wolox.dto.PermissionDTO;
import com.tech.wolox.service.implementation.PermissionsServiceImpl;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author Usuario
 */
@RunWith(SpringRunner.class)
public class PermissionsControllerTest {
    
    private static PermissionDTO permissionDTO = new PermissionDTO(1,1,1,2);
    
    @Mock
    private PermissionsServiceImpl permissionService;
    
    @InjectMocks
    private PermissionsController permissionController;
    
    @Before
    public void setUp() {
    }

    /**
     * Test of getPermission method, of class PermissionsController.
     */
    @Test
    public void testGetPermission() {
        List<PermissionDTO> listPhotos = new ArrayList<>();
        listPhotos.add(permissionDTO);
        
        when(permissionService.getPermissions()).thenReturn(listPhotos);
        ResponseEntity<List<PermissionDTO>> testing = permissionController.getPermission(); 
        assertEquals(ResponseEntity.ok(listPhotos), testing);
        
    }
    
    @Test
    public void testNotPermissionsFound(){
        
        List<PermissionDTO> listPermission = new ArrayList<>();
        
        when(permissionService.getPermissions()).thenReturn(listPermission);
        ResponseEntity<List<PermissionDTO>> testing = permissionController.getPermission(); 
        assertEquals(ResponseEntity.status(HttpStatus.NOT_FOUND).build(), testing);
    }

    /**
     * Test of updatePermission method, of class PermissionsController.
     */
    @Test
    public void testUpdatePermission() {
        PermissionDTO permissionDTO2 = new PermissionDTO();
        permissionDTO2.setId(permissionDTO.getId());
        permissionDTO2.setAlbumId(permissionDTO.getAlbumId());
        permissionDTO2.setType(permissionDTO.getType());
        permissionDTO2.setUserId(permissionDTO.getUserId());
        when(permissionService.updatePermission(permissionDTO.userId, permissionDTO.albumId, permissionDTO2)).thenReturn(permissionDTO2);
        ResponseEntity<PermissionDTO> testing = permissionController.updatePermission(permissionDTO.userId, permissionDTO.albumId, permissionDTO2); 
        assertEquals(ResponseEntity.ok(permissionDTO2), testing);
    }
    
    @Test
    public void testFailUpdatePermission() {
        when(permissionService.updatePermission(1, 1, null)).thenReturn(null);
        ResponseEntity<PermissionDTO> testing = permissionController.updatePermission(1, 1, null); 
        assertEquals(ResponseEntity.status(HttpStatus.BAD_REQUEST).build(), testing);
    }

    /**
     * Test of createPermission method, of class PermissionsController.
     */
    @Test
    public void testCreatePermission() {
        when(permissionService.createPermission(permissionDTO)).thenReturn(permissionDTO);
        ResponseEntity<PermissionDTO> testing = permissionController.createPermission(permissionDTO); 
        assertEquals(ResponseEntity.status(HttpStatus.CREATED).body(permissionDTO), testing);
    }
    
    @Test
    public void testFailedCreatingPermission() {
        when(permissionService.createPermission(null)).thenReturn(null);
        ResponseEntity<PermissionDTO> testing = permissionController.createPermission(null); 
        assertEquals(ResponseEntity.status(HttpStatus.BAD_REQUEST).build(), testing);
    }
    
}
