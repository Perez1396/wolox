/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tech.wolox.service;

import com.tech.wolox.dto.UserDTO;
import com.tech.wolox.model.Permissions;
import java.util.List;

/**
 *
 * @author Usuario
 */
public interface UsersService {
     UserDTO[] getUsers();
     
     List<UserDTO> getUsersByAlbumAndType(Permissions permissions);
}
