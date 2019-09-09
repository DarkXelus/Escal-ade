package com.example.escalade.bo;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Site {
    @PrimaryKey(autoGenerate = true)
    private int uid;

    @ColumnInfo(name = "nom")
    String nom;

    @ColumnInfo(name = "adresse")
    String adresse;

    @ColumnInfo(name = "longitude")
    float longitude;

    @ColumnInfo(name = "latitude")
    float latitude;

    @ColumnInfo(name = "urlSite")
    String urlSite;

    @ColumnInfo(name = "telephone")
    String telephone;

    @ColumnInfo(name = "interieur")
    boolean interieur;

}
