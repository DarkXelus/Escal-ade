package com.example.escalade.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.example.escalade.bo.Site;

import java.util.ArrayList;
import java.util.List;

@Dao
public abstract class SiteDao {

    @Query("SELECT * FROM site")
    public abstract List<Site> getAll();

    /*@Query("SELECT * FROM site WHERE uid IN (:siteIds)")
    ArrayList<Site> loadAllByIds(int[] siteIds);*/

    @Query("SELECT * FROM site WHERE nom LIKE :nom LIMIT 1")
    public abstract Site findByName(String nom);

    @Query("SELECT * FROM site WHERE uid LIKE :id LIMIT 1")
    public abstract Site get(int id);

	@Query("SELECT * FROM site WHERE uid LIKE :id LIMIT 1")
    public abstract Site findById(int id);
    
    @Insert
    public abstract List<Long> insertAll(Site... sites);

    @Update
    public abstract int update(Site... sites);

    @Delete
    public abstract void delete(Site site);
}
