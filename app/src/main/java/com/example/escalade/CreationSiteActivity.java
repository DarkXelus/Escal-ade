package com.example.escalade;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.room.Room;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Switch;
import android.widget.Toast;

import com.example.escalade.bo.Site;
import com.example.escalade.dao.SiteDao;

import org.osmdroid.api.IMapController;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;

import static android.content.pm.PackageManager.PERMISSION_GRANTED;

public class CreationSiteActivity extends AppCompatActivity {
    public static final int REQUEST_CODE = 402;
    private String nom;
    private String adresse;
    private float longitude;
    private float latitude;
    private String numero;
    private boolean interieur;
    private String url;
    private float note;
    private EditText edt_latitude;
    private EditText edt_longitude;
    private Button btn_position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creation_site);
        setSupportActionBar((Toolbar)findViewById(R.id.creation_site_tl));

        EditText edt_nom = findViewById(R.id.creation_site_edt_nom);
        final EditText edt_adresse = findViewById(R.id.creation_site_edt_adresse);
        Switch sw_interieur = findViewById(R.id.creation_site_sw_interieur);
        edt_latitude = findViewById(R.id.creation_site_edt_latitude);
        edt_longitude = findViewById(R.id.creation_site_edt_longitude);
        final EditText edt_telephone = findViewById(R.id.creation_site_edt_telephone);
        final EditText edt_url = findViewById(R.id.creation_site_edt_url);
        btn_position = findViewById(R.id.creation_site_btn_position);
        RatingBar rb_note = findViewById(R.id.creation_site_rb_note);

        ActivityCompat.requestPermissions(this, new String[]{
                Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION}, REQUEST_CODE);

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
                if (compoundButton.isChecked()) {
                    edt_latitude.setText("");//.clear();
                    edt_latitude.setVisibility(View.INVISIBLE);
                    edt_longitude.setText("");
                   // edt_longitude.getText().clear();
                    edt_longitude.setVisibility(View.INVISIBLE);
                    edt_telephone.setVisibility(View.VISIBLE);
                } else {
                    edt_latitude.setVisibility(View.VISIBLE);
                    edt_longitude.setVisibility(View.VISIBLE);
                    edt_telephone.setVisibility(View.INVISIBLE);
                    edt_telephone.getText().clear();
                }
                interieur = compoundButton.isChecked();
            }
        });

        btn_position.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (ActivityCompat.checkSelfPermission(CreationSiteActivity.this,Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
                        ActivityCompat.checkSelfPermission(CreationSiteActivity.this,Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

                    LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                    Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                    edt_latitude.setText(String.valueOf(location.getLatitude()));
                    edt_longitude.setText(String.valueOf(location.getLongitude()));
                }
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
                if (editable != null) {
                    longitude = Float.parseFloat(editable.toString().isEmpty() ? "0.00":editable.toString());
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
                if (editable != null) {
                    String test = editable.toString();
                    latitude = Float.parseFloat(editable.toString().isEmpty() ? "0.00":editable.toString());
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
                    numero = editable.toString();
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
        if (enr) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    AppDatabase connexion = Connexion.getConnexion(CreationSiteActivity.this);
                    connexion.siteDao().insertAll(new Site(nom, adresse, longitude, latitude, url, numero, interieur, note));
                }
            }).start();
        }
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_creation,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.menu_creation_annuler){
            this.finish();
            return true;
        }
        return false;
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {

            if (nom != null
                    && adresse != null) {
                enr = true;
                Toast.makeText(this, "Site enregistrer", Toast.LENGTH_SHORT).show();
                return super.onKeyDown(keyCode, event);
            }
            else
            {
                Toast.makeText(this, "Annulation", Toast.LENGTH_SHORT).show();
            }
        }

        return false;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CODE && grantResults.length > 0 && grantResults[0] == PERMISSION_GRANTED && grantResults[1] == PERMISSION_GRANTED) {
            btn_position.setEnabled(true);
        }
    }
}
