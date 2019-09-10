package com.example.escalade.bo;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.escalade.Enum.Particularite;

import java.util.Arrays;
import java.util.List;
@Entity
public class Bloc {
        @PrimaryKey(autoGenerate = true)
        private int uid;

        @ColumnInfo(name = "nom")
        String nom;

        @ColumnInfo(name = "bloc")
        boolean bloc;

        @ColumnInfo(name = "particularites")
        int[] particularites;

        @ColumnInfo(name = "hauteur")
        float hauteur;

        @ColumnInfo(name = "difficulte")
        int difficulte;

        @ColumnInfo(name = "siteId")
        int siteId;

        @ColumnInfo(name = "valide")
        boolean valide;

        @ColumnInfo(name = "note")
        float note;

        //String video;


        public Bloc(String nom, boolean bloc, int[] particularites, float hauteur, int difficulte, int siteId, boolean valide, float note) {
                this.nom = nom;
                this.bloc = bloc;
                this.particularites = particularites;
                this.hauteur = hauteur;
                this.difficulte = difficulte;
                this.siteId = siteId;
                this.valide = valide;
                this.note = note;
        }

        @Override
        public String toString() {
                return "Bloc{" +
                        "uid=" + uid +
                        ", nom='" + nom + '\'' +
                        ", bloc=" + bloc +
                        ", particularites=" + Arrays.toString(particularites) +
                        ", hauteur=" + hauteur +
                        ", difficulte=" + difficulte +
                        ", siteId=" + siteId +
                        ", valide=" + valide +
                        ", note=" + note +
                        '}';
        }

        public String getNom() {
                return nom;
        }

        public void setNom(String nom) {
                this.nom = nom;
        }

        public int getUid() {
                return uid;
        }

        public void setUid(int uid) {
                this.uid = uid;
        }

        public boolean isBloc() {
                return bloc;
        }

        public void setBloc(boolean bloc) {
                this.bloc = bloc;
        }

        public int[] getParticularites() {
                return particularites;
        }

        public void setParticularites(int[] particularites) {
                this.particularites = particularites;
        }

        public float getHauteur() {
                return hauteur;
        }

        public void setHauteur(float hauteur) {
                this.hauteur = hauteur;
        }

        public int getSiteId() {
                return siteId;
        }

        public void setSiteId(int siteId) {
                this.siteId = siteId;
        }

        public boolean isValide() {
                return valide;
        }

        public void setValide(boolean valide) {
                this.valide = valide;
        }

        public int getDifficulte() {
                return difficulte;
        }

        public void setDifficulte(int difficulte) {
                this.difficulte = difficulte;
        }

        public float getNote() {
                return note;
        }

        public void setNote(float note) {
                this.note = note;
        }
}
