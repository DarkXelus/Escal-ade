package com.example.escalade.ui.voie;

import android.content.Context;
import android.content.Intent;
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
import com.example.escalade.CreationBlocActivity;
import com.example.escalade.CreationSiteActivity;
import com.example.escalade.DetailBlocActivity;
import com.example.escalade.MainActivity;
import com.example.escalade.R;
import com.example.escalade.adapter.BlocsAdapter;
import com.example.escalade.bo.Bloc;
import com.example.escalade.dao.BlocDao;
import com.example.escalade.ui.bloc.BlocFragment;
import com.example.escalade.ui.bloc.BlocViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class VoieFragment extends Fragment {

     public static final String KEY_ARTICLE = "KEY_ARTICLE";
    private BlocViewModel blocsViewModel;
    List<Bloc> blocArrayList;
    BlocsAdapter adapter;
    BlocDao dao;
    private View root;
    private RecyclerView recyclerView;
    OnReloadListListener listListener;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        blocsViewModel =
                ViewModelProviders.of(this).get(BlocViewModel.class);
        root = inflater.inflate(R.layout.fragment_bloc, container, false);
        blocArrayList = new ArrayList<>();
        dao = Connexion.getConnexion(getActivity()).blocDao();
        new Thread(new Runnable() {
            @Override
            public void run() {
                AppDatabase connexion = Connexion.getConnexion(getActivity());
                blocArrayList =  connexion.blocDao().getAllByVoie();
                adapter.setBlocArrayList(blocArrayList,VoieFragment.this.getActivity());
            }
        }).start();

        recyclerView = root.findViewById(R.id.fragment_bloc_rc_bloc);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        adapter = new BlocsAdapter(blocArrayList, new BlocsAdapter.OnBlocClickListener() {
            @Override
            public void onBlocClick(Bloc bloc) {
                Intent intentToDetail = new Intent(
                        VoieFragment.this.getActivity(), DetailBlocActivity.class);
                intentToDetail.putExtra(KEY_ARTICLE,bloc);
                startActivity(intentToDetail);
            }
        });
        recyclerView.setAdapter(adapter);

        final FloatingActionButton fab = root.findViewById(R.id.bloc_fab_add);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), CreationBlocActivity.class);
                startActivity(i);
            }
        });

        return root;
    }

    public void reloadList(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                adapter.setBlocArrayList(dao.getAllByVoie(),getActivity());
            }
        }).start();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnReloadListListener) {
            listListener = (OnReloadListListener) context;
            listListener.onReload(this);
        }

    }

    public interface OnReloadListListener{
        void onReload(VoieFragment fragment);
    }
}