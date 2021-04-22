/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tech.wolox.service.implementation;

import com.tech.wolox.dto.UserDTO;
import com.tech.wolox.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author Usuario
 */
@Service
public class UsersServiceImpl implements UsersService{
    private static final String URL = "https://jsonplaceholder.typicode.com/users";
    
    @Autowired
    private RestTemplate restTemplate;
    
    @Override
    public UserDTO[] getUsers() {
        System.out.println("Acá");
        return restTemplate.getForObject(URL, UserDTO[].class);
    } 
    
}
