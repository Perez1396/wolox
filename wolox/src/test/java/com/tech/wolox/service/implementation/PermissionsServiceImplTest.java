/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tech.wolox.service.implementation;

import com.tech.wolox.dto.PermissionDTO;
import com.tech.wolox.model.Permissions;
import com.tech.wolox.repository.PermissionsRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author Usuario
 */
@RunWith(SpringRunner.class)
public class PermissionsServiceImplTest {
    private static Permissions permissions2 = new Permissions(1,1,1,1);
    @Mock
    private PermissionsRepository permissionRepository;
    
    @Mock
    private ModelMapper modelMapper;
    
    @InjectMocks
    private PermissionsServiceImpl permissionService;
    
    
    @Before
    public void setUp() {
    }

    /**
     * Test of GetPermission method, of class PermissionsServiceImpl.
     */
    @Test
    public void testGetPermission() {
        PermissionDTO permissionDTO = new PermissionDTO(1,1,1,2);
        List<Permissions> listPermissions = new ArrayList<>();
        List<PermissionDTO> listPermissionsDTO = new ArrayList<>();
        Permissions permissions = new Permissions(1,1,1,1);
        listPermissions.add(permissions);
        listPermissionsDTO.add(permissionDTO);
        
        when(permissionRepository.findAll())
                .thenReturn(listPermissions);
        when(modelMapper.map(permissions, PermissionDTO.class))
                .thenReturn(permissionDTO);
        List<PermissionDTO> response = permissionService.getPermissions();
        Assert.assertNotNull(response);
        Assert.assertEquals(response.size(), listPermissions.size());
    }

    /**
     * Test of updatePermission method, of class PermissionsServiceImpl.
     */
    @Test
    public void testUpdatePermission() {
        PermissionDTO permissionDTO = new PermissionDTO(1,1,1,2);
        Optional<Permissions> listPermissions = Optional.of(new Permissions(1,1,1,1));
        List<PermissionDTO> listPermissionsDTO = new ArrayList<>();
        Permissions permissions = new Permissions(1,1,1,1);
        listPermissionsDTO.add(permissionDTO);
                
        when(permissionRepository.findByUserIdAndAlbumId(1, 1))
                .thenReturn(listPermissions);
        when(modelMapper.map(permissionRepository.save(permissions), PermissionDTO.class))
                .thenReturn(permissionDTO);
        PermissionDTO response = permissionService.updatePermission(1,1, permissionDTO);
        Assert.assertNotNull(response);
        Assert.assertEquals(response.getId(), listPermissions.get().getId());
    }
    
    @Test
    public void testUpdatePermissionsReturnNull() {
        List<PermissionDTO> listPermissionsDTO = new ArrayList<>();
        PermissionDTO permissionDTO = new PermissionDTO(1,1,1,2);
        Optional<Permissions> listPermissions = Optional.empty();
        listPermissionsDTO.add(permissionDTO);
        when(permissionRepository.findByUserIdAndAlbumId(1, 1))
                .thenReturn(listPermissions);
        PermissionDTO response = permissionService.updatePermission(1,1, permissionDTO);
        Assert.assertEquals(response, null);
    }

    /**
     * Test of createPermissions method, of class PermissionsServiceImpl.
     */
    @Test
    public void testCreatePermissions() {
        List<PermissionDTO> listPermissionsDTO = new ArrayList<>();
        
        PermissionDTO permissionDTO = new PermissionDTO(1,1,1,2);
        Permissions permissions = new Permissions();
        permissions.setId(permissions2.getId());
        permissions.setAlbumId(permissions2.getAlbumId());
        permissions.setUserId(permissions2.getUserId());
        permissions.setTypeId(permissions2.getTypeId());
        
        listPermissionsDTO.add(permissionDTO);
               
        when(modelMapper.map(permissionDTO, Permissions.class))
                .thenReturn(permissions);
        when(permissionRepository.save(permissions))
                .thenReturn(permissions);
        when(modelMapper.map(permissions, PermissionDTO.class))
                .thenReturn(permissionDTO);
        PermissionDTO response = permissionService.createPermission(permissionDTO);
        Assert.assertNotNull(response);
        Assert.assertEquals(response.getId(), permissionDTO.getId());
    }
    
    @Test
    public void testCreatePermissionsReturnNull() {
        List<PermissionDTO> listPermissionsDTO = new ArrayList<>();
        
        PermissionDTO permissionDTO = new PermissionDTO(1,1,1,2);
        
        listPermissionsDTO.add(permissionDTO);
        
        PermissionDTO response = permissionService.createPermission(null);
        Assert.assertEquals(response, null);
    }
    
}
