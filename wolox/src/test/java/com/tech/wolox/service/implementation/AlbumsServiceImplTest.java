/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tech.wolox.service.implementation;

import com.tech.wolox.dto.AlbumDTO;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
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
public class AlbumsServiceImplTest {
    private static final String URL = "https://jsonplaceholder.cypress.io/albums";
    private static final String PARAM = "?userId=";
    private static final AlbumDTO album = new AlbumDTO(1,1,"rock");
    
    @Mock
    private RestTemplate restTemplate;
       
    @InjectMocks
    private AlbumsServiceImpl albumsService;
    
    @Before
    public void setUp() {
    }
    
    /**
     * Test of getAlbums method, of class AlbumsServiceImpl.
     */
    @Test
    public void testGetAlbums() {
        List<AlbumDTO> listAlbums = new ArrayList<>();
        listAlbums.add(album);
        AlbumDTO[] listAlbumDTO = new AlbumDTO[1];
        listAlbums.toArray(listAlbumDTO);
        
        HttpHeaders headers = new HttpHeaders();
        headers.add("user-agent", "Application");
	HttpEntity<String> entity = new HttpEntity<>(headers);
        
        when(restTemplate.exchange(URL, HttpMethod.GET, entity, AlbumDTO[].class))
                .thenReturn(new ResponseEntity<>(listAlbumDTO,HttpStatus.OK));
        AlbumDTO[] response = albumsService.getAlbums();
        Assert.assertNotNull(response);
        Assert.assertEquals(response.length, listAlbumDTO.length);
    }

    /**
     * Test of getAlbumsbyUser method, of class AlbumsServiceImpl.
     */
    @Test
    public void testGetAlbumsbyUser() {
        List<AlbumDTO> listAlbums = new ArrayList<>();
        listAlbums.add(album);
        AlbumDTO[] listAlbumDTO = new AlbumDTO[1];
        listAlbums.toArray(listAlbumDTO);
        
        HttpHeaders headers = new HttpHeaders();
        headers.add("user-agent", "Application");
	HttpEntity<String> entity = new HttpEntity<>(headers);
        
        when(restTemplate.exchange(URL+PARAM+album.getUserId(), HttpMethod.GET, entity, AlbumDTO[].class))
                .thenReturn(new ResponseEntity<>(listAlbumDTO,HttpStatus.OK));
        AlbumDTO[] response = albumsService.getAlbumsbyUser(album.getUserId());
        Assert.assertNotNull(response);
        Assert.assertEquals(response.length, listAlbumDTO.length);
    }
}
