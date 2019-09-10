package com.example.escalade.ui.bloc;

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

public class BlocFragment extends Fragment {

    private BlocViewModel blocViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        blocViewModel =
                ViewModelProviders.of(this).get(BlocViewModel.class);
        View root = inflater.inflate(R.layout.fragment_bloc, container, false);
        final TextView textView = root.findViewById(R.id.text_gallery);
        blocViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}