/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tech.wolox.service.implementation;

import com.tech.wolox.dto.PermissionDTO;
import com.tech.wolox.model.Permissions;
import com.tech.wolox.repository.PermissionsRepository;
import com.tech.wolox.service.PermissionsService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Usuario
 */
@Slf4j
@Service
public class PermissionsServiceImpl implements PermissionsService {

    @Autowired
    private PermissionsRepository permissionRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public PermissionDTO createPermission(PermissionDTO permissionDTO) {
        log.info("Method to create a new permission for a user to a specific album");
        if (permissionDTO == null) {
            return null;
        }
        log.info("Mapping the incoming data to save in Data base");
        Permissions response = modelMapper.map(permissionDTO, Permissions.class);
        response = permissionRepository.save(response);
        log.info("Mapping the returned data to a DTO for the method answer.");
        PermissionDTO newPermission = modelMapper.map(response, PermissionDTO.class);
        return newPermission;
    }

    @Override
    public PermissionDTO updatePermission(Integer userId, Integer albumId, PermissionDTO permissionDTO) {
        log.info("Method to update a permission for a user to a specific album.");
        Optional<Permissions> permission = permissionRepository.findByUserIdAndAlbumId(userId, albumId);
        if (permission.isPresent()) {
            log.info("Validation if exists a user and the album to update the type of permission.");
            permission.get().setTypeId(permissionDTO.getType());
            PermissionDTO response = modelMapper.map(permissionRepository
                    .save(permission.get()), PermissionDTO.class);
            return response;
        }
        return null;
    }

    @Override
    public List<PermissionDTO> getPermissions() {
        log.info("Consult whole permissions from  H2 data base.");
        List<PermissionDTO> response = new ArrayList<>();
        List<Permissions> permissions = permissionRepository.findAll();
        permissions.stream().map((permission) -> modelMapper.map(permission, PermissionDTO.class))
                .forEachOrdered((responses) -> {
            response.add(responses);
        });
        return response;
    }
}
