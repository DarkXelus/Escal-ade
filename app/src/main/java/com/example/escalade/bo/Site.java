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

    @ColumnInfo(name = "note")
    float note;

    public Site(String nom, String adresse, float longitude, float latitude, String urlSite, String telephone, boolean interieur, float note) {
        this.nom = nom;
        this.adresse = adresse;
        this.longitude = longitude;
        this.latitude = latitude;
        this.urlSite = urlSite;
        this.telephone = telephone;
        this.interieur = interieur;
        this.note = note;
    }

    @Override
    public String toString() {
        return "Site{" +
                "uid=" + uid +
                ", nom='" + nom + '\'' +
                ", adresse='" + adresse + '\'' +
                ", longitude=" + longitude +
                ", latitude=" + latitude +
                ", urlSite='" + urlSite + '\'' +
                ", telephone='" + telephone + '\'' +
                ", interieur=" + interieur +
                ", note=" + note +
                '}';
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public String getUrlSite() {
        return urlSite;
    }

    public void setUrlSite(String urlSite) {
        this.urlSite = urlSite;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public boolean isInterieur() {
        return interieur;
    }

    public void setInterieur(boolean interieur) {
        this.interieur = interieur;
    }

    public float getNote() {
        return note;
    }

    public void setNote(float note) {
        this.note = note;
    }
}
