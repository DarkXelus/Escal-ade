package com.example.escalade;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.escalade.bo.Site;
import com.example.escalade.ui.site.SiteFragment;

import org.osmdroid.api.IMapController;
import org.osmdroid.config.Configuration;
import org.osmdroid.events.MapListener;
import org.osmdroid.events.ScrollEvent;
import org.osmdroid.events.ZoomEvent;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.mylocation.GpsMyLocationProvider;
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay;

import java.net.URI;

import static android.content.pm.PackageManager.PERMISSION_GRANTED;

public class DetailSiteActivity extends AppCompatActivity  {
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
        setSupportActionBar((Toolbar)findViewById(R.id.detail_site_tl));

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
    protected void onRestart() {
        super.onRestart();
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        AppDatabase connexion = Connexion.getConnexion(DetailSiteActivity.this);
                        site = connexion.siteDao().findById(site.getUid());
                        nom.setText(site.getNom());
                        adresse.setText(site.getAdresse());
                        note.setRating(site.getNote());
                        ActivityCompat.requestPermissions(DetailSiteActivity.this, new String[]{
                                Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION,
                                Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CODE);

                    }
                }).start();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_detail,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.menu_detail_item_modification){

            Intent intentToDetail = new Intent(
                    this, CreationSiteActivity.class);
            intentToDetail.putExtra(KEY_ARTICLE,site);
            startActivity(intentToDetail);
            return true;
        }
        if(item.getItemId() == R.id.menu_detail_item_url){
            if(site.getUrlSite() != null && site.getUrlSite() != "") {

                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(site.getUrlSite()));
                startActivity(intent);
            }
           return true;
        }
        return false;
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

            final IMapController controller = mv.getController();
            Toast.makeText(DetailSiteActivity.this, site.getLatitude()+","+site.getLongitude(), Toast.LENGTH_SHORT).show();

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //controller.setCenter(new GeoPoint(site.getLatitude(),site.getLongitude()));
                            controller.animateTo(new GeoPoint(site.getLatitude(),site.getLongitude()));
                        }
                    });
                }
            }).start();


            controller.setZoom(18.0);




        }
    }
}
