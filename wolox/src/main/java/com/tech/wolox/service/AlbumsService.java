/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tech.wolox.service;

import com.tech.wolox.dto.AlbumDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author Usuario
 */
public interface AlbumsService {

    AlbumDTO[] getAlbums();

    AlbumDTO[] getAlbumsbyUser(Long userId);
    
    AlbumDTO createAlbum(AlbumDTO albumDTO);
    
}
