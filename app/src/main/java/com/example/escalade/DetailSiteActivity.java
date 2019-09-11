package com.example.escalade;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.escalade.bo.Site;

import org.osmdroid.api.IMapController;
import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.mylocation.GpsMyLocationProvider;
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay;

import static android.content.pm.PackageManager.PERMISSION_GRANTED;

public class DetailSiteActivity extends AppCompatActivity{
    public static final String KEY_ARTICLE = "KEY_ARTICLE";
    public static final int REQUEST_CODE = 454;

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

        Configuration.getInstance().load(this, PreferenceManager.getDefaultSharedPreferences(this));

        ActivityCompat.requestPermissions(this, new String[]{
                Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CODE);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode==REQUEST_CODE && grantResults.length>0 && grantResults[0] == PERMISSION_GRANTED && grantResults[1] == PERMISSION_GRANTED
                && grantResults[2] == PERMISSION_GRANTED)
        {
            final MapView mv = findViewById(R.id.mapView);
            mv.setTileSource(TileSourceFactory.MAPNIK);
            mv.setBuiltInZoomControls(true);
            mv.setMultiTouchControls(true);

            IMapController controller = mv.getController();
            Toast.makeText(DetailSiteActivity.this, site.getLatitude()+","+site.getLongitude(), Toast.LENGTH_SHORT).show();
            controller.setCenter(new GeoPoint(site.getLatitude(),site.getLongitude()));
            controller.animateTo(new GeoPoint(site.getLatitude(),site.getLongitude()));

            controller.setZoom(18.0);




        }
    }
}
