package com.example.escalade;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.escalade.bo.Bloc;
import com.example.escalade.bo.Site;
import com.example.escalade.dao.BlocDao;
import com.example.escalade.helper.Converters;

@Database(entities = {Bloc.class, Site.class}, version = 1)
@TypeConverters({Converters.class})

public abstract class AppDatabase extends RoomDatabase {
    public abstract BlocDao blocDao();
}
