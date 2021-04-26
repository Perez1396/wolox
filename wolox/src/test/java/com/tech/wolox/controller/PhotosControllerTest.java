/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tech.wolox.controller;

import com.tech.wolox.dto.AlbumDTO;
import com.tech.wolox.dto.PhotoDTO;
import com.tech.wolox.service.implementation.PhotosServiceImpl;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author Usuario
 */
@RunWith(SpringRunner.class)
public class PhotosControllerTest {
    private static PhotoDTO photoDTO = new PhotoDTO(1,1, "cat.jpg", "google.com", "cat05x");
    
    @Mock
    private PhotosServiceImpl photoService;
    
    @InjectMocks
    private PhotosController photoController;
    
    @Before
    public void setUp() {
    }

    /**
     * Test of getPhotos method, of class PhotosController.
     */
    @Test
    public void testGetPhotos() {
        List<PhotoDTO> listPhotos = new ArrayList<>();
        listPhotos.add(photoDTO);
        PhotoDTO[] listPhotoDTO = new PhotoDTO[1];
        listPhotos.toArray(listPhotoDTO);
        
        when(photoService.getPhotos()).thenReturn(listPhotoDTO);
        ResponseEntity<PhotoDTO[]> testing = photoController.getPhotos(); 
        assertEquals(ResponseEntity.ok(listPhotoDTO), testing);
    }

    /**
     * Test of getPhotosByUser method, of class PhotosController.
     */
    @Test
    public void testGetPhotosByUser() {
        AlbumDTO albumDTO = new AlbumDTO(1,1,"test");
        photoDTO.setId(albumDTO.getUserId());
        List<PhotoDTO> listPhotos = new ArrayList<>();
        listPhotos.add(photoDTO);
        
        when(photoService.getPhotosByUser(photoDTO.getId())).thenReturn(listPhotos);
        ResponseEntity<List<PhotoDTO>> testing = photoController.getPhotosByUser(photoDTO.getId()); 
        assertEquals(ResponseEntity.ok(listPhotos), testing);
    }
    
}
