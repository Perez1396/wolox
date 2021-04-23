/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tech.wolox.service.implementation;

import com.tech.wolox.dto.PermissionDTO;
import com.tech.wolox.dto.UserTypeDTO;
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
            Permissions response = modelMapper.map(permissionDTO, Permissions.class);
            response = permissionRepository.save(response);
            PermissionDTO newPermission = modelMapper.map(response, PermissionDTO.class);
            return newPermission;  
    }

    @Override
    public PermissionDTO updatePermission(Integer userId, Integer albumId, PermissionDTO permissionDTO) {
        Optional<Permissions> permission = permissionRepository.findByUserIdAndAlbumId(userId, albumId);
        if (permission.isPresent()) {
            permission.get().setTypeId(permissionDTO.getType());
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
            //validationMap.put("type", permission.getTypes());
            //responseAlone.setType(validationMap.));
            return responseAlone;
        }).forEachOrdered((responseAlone) -> {
            response.add(responseAlone);
        });
        return response;
    }

    @Override
    public List<Integer> getUsersByAlbumAndType(Permissions permissions) {
        List<Integer> response = new ArrayList<>();
         Integer query = permissionRepository.findUserIdAndType(permissions.getAlbumId(), permissions.getTypeId());
         System.out.println("HELLOOOO"+query);
         response.add(query);
         
         /*for (Permissions permissions : query) {
             PermissionDTO resp = new PermissionDTO();
            resp.setUserId(permissions.getUserId());
            response.add(resp);
        }*/
         
         return response;

    }
    


}


