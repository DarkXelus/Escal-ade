package com.example.escalade;

import android.os.Bundle;



import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.escalade.ui.bloc.BlocFragment;
import com.example.escalade.ui.site.SiteFragment;
import com.example.escalade.ui.voie.VoieFragment;
import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;

public class MainActivity extends AppCompatActivity implements SiteFragment.OnReloadListListener, BlocFragment.OnReloadListListener, VoieFragment.OnReloadListListener {

    NavController navController;
    SiteFragment siteFragment;
    BlocFragment blocFragment;
    VoieFragment voieFragment;

    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.creation_bloc_tl);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_bloc, R.id.nav_voie,
                R.id.nav_site)
                .setDrawerLayout(drawer)
                .build();
        navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        if (siteFragment != null) {
            siteFragment.reloadList();
        } else if (blocFragment != null) {
            blocFragment.reloadList();
        } else {
            voieFragment.reloadList();
        }
    }

    @Override
    public void onReload(SiteFragment fragment) {
        siteFragment = fragment;
    }

    @Override
    public void onReload(BlocFragment fragment) {
        blocFragment = fragment;
    }

    @Override
    public void onReload(VoieFragment fragment) {
        voieFragment = fragment;
    }
}
