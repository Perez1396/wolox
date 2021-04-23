/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tech.wolox.service.implementation;

import com.tech.wolox.dto.AlbumDTO;
import com.tech.wolox.service.AlbumsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author Usuario
 */
@Service
public class AlbumsServiceImpl implements AlbumsService{
    private static final String URL = "https://jsonplaceholder.cypress.io/albums";
    private static final String PARAM = "?userId=";
    
    @Autowired
    private RestTemplate restTemplate;
    
    @Override
    public AlbumDTO[] getAlbums() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("user-agent", "Application");
	HttpEntity<String> entity = new HttpEntity<>(headers);
        return restTemplate.exchange(URL, HttpMethod.GET, entity, AlbumDTO[].class).getBody();
    }
    
    @Override
    public AlbumDTO[] getAlbumsbyUser(Long userId) {
        String url = URL+PARAM+userId.toString();
        return restTemplate.getForObject(url, AlbumDTO[].class);
    }

    @Override
    public AlbumDTO createAlbum(AlbumDTO albumDTO) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
