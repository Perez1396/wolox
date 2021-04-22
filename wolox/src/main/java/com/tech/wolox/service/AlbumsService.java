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
@Service
public class AlbumsService {
    @Autowired
    private RestTemplate restTemplate;
    
    public AlbumDTO[] getAlbums() {
        String url = "https://jsonplaceholder.typicode.com/albums";
        return restTemplate.getForObject(url, AlbumDTO[].class);
    }
    
    public AlbumDTO[] getAlbumsbyUser(Long userId) {
        String url = "https://jsonplaceholder.typicode.com/albums?userId="+userId.toString();
        return restTemplate.getForObject(url, AlbumDTO[].class);
    }
}
