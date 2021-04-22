/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tech.wolox.service.implementation;

import com.tech.wolox.dto.PermissionDTO;
import com.tech.wolox.model.Permissions;
import com.tech.wolox.model.Types;
import com.tech.wolox.repository.PermissionsRepository;
import com.tech.wolox.service.PermissionsService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Usuario
 */
@Service
public class PermissionsServiceImpl implements PermissionsService {

    @Autowired
    private PermissionsRepository permissionRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public PermissionDTO createPermission(PermissionDTO permissionDTO) {
        Permissions newPermission = new Permissions();
        List<Permissions> permission = permissionRepository.findByUserId(permissionDTO.getUserId());
        if (permission.isEmpty()) {
            PermissionDTO response = modelMapper.map(permissionDTO, PermissionDTO.class);
            newPermission.setUserId(response.getUserId());
            newPermission.setAlbumId(response.getAlbumId());
            permissionRepository.save(newPermission);
            return response;
        }
        else{
            return null;
        } 
            
    }

    @Override
    public PermissionDTO updatePermission(Integer userId, Integer albumId, PermissionDTO permissionDTO) {
        Optional<Permissions> permission = permissionRepository.findByUserIdAndAlbumId(userId, albumId);
        if (permission.isPresent()) {
            //permission.get().setTypes(types); TO DO: Hacer el mapeo de los types a string

            PermissionDTO response = modelMapper.map(permissionRepository.save(permission.get()), PermissionDTO.class);
            return response;
        }
        return null;
    }

    @Override
    public List<PermissionDTO> getPermissions() {
        List<PermissionDTO> response = new ArrayList<>();
        List<Permissions> permissions = permissionRepository.findAll();
        permissions.stream().map((permission) -> {
            PermissionDTO responseAlone = new PermissionDTO();
            responseAlone.setUserId(permission.getUserId());
            responseAlone.setAlbumId(permission.getAlbumId());
            //responseAlone.setType(permission.getTypes().toString());
            Map<String, List<Types>> validationMap = new HashMap<>();
            validationMap.put("type", permission.getTypes());
            responseAlone.setType(validationMap.toString());
            return responseAlone;
        }).forEachOrdered((responseAlone) -> {
            response.add(responseAlone);
        });
        return response;
    }

}


