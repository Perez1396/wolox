/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tech.wolox.service.implementation;

import com.tech.wolox.dto.AlbumDTO;
import com.tech.wolox.service.AlbumsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author Usuario
 */
@Slf4j
@Service
public class AlbumsServiceImpl implements AlbumsService {

    private static final String URL = "https://jsonplaceholder.cypress.io/albums";
    private static final String PARAM = "?userId=";

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public AlbumDTO[] getAlbums() {
        log.info("Consult whole album json data");
        HttpHeaders headers = new HttpHeaders();
        headers.add("user-agent", "Application");
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<AlbumDTO[]> response = restTemplate
                .exchange(URL, HttpMethod.GET, entity, AlbumDTO[].class);
        return response.getBody();
    }

    @Override
    public AlbumDTO[] getAlbumsbyUser(Integer userId) {
        log.info("Consult whole album json data which belongs to a specific user");
        HttpHeaders headers = new HttpHeaders();
        headers.add("user-agent", "Application");
        HttpEntity<String> entity = new HttpEntity<>(headers);
        String url = URL + PARAM + userId.toString();
        return restTemplate.exchange(url, HttpMethod.GET, entity, AlbumDTO[].class).getBody();
    }

}
