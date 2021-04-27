/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tech.wolox.service;

import com.tech.wolox.dto.AlbumDTO;

/**
 *
 * @author Usuario
 */
public interface AlbumsService {

    AlbumDTO[] getAlbums();

    AlbumDTO[] getAlbumsbyUser(Integer userId);
    
}
