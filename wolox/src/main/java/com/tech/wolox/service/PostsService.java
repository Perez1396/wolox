/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tech.wolox.service;

import com.tech.wolox.dto.CommentDTO;
import com.tech.wolox.dto.PostDTO;
import java.util.List;

/**
 *
 * @author Usuario
 */
public interface PostsService {
    PostDTO[] getPosts();
    List<CommentDTO> getPostsByUser(Integer id);
}
