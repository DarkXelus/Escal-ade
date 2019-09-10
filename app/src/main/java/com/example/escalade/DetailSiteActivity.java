package com.example.escalade;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.escalade.bo.Site;

public class DetailSiteActivity extends AppCompatActivity {
    public static final String KEY_ARTICLE = "KEY_ARTICLE";

    private Site site;
    private TextView nom;
    private TextView adresse;
    private RatingBar note;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_site);

        site = getIntent().getParcelableExtra(KEY_ARTICLE);
        nom = findViewById(R.id.detail_site_tv_nom);
        adresse = findViewById(R.id.detail_site_tv_adresse);
        note = findViewById(R.id.detail_site_rb_note);

        nom.setText(site.getNom());
        adresse.setText(site.getAdresse());
        note.setRating(site.getNote());

    }
}
