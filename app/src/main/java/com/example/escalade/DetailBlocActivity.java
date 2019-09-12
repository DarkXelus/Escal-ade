package com.example.escalade;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.escalade.bo.Bloc;
import com.example.escalade.bo.Site;

import org.osmdroid.config.Configuration;

public class DetailBlocActivity extends AppCompatActivity {
    public static final String KEY_ARTICLE = "KEY_ARTICLE";
    public static final int REQUEST_CODE = 454;

    private Bloc bloc;
    private TextView nom;
    private TextView hauteur;
    private TextView difficulte;
    private TextView site;
    private ImageView img;
    private RatingBar note;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_bloc);

        bloc = getIntent().getParcelableExtra(KEY_ARTICLE);
        nom = findViewById(R.id.detail_site_tv_nom);
        hauteur = findViewById(R.id.detail_bloc_tv_hauteur);
        difficulte = findViewById(R.id.detail_bloc_tv_difficulte);
        site = findViewById(R.id.detail_bloc_tv_site);
        note = findViewById(R.id.detail_site_rb_note);
        img = findViewById(R.id.detail_bloc_iv_bloc);

        nom.setText(bloc.getNom());
        hauteur.setText(String.valueOf(bloc.getHauteur()));
        difficulte.setText(String.valueOf(bloc.getDifficulte()));
        new Thread(new Runnable() {
            @Override
            public void run() {
                AppDatabase connexion = Connexion.getConnexion(DetailBlocActivity.this);
                Site siterecup = connexion.siteDao().get(bloc.getUid());
                site.setText(siterecup.getNom());
            }
        }).start();


        note.setRating(bloc.getNote());

        if (bloc.isBloc() == false) {
            img.setImageResource(R.drawable.climb);
        } else {
            img.setImageResource(R.drawable.mountains);
        }

        Configuration.getInstance().load(this, PreferenceManager.getDefaultSharedPreferences(this));
    }



}
