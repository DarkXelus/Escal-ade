package com.example.escalade;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.escalade.bo.Bloc;
import com.example.escalade.dao.BlocDao;

@Database(entities = {Bloc.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract BlocDao blocDao();
}
