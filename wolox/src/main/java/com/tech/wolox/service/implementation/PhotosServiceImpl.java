/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tech.wolox.service.implementation;

import com.tech.wolox.dto.AlbumDTO;
import com.tech.wolox.dto.PhotoDTO;
import com.tech.wolox.service.PhotosService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.modelmapper.ModelMapper;
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
public class PhotosServiceImpl implements PhotosService{
    private static final String URL = "https://jsonplaceholder.cypress.io/photos";
    private static final String URL_ALBUMS = "https://jsonplaceholder.cypress.io/albums";
    private static final String PARAM_USER = "?userId=";
    private static final String PARAM_ALBUM = "?albumId=";
    
    @Autowired
    private RestTemplate restTemplate;
    
    
    @Override
    public PhotoDTO[] getPhotos() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("user-agent", "Application");
	HttpEntity<String> entity = new HttpEntity<>(headers);
        return restTemplate.exchange(URL, HttpMethod.GET, entity, PhotoDTO[].class).getBody();
    }

    @Override
    public List<PhotoDTO> getPhotosByUser(Integer userId) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("user-agent", "Application");
	HttpEntity<String> entity = new HttpEntity<>(headers);
        AlbumDTO[] resp = restTemplate.exchange(URL_ALBUMS+PARAM_USER+userId, HttpMethod.GET, entity, AlbumDTO[].class).getBody();
        List<PhotoDTO> userResponse = getPhotosByAlbum(resp, entity);
        return userResponse;
    }
    
    private List<PhotoDTO> getPhotosByAlbum(AlbumDTO[] resp, HttpEntity<String> entity ){
        List<PhotoDTO> userResponse = new ArrayList<>();
        for (AlbumDTO albumDTO : resp) {
          PhotoDTO[] respPhotos = restTemplate.exchange(URL+PARAM_ALBUM+albumDTO.getId(), HttpMethod.GET, entity, PhotoDTO[].class).getBody();
          userResponse.addAll(Arrays.asList(respPhotos));
        }
        return userResponse;
    }
    
    
    
}
