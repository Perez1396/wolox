/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tech.wolox.service.implementation;

import com.tech.wolox.dto.AlbumDTO;
import com.tech.wolox.dto.PhotoDTO;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
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
public class PhotosServiceImplTest {
    private static final String URL = "https://jsonplaceholder.cypress.io/photos";
    private static final String URL_ALBUMS = "https://jsonplaceholder.cypress.io/albums";
    private static final String PARAM_USER = "?userId=";
    private static final String PARAM_ALBUM = "?albumId=";
    private static final PhotoDTO photo = new PhotoDTO(1,1,"labore","juan@something.com","www.something.com");
    private static final AlbumDTO album = new AlbumDTO(1,1,"rock");
   
    @Mock
    private RestTemplate restTemplate;
       
    @InjectMocks
    private PhotosServiceImpl photoService;
    
    @Before
    public void setUp() {
    }

    /**
     * Test of getPhotos method, of class PhotosServiceImpl.
     */
    @Test
    public void testGetPhotos() {
        List<PhotoDTO> listPhotos = new ArrayList<>();
        listPhotos.add(photo);
        PhotoDTO[] listPhotosDTO = new PhotoDTO[1];
        listPhotos.toArray(listPhotosDTO);
        
        HttpHeaders headers = new HttpHeaders();
        headers.add("user-agent", "Application");
	HttpEntity<String> entity = new HttpEntity<>(headers);
        
        when(restTemplate.exchange(URL, HttpMethod.GET, entity, PhotoDTO[].class))
                .thenReturn(new ResponseEntity<>(listPhotosDTO,HttpStatus.OK));
        PhotoDTO[] response = photoService.getPhotos();
        Assert.assertNotNull(response);
        Assert.assertEquals(response.length, listPhotosDTO.length);
    }

    /**
     * Test of getPhotosByUser method, of class PhotosServiceImpl.
     */
    @Test
    public void testGetPhotosByUser() {
        
        List<PhotoDTO> listPhotos = new ArrayList<>();
        List<AlbumDTO> listAlbums = new ArrayList<>();
        
        PhotoDTO[] listPhotosDTO = new PhotoDTO[1];
        AlbumDTO[] listAlbumDTO = new AlbumDTO[1];
        
        listPhotos.add(photo);
        listAlbums.add(album);
        
        listPhotos.toArray(listPhotosDTO);
        listAlbums.toArray(listAlbumDTO);
        Integer userId = 1;
        
        HttpHeaders headers = new HttpHeaders();
        headers.add("user-agent", "Application");
	HttpEntity<String> entity = new HttpEntity<>(headers);
        
        when(restTemplate.exchange(URL_ALBUMS+PARAM_USER+userId, HttpMethod.GET, entity, AlbumDTO[].class))
                .thenReturn(new ResponseEntity<>(listAlbumDTO,HttpStatus.OK));
        when(restTemplate.exchange(URL+PARAM_ALBUM+album.getId(), HttpMethod.GET, entity, PhotoDTO[].class))
                .thenReturn(new ResponseEntity<>(listPhotosDTO,HttpStatus.OK));
        List<PhotoDTO> response = photoService.getPhotosByUser(photo.getId());
        Assert.assertNotNull(response);
        Assert.assertEquals(response.size(), listPhotosDTO.length);
    }
    
}
