/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tech.wolox.service.implementation;

import com.tech.wolox.dto.AddressDTO;
import com.tech.wolox.dto.CompanyDTO;
import com.tech.wolox.dto.GeoDTO;
import com.tech.wolox.dto.UserDTO;
import com.tech.wolox.model.Permissions;
import com.tech.wolox.repository.PermissionsRepository;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author Usuario
 */
@RunWith(SpringRunner.class)
public class UsersServiceImplTest {
    private static CompanyDTO company = new CompanyDTO("wolox","Here we are","231564");
    private static GeoDTO geo = new GeoDTO("05123", "0231");
    private static AddressDTO address = new AddressDTO("St 402 Mt. bour","presidential","Pereira","660004",geo);
    private static UserDTO mockedUser = new UserDTO(1,"Juan","juan@something.com","juan@something.com",address,"3218705321","www.something.com",company);

    private static final String URL = "https://jsonplaceholder.cypress.io/users";
    private static final String URL_USER = "https://jsonplaceholder.cypress.io/users/";
    
    @Mock
    private PermissionsRepository permissionRepository;
    
    @Mock
    private RestTemplate restTemplate;
    
    @InjectMocks
    private UsersServiceImpl userService;
    
    @Before
    public void setUp() {
    }

    /**
     * Test of getUsers method, of class UsersServiceImpl.
     */
    @Test
    public void testGetUsers() {
        List<UserDTO> listUsers = new ArrayList<>();
        listUsers.add(mockedUser);
        UserDTO[] listUserDTO = new UserDTO[1];
        listUsers.toArray(listUserDTO);
        
        HttpHeaders headers = new HttpHeaders();
        headers.add("user-agent", "Application");
	HttpEntity<String> entity = new HttpEntity<>(headers);
        
        when(restTemplate.exchange(URL, HttpMethod.GET, entity, UserDTO[].class))
                .thenReturn(new ResponseEntity<>(listUserDTO,HttpStatus.OK));
        UserDTO[] response = userService.getUsers();
        Assert.assertNotNull(response);
        Assert.assertEquals(response.length, listUserDTO.length);
    }

    /**
     * Test of getUsersByAlbumAndType method, of class UsersServiceImpl.
     */
    /*@Test
    public void testGetUsersByAlbumAndType() {
        UserDTO user = new UserDTO(1,"Juan","juan@something.com","juan@something.com",address,"3218705321","www.something.com",company);
        Permissions permission = new Permissions(1,1,1,1);
        List<Integer> listIntegers = new ArrayList<>();
        List<UserDTO> users = new ArrayList<>();
        listIntegers.add(1);
        listIntegers.add(2);
        listIntegers.add(3);
        
        HttpHeaders headers = new HttpHeaders();
        headers.add("user-agent", "Application");
	HttpEntity<String> entity = new HttpEntity<>(headers);
        
        when(permissionRepository.findUserIdAndType(permission.getAlbumId(), permission.getTypeId()))
                .thenReturn(listIntegers);
        when(restTemplate.exchange(URL_USER+listIntegers.get(1), HttpMethod.GET, entity, UserDTO.class))
                .thenReturn(new ResponseEntity<>(user,HttpStatus.OK));
        List<UserDTO> response = userService.getUsersByAlbumAndType(permission);
        Assert.assertNotNull(response);
        Assert.assertEquals(response.size(), listIntegers.size());
    }*/
    
}
