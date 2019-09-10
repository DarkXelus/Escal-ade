package com.example.escalade;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Switch;

import com.example.escalade.bo.Site;
import com.example.escalade.dao.SiteDao;

public class CreationSiteActivity extends AppCompatActivity {
    private String nom;
    private String adresse;
    private float longitude;
    private float latitude;
    private String numero;
    private boolean interieur;
    private String url;
    private float note;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creation_site);

        EditText edt_nom = findViewById(R.id.creation_site_edt_nom);
        final EditText edt_adresse = findViewById(R.id.creation_site_edt_adresse);
        Switch sw_interieur = findViewById(R.id.creation_site_sw_interieur);
        final EditText edt_latitude = findViewById(R.id.creation_site_edt_latitude);
        final EditText edt_longitude = findViewById(R.id.creation_site_edt_longitude);
        final EditText edt_telephone = findViewById(R.id.creation_site_edt_telephone);
        final EditText edt_url = findViewById(R.id.creation_site_edt_url);
        RatingBar rb_note = findViewById(R.id.creation_site_rb_note);

        edt_nom.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                nom = editable.toString();
            }
        });

        edt_adresse.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                adresse = editable.toString();
            }
        });

        sw_interieur.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(compoundButton.isChecked())
                {
                    edt_latitude.getText().clear();
                    edt_latitude.setVisibility(View.INVISIBLE);
                    edt_longitude.getText().clear();
                    edt_longitude.setVisibility(View.INVISIBLE);
                    edt_telephone.setVisibility(View.VISIBLE);
                }
                else
                {
                    edt_latitude.setVisibility(View.VISIBLE);
                    edt_longitude.setVisibility(View.VISIBLE);
                    edt_telephone.setVisibility(View.INVISIBLE);
                    edt_telephone.getText().clear();
                }
                interieur = compoundButton.isChecked();
            }
        });

        edt_longitude.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(editable != null)
                {
                    longitude = Float.parseFloat(editable.toString());
                }
            }
        });

        edt_latitude.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(editable != null)
                {
                    latitude = Float.parseFloat(editable.toString());
                }
            }
        });

        edt_telephone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        edt_url.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                url = editable.toString();
            }

        });

        rb_note.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                note = v;
            }
        });
    }
    private boolean enr;

    @Override
    protected void onDestroy() {
        if(enr)
        {
            AppDatabase connexion = Connexion.getConnexion(this);
            connexion.siteDao().insertAll(new Site(nom,adresse,longitude,latitude,url,numero,interieur,note));
        }
        super.onDestroy();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK){

            if(!nom.isEmpty()
                    && !adresse.isEmpty())
            {
                enr = true;
                return super.onKeyDown(keyCode, event);
            }
            else
            {
                return false;
            }
        }
        return false;

    }
}
