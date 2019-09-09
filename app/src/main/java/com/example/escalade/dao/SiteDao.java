package com.example.escalade.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.escalade.bo.Site;

import java.util.List;

@Dao
public interface SiteDao {

    @Query("SELECT * FROM site")
    List<Site> getAll();

    @Query("SELECT * FROM site WHERE uid IN (:siteIds)")
    List<Site> loadAllByIds(int[] siteIds);

    @Query("SELECT * FROM site WHERE nom LIKE :nom LIMIT 1")
    Site findByName(String nom);

    @Insert
    void insertAll(Site... sites);

    @Delete
    void delete(Site site);
}
