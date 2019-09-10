package com.example.escalade.ui.voie;

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

import com.example.escalade.R;

public class VoieFragment extends Fragment {

    private VoieViewModel voieViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        voieViewModel =
                ViewModelProviders.of(this).get(VoieViewModel.class);
        View root = inflater.inflate(R.layout.fragment_voie, container, false);
        final TextView textView = root.findViewById(R.id.text_slideshow);
        voieViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}