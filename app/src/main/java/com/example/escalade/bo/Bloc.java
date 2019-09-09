package com.example.escalade.bo;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.escalade.Enum.Particularite;

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
        List<Particularite> particularites;

        @ColumnInfo(name = "hauteur")
        float hauteur;

        @ColumnInfo(name = "difficulte")
        String difficulte;

        @ColumnInfo(name = "siteId")
        int siteId;

        @ColumnInfo(name = "valide")
        boolean valide;

        //String video;


        public Bloc(String nom, boolean bloc, List<Particularite> particularites,
                    float hauteur, String difficulte, int siteId,boolean valide) {
                this.nom = nom;
                this.bloc = bloc;
                this.particularites = particularites;
                this.hauteur = hauteur;
                this.difficulte = difficulte;
                this.siteId = siteId;
                this.valide = valide;
        }

        @Override
        public String toString() {
                return "Bloc{" +
                        "uid=" + uid +
                        ", nom='" + nom + '\'' +
                        ", bloc=" + bloc +
                        ", particularites=" + particularites +
                        ", hauteur=" + hauteur +
                        ", difficulte='" + difficulte + '\'' +
                        ", site=" + siteId +
                        ", valide=" + valide +
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

        public List<Particularite> getParticularites() {
                return particularites;
        }

        public void setParticularites(List<Particularite> particularites) {
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

        public String getDifficulte() {
                return difficulte;
        }

        public void setDifficulte(String difficulte) {
                this.difficulte = difficulte;
        }
}
