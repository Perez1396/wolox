/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tech.wolox.service;

import com.tech.wolox.dto.PermissionDTO;
import java.util.List;

/**
 *
 * @author Usuario
 */
public interface PermissionsService {

    PermissionDTO createPermission(PermissionDTO permissionDTO);

    PermissionDTO updatePermission(Integer userId, Integer albumId,
            PermissionDTO permissionDTO);

    List<PermissionDTO> getPermissions();
    
    
}
