package com.example.escalade;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Thread.sleep;

public class MainActivity extends AppCompatActivity {
    public static final String[] tabDificulte = new String[]{"3","3+","4a","4b","4c","5a","5b","5c","6a","6a+","6b","6b+"
            ,"6c","6c+","7a","7a+","7b","7b+","7c","7c+","8a","8a+","8b","8b+","8c","8c+","9a","9a+","9b","9b+","9c","9c+"};
    public static final String[] tabParticularite = new String[]{
            "dever","toit","dalle","reglette","fissure","no_foot","jetee"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTheme(R.style.Theme_AppCompat_Light_Dialog);



    }
}
