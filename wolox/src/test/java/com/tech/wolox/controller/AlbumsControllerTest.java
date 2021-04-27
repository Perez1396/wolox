/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tech.wolox.controller;

import com.tech.wolox.dto.AlbumDTO;
import com.tech.wolox.service.implementation.AlbumsServiceImpl;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
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
public class AlbumsControllerTest {
    private static AlbumDTO album = new AlbumDTO(1,1,"album");
    @Mock
    private AlbumsServiceImpl albumService;
    
    @InjectMocks
    private AlbumsController albumController;
    
    @Before
    public void setupMock() {
    }

    /**
     * Test of getAlbums method, of class AlbumsController.
     */
    @Test
    public void testGetAlbums() {
        List<AlbumDTO> listAlbums = new ArrayList<>();
        listAlbums.add(album);
        AlbumDTO[] listAlbumDTO = new AlbumDTO[1];
        listAlbums.toArray(listAlbumDTO);
        
        when(albumService.getAlbums()).thenReturn(listAlbumDTO);
        ResponseEntity<AlbumDTO[]> testing = albumController.getAlbums(); 
        assertEquals(ResponseEntity.ok(listAlbumDTO), testing);
    }

    /**
     * Test of getAlbumsByUser method, of class AlbumsController.
     */
    @Test
    public void testGetAlbumsByUser() {
        
        AlbumDTO album2 = new AlbumDTO();
        album2.setId(album.getId());
        album2.setUserId(album.getUserId());
        album2.setTitle(album.getTitle());
        List<AlbumDTO> listAlbums = new ArrayList<>();
        listAlbums.add(album2);
        AlbumDTO[] listAlbumDTO = new AlbumDTO[1];
        listAlbums.toArray(listAlbumDTO);
        
        when(albumService.getAlbumsbyUser(album.getId())).thenReturn(listAlbumDTO);
        ResponseEntity<AlbumDTO[]> testing = albumController.getAlbumsByUser(album.getId()); 
        assertEquals(ResponseEntity.ok(listAlbumDTO), testing);
    }
    
}
