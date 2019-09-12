package com.example.escalade.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.escalade.bo.Bloc;

import java.util.List;

@Dao
public abstract class BlocDao {

    @Query("SELECT * FROM bloc")
    public abstract List<Bloc> getAll();

    @Query("SELECT * FROM bloc WHERE uid IN (:blocIds)")
    public abstract List<Bloc> loadAllByIds(int[] blocIds);

    @Query("SELECT * FROM bloc WHERE nom LIKE :nom LIMIT 1")
    public abstract Bloc findByName(String nom);

    @Query("SELECT * FROM bloc JOIN site WHERE bloc.siteId == site.uid")
    public abstract List<Bloc> getAllBySite();

    @Query("SELECT * FROM bloc WHERE bloc = 0")
    public abstract  List<Bloc> getAllByBloc();

    @Query("SELECT * FROM bloc WHERE bloc = 1")
    public abstract  List<Bloc> getAllByVoie();

    @Insert
    public abstract List<Long> insertAll(Bloc... blocs);

    @Delete
    public abstract void delete(Bloc bloc);
}
