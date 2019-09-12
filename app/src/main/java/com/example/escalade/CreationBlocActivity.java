package com.example.escalade;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import com.example.escalade.bo.Bloc;
import com.example.escalade.bo.Site;
import java.util.ArrayList;
import java.util.List;


public class CreationBlocActivity extends AppCompatActivity implements View.OnClickListener{

    String nom;
    boolean bloc;
    boolean checkValue_dever;
    boolean checkValue_toit;
    boolean checkValue_dalle;
    boolean checkValue_reglette;
    boolean checkValue_fissure;
    boolean checkValue_no_foot;
    boolean checkValue_jetee;
    int[] particularites = {0,0,0,0,0,0,0};
    float hauteur;
    int difficulte;
    int siteId;
    boolean valide;
    float note;
    boolean enr;
    private List<Site> listSite;
    String siteValue;

    CheckBox checkBox_dever;
    CheckBox checkBox_toit;
    CheckBox checkBox_dalle;
    CheckBox checkBox_reglette;
    CheckBox checkBox_fissure;
    CheckBox checkBox_no_foot;
    CheckBox checkBox_jetee;
    CheckBox checkBoxValider;
    RatingBar rb_note;
    private Spinner spinnerListSite;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creation_bloc);
        setSupportActionBar((Toolbar)findViewById(R.id.creation_bloc_tl));

        //Instancie les controllers
        EditText nameBloc = findViewById(R.id.creation_bloc_edt_nom);
        Switch switchBloc = findViewById(R.id.creation_bloc_sw_bloc);
        checkBox_dever = findViewById(R.id.creation_check_dever);
        checkBox_toit = findViewById(R.id.creation_check_toit);
        checkBox_dalle = findViewById(R.id.creation_check_dalle);
        checkBox_reglette = findViewById(R.id.creation_check_reglette);
        checkBox_fissure = findViewById(R.id.creation_check_fissure);
        checkBox_no_foot = findViewById(R.id.creation_check_no_foot);
        checkBox_jetee = findViewById(R.id.creation_check_jetee);
        checkBoxValider = findViewById(R.id.creation_valider_check);
        rb_note = findViewById(R.id.creation_bloc_rb_note);


        EditText editHauteur = findViewById(R.id.creation_hauteur);
        Spinner spinnerDifficulte = findViewById(R.id.creation_spinner_difficulte);
        spinnerListSite = findViewById(R.id.creation_spinner_list_site);

        //Spinner Difficulté
        ArrayAdapter<CharSequence> adapter  = ArrayAdapter.createFromResource(this,R.array.array_difficulte, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDifficulte.setAdapter(adapter);

        //Spinner ListSite
        new Thread(new Runnable() {
            @Override
            public void run() {
                AppDatabase connexion = Connexion.getConnexion(CreationBlocActivity.this);
                listSite = connexion.siteDao().getAll();

                ArrayList<String> siteNames = new ArrayList<String>();

                for (int i = 0; i < listSite.size(); i++) {
                    siteNames.add(listSite.get(i).getNom());
                }

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                        CreationBlocActivity.this,
                        android.R.layout.simple_spinner_item,
                        siteNames
                );

                spinnerListSite.setAdapter(adapter);
            }
        }).start();

        checkBox_dever.setOnClickListener(this);
        checkBox_toit.setOnClickListener(this);
        checkBox_dalle.setOnClickListener(this);
        checkBox_reglette.setOnClickListener(this);
        checkBox_fissure.setOnClickListener(this);
        checkBox_no_foot.setOnClickListener(this);
        checkBox_jetee.setOnClickListener(this);
        checkBoxValider.setOnClickListener(this);

        //Récupère Valeur
        nameBloc.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) { }
            @Override
            public void afterTextChanged(Editable editable) {
                nom = editable.toString();
            }
        });

        switchBloc.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    bloc = isChecked;
                }
        });

        editHauteur.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) { }
            @Override
            public void afterTextChanged(Editable editable) {
                hauteur = Float.parseFloat(editable.toString());
            }
        });

        difficulte = Integer.valueOf(spinnerDifficulte.getSelectedItem().toString());

        rb_note.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                note = v;
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.creation_check_dever:
                checkBox_dever = findViewById(R.id.creation_check_dever);
                checkValue_dever = checkBox_dever.isChecked();
                break;
            case R.id.creation_check_toit:
                checkBox_toit = findViewById(R.id.creation_check_toit);
                checkValue_toit = checkBox_toit.isChecked();
                break;
            case R.id.creation_check_dalle:
                checkBox_dalle = findViewById(R.id.creation_check_dalle);
                checkValue_dalle = checkBox_dalle.isChecked();
                break;
            case R.id.creation_check_reglette:
                checkBox_reglette = findViewById(R.id.creation_check_reglette);
                checkValue_reglette = checkBox_reglette.isChecked();
                break;
            case R.id.creation_check_fissure:
                checkBox_fissure = findViewById(R.id.creation_check_fissure);
                checkValue_fissure = checkBox_fissure.isChecked();
                break;
            case R.id.creation_check_no_foot:
                checkBox_no_foot = findViewById(R.id.creation_check_no_foot);
                checkValue_no_foot = checkBox_no_foot.isChecked();
                break;
            case R.id.creation_check_jetee:
                checkBox_jetee = findViewById(R.id.creation_check_jetee);
                checkValue_jetee = checkBox_jetee.isChecked();
                break;
            case R.id.creation_valider_check:
                checkBoxValider = findViewById(R.id.creation_valider_check);
                valide = checkBoxValider.isChecked();
        }
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
        if(item.getItemId() == R.id.menu_creation_enregistrer){
            if (nom != null) {
                enr = true;
                saveInDB();
                return true;
            }
            else
            {
                Toast.makeText(this, "Le champs nom doit être renseigné", Toast.LENGTH_SHORT).show();
            }
        }
        return false;
    }


    public void saveInDB(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                AppDatabase connexion = Connexion.getConnexion(CreationBlocActivity.this);

                if (checkValue_dever == true) {
                    particularites[0] = 1;
                }
                if (checkValue_toit == true) {
                    particularites[1] = 1;
                }
                if (checkValue_dalle == true) {
                    particularites[2] = 1;
                }
                if (checkValue_reglette == true) {
                    particularites[3] = 1;
                }
                if (checkValue_fissure == true) {
                    particularites[4] = 1;
                }
                if (checkValue_no_foot == true) {
                    particularites[5] = 1;
                }
                if (checkValue_jetee == true) {
                    particularites[6] = 1;
                }
                siteValue = spinnerListSite.getSelectedItem().toString();
                for (Site site: listSite) {
                    if (site.getNom() == siteValue) {
                        siteId = site.getUid();
                    }
                }
                List<Long> list = connexion.blocDao().insertAll(new Bloc(nom,bloc,particularites,hauteur,difficulte,siteId,valide,note));
                CreationBlocActivity.this.finish();

            }
        }).start();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            saveInDB();
            return true;
        }

        return false;
    }
}
