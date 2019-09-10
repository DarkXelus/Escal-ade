package com.example.escalade.ui.site;

import android.content.Intent;
import android.content.ReceiverCallNotAllowedException;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.escalade.AppDatabase;
import com.example.escalade.Connexion;
import com.example.escalade.CreationSiteActivity;
import com.example.escalade.DetailSiteActivity;
import com.example.escalade.MainActivity;
import com.example.escalade.R;
import com.example.escalade.adapter.SitesAdapter;
import com.example.escalade.bo.Site;
import com.example.escalade.dao.SiteDao;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class SiteFragment extends Fragment {

    public static final String KEY_ARTICLE = "KEY_ARTICLE";
    private SitesViewModel sitesViewModel;
    List<Site> siteArrayList;
    SitesAdapter adapter;
    SiteDao dao;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        sitesViewModel =
                ViewModelProviders.of(this).get(SitesViewModel.class);
        View root = inflater.inflate(R.layout.fragment_site, container, false);
        siteArrayList = new ArrayList<>();
        new Thread(new Runnable() {
            @Override
            public void run() {
                AppDatabase connexion = Connexion.getConnexion(getActivity());
                siteArrayList =  connexion.siteDao().getAll();
                adapter.setSiteArrayList(siteArrayList,SiteFragment.this.getActivity());
            }
        }).start();

        RecyclerView recyclerView = root.findViewById(R.id.fragment_site_rc_sites);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


        adapter = new SitesAdapter(siteArrayList, new SitesAdapter.OnSiteClickListener() {
            @Override
            public void onSiteClick(Site site) {
                Intent intentToDetail = new Intent(
                        SiteFragment.this.getActivity(), DetailSiteActivity.class);
                intentToDetail.putExtra(KEY_ARTICLE,site);
                startActivity(intentToDetail);
            }
        });
        recyclerView.setAdapter(adapter);

        final FloatingActionButton fab = root.findViewById(R.id.site_fab_add);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), CreationSiteActivity.class);
                startActivity(i);
            }
        });

        return root;
    }
}