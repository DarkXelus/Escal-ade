package com.example.escalade;

import android.content.Context;

import androidx.room.Room;

public class Connexion {
    public static AppDatabase getConnexion(Context context)
    {
        return Room.databaseBuilder(context,AppDatabase.class, "db-escalade").build();
    }
}
