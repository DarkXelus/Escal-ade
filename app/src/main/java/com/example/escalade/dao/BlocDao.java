package com.example.escalade.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.escalade.bo.Bloc;

import java.util.List;

@Dao
public interface BlocDao {

    @Query("SELECT * FROM bloc")
    List<Bloc> getAll();

    @Query("SELECT * FROM bloc WHERE uid IN (:blocIds)")
    List<Bloc> loadAllByIds(int[] blocIds);

    @Query("SELECT * FROM bloc WHERE nom LIKE :nom LIMIT 1")
    Bloc findByName(String nom);

    @Query("SELECT * FROM bloc JOIN site WHERE bloc.siteId == site.uid")
    List<Bloc> getAllBySite();

    @Insert
    void insertAll(Bloc... blocs);

    @Delete
    void delete(Bloc bloc);
}
