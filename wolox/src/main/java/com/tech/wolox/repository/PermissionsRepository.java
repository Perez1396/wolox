/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tech.wolox.repository;

import com.tech.wolox.model.Permissions;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Usuario
 */
@Repository
public interface PermissionsRepository extends JpaRepository <Permissions, Integer> {
    List<Permissions> findByUserId(Integer userId);
    @Query(value = "SELECT p.userId FROM Permissions p WHERE p.albumId = :albumId AND p.typeId = :typeId")
    List<Integer> findUserIdAndType(@Param("albumId") Integer albumId, @Param("typeId") Integer typeId);
    Optional<Permissions> findByUserIdAndAlbumId(Integer userId, Integer albumId);
    
}
