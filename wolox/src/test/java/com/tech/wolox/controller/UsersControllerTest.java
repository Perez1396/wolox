/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tech.wolox.controller;

import com.tech.wolox.dto.AddressDTO;
import com.tech.wolox.dto.CompanyDTO;
import com.tech.wolox.dto.GeoDTO;
import com.tech.wolox.dto.PermissionDTO;
import com.tech.wolox.dto.UserDTO;
import com.tech.wolox.model.Permissions;
import com.tech.wolox.service.implementation.UsersServiceImpl;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author Usuario
 */
@RunWith(SpringRunner.class)
public class UsersControllerTest {
    private static CompanyDTO company = new CompanyDTO("wolox","Here we are","231564");
    private static GeoDTO geo = new GeoDTO("05123", "0231");
    private static AddressDTO address = new AddressDTO("St 402 Mt. bour","presidential","Pereira","660004",geo);
    private static UserDTO mockedUser = new UserDTO(1,"Juan","juan@something.com","juan@something.com",address,"3218705321","www.something.com",company);
    
    @Mock
    private UsersServiceImpl userService;
    
    @InjectMocks
    private UsersController userController;
        
    @Before
    public void setUp() {
    }

    /**
     * Test of getUsers method, of class UsersController.
     */
    @Test
    public void testGetUsers() {
        
        PermissionDTO permission = new PermissionDTO(1,1,1,2);
        UserDTO userDTO2 = new UserDTO();
        userDTO2.setId(mockedUser.getId());
        userDTO2.setName(mockedUser.getName());
        userDTO2.setUsername(mockedUser.getUsername());
        userDTO2.setPhone(mockedUser.getPhone());
        userDTO2.setWebsite(mockedUser.getWebsite());
        userDTO2.setEmail(mockedUser.getEmail());
        CompanyDTO companyDTO = new CompanyDTO();
        companyDTO.setName(company.getName());
        companyDTO.setCatchPhrase(company.getCatchPhrase());
        companyDTO.setBs(company.getBs());
        AddressDTO address2 = new AddressDTO();
        address2.setCity(address.getCity());
        address2.setStreet(address.getStreet());
        address2.setSuite(address.getSuite());
        address2.setZipcode(address.getZipcode());
        GeoDTO geoDTO = new GeoDTO();
        geoDTO.setLat(geo.getLat());
        geoDTO.setLng(geo.getLng());
        address2.setGeo(geo);
        userDTO2.setAddress(address);
        userDTO2.setCompany(companyDTO);
        List<UserDTO> listUsers = new ArrayList<>();
        listUsers.add(userDTO2);
        UserDTO[] listUsersArray = new UserDTO[1];
        listUsers.toArray(listUsersArray);
        when(userService.getUsers()).thenReturn(listUsersArray);
        ResponseEntity<UserDTO[]> testing = userController.getUsers(); 
        assertEquals(ResponseEntity.ok(listUsersArray), testing);
    }

    /**
     * Test of getUsersByAlbumAndType method, of class UsersController.
     */
    
    
    @Test
    public void testGetUsersByAlbumAndType(){
        
        Integer id = 1;
        String name= "Juan";
        String username = "jdppul";
        String email = "juan@something.com";
        String phone = "3218705321";
        String website = "www.something.com";
        String street = "St 402 Mt. bour";
        String suite = "presidencial";
        String city = "Pereira";
        String zipcode = "660004";
        String lat = "05123";
        String lng = "0231";
        String companyName = "wolox";
        String catchPhrase = "Here we are";
        String bs = "231564";
        
        Permissions permission = new Permissions(1,1,1,2);
        CompanyDTO company = new CompanyDTO(companyName,catchPhrase,bs);
        GeoDTO geo = new GeoDTO(lat, lng);
        AddressDTO address = new AddressDTO(street,suite,city,zipcode,geo);
        UserDTO mockedUser = new UserDTO(id,name,username,email,address,phone,website,company);
        List<UserDTO> listUsers = new ArrayList<>();
        listUsers.add(mockedUser);
        
        when(userService.getUsersByAlbumAndType(permission)).thenReturn(listUsers);
        ResponseEntity<List<UserDTO>> testing = userController.getUsersByAlbumAndType(permission); 
        assertEquals(ResponseEntity.ok(listUsers), testing);
    }
    
    @Test
    public void testGetNotFound(){
        
        Permissions permission = new Permissions();
        List<UserDTO> listUsers = new ArrayList<>();
        
        when(userService.getUsersByAlbumAndType(permission)).thenReturn(listUsers);
        ResponseEntity<List<UserDTO>> testing = userController.getUsersByAlbumAndType(permission); 
        assertEquals(ResponseEntity.status(HttpStatus.NOT_FOUND).build(), testing);
    }
}
