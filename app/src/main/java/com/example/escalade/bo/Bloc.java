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

        @ColumnInfo(name = "bloc")
        boolean bloc;

        @ColumnInfo(name = "particularites")
        List<Particularite> particularites;

        @ColumnInfo(name = "hauteur")
        float hauteur;

        List<String> difficultes;

        @ColumnInfo(name = "difficulte")
        String difficulte;

        @ColumnInfo(name = "site")
        Site site;

        @ColumnInfo(name = "exterieur")
        boolean exterieur;

        @ColumnInfo(name = "valide")
        boolean valide;


        //String video;


        public Bloc(boolean bloc, List<Particularite> particularites,String difficulte, float hauteur, Site site, boolean exterieur, boolean valide) {
                difficultes.add("3");
                difficultes.add("3+");
                difficultes.add("4a");
                difficultes.add("4b");
                difficultes.add("4c");
                difficultes.add("5a");
                difficultes.add("5b");
                difficultes.add("5c");
                difficultes.add("6a");
                difficultes.add("6a+");
                difficultes.add("6b");
                difficultes.add("6b+");
                difficultes.add("6c");
                difficultes.add("6c+");
                difficultes.add("7a");
                difficultes.add("7a+");
                difficultes.add("7b");
                difficultes.add("7b+");
                difficultes.add("7c");
                difficultes.add("7c+");
                difficultes.add("8a");
                difficultes.add("8a+");
                difficultes.add("8b");
                difficultes.add("8b+");
                difficultes.add("8c");
                difficultes.add("8c+");
                difficultes.add("9a");
                difficultes.add("9a+");
                difficultes.add("9b");
                difficultes.add("9b+");
                difficultes.add("9c");
                difficultes.add("9c+");

                this.bloc = bloc;
                this.particularites = particularites;
                this.hauteur = hauteur;
                this.difficulte = difficulte;
                this.site = site;
                this.exterieur = exterieur;
                this.valide = valide;
        }

        @Override
        public String toString() {
                return "Bloc{" +
                        "uid=" + uid +
                        ", bloc=" + bloc +
                        ", particularites=" + particularites +
                        ", hauteur=" + hauteur +
                        ", difficulte=" + difficulte +
                        ", site=" + site +
                        ", exterieur=" + exterieur +
                        ", valide=" + valide +
                        '}';
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


        public Site getSite() {
                return site;
        }

        public void setSite(Site site) {
                this.site = site;
        }

        public boolean isExterieur() {
                return exterieur;
        }

        public void setExterieur(boolean exterieur) {
                this.exterieur = exterieur;
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
