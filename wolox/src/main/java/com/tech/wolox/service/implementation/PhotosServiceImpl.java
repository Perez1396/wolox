/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tech.wolox.service.implementation;

import com.tech.wolox.dto.PhotoDTO;
import com.tech.wolox.service.PhotosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author Usuario
 */
@Service
public class PhotosServiceImpl implements PhotosService{
    private static final String URL = "https://jsonplaceholder.typicode.com/photos";
    
    @Autowired
    private RestTemplate restTemplate;
    
    @Override
    public PhotoDTO[] getPhotos() {
        return restTemplate.getForObject(URL, PhotoDTO[].class);
    }
    
}
