/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tech.wolox.service.implementation;

import com.tech.wolox.dto.UserDTO;
import com.tech.wolox.model.Permissions;
import com.tech.wolox.repository.PermissionsRepository;
import com.tech.wolox.service.UsersService;
import java.util.ArrayList;
import java.util.List;
import org.modelmapper.ModelMapper;
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
@Service
public class UsersServiceImpl implements UsersService{
    private static final String URL = "https://jsonplaceholder.cypress.io/users";
    private static final String URL_USER = "https://jsonplaceholder.cypress.io/users/";
    
    @Autowired
    private PermissionsRepository permissionRepository;
    
    @Autowired
    private RestTemplate restTemplate;
    
    @Autowired
    private ModelMapper modelMapper;
    
    @Override
    public UserDTO[] getUsers() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("user-agent", "Application");
	HttpEntity<String> entity = new HttpEntity<>(headers);
        return restTemplate.exchange(URL, HttpMethod.GET, entity, UserDTO[].class).getBody();
         
    } 
    
    @Override
    public List<UserDTO> getUsersByAlbumAndType(Permissions permissions) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("user-agent", "Application");
	HttpEntity<String> entity = new HttpEntity<>(headers);
        List<UserDTO> userResponse = new ArrayList<>();
        List<Integer> query = permissionRepository.findUserIdAndType(permissions.getAlbumId(), permissions.getTypeId());
        for (int i = 0; i < query.size(); i++) {
            ResponseEntity<UserDTO> resp = restTemplate.exchange(URL_USER+query.get(i), HttpMethod.GET, entity, UserDTO.class);
            userResponse.add(resp.getBody());
        }
        return userResponse;
    }
    
}
