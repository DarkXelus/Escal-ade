package com.example.escalade.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.escalade.R;
import com.example.escalade.bo.Bloc;

import java.util.List;

public class BlocsAdapter extends RecyclerView.Adapter<BlocsAdapter.BlocsViewHolder>{
    List<Bloc> blocArrayList;
    BlocsAdapter.OnBlocClickListener listener;

    public class BlocsViewHolder extends RecyclerView.ViewHolder{


        private final TextView tv_nom;
        private final RatingBar rb_note;
        private final TextView tv_difficulte;

        public BlocsViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_nom = itemView.findViewById(R.id.element_list_bloc_tv_nom);
            rb_note = itemView.findViewById(R.id.element_list_bloc_rb_note);
            tv_difficulte = itemView.findViewById(R.id.element_list_bloc_tv_difficulte);
        }
    }

    public BlocsAdapter(List<Bloc> blocArrayList, BlocsAdapter.OnBlocClickListener listener) {
        this.blocArrayList = blocArrayList;
        this.listener = listener;
    }


    @NonNull
    @Override
    public BlocsAdapter.BlocsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater li = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        return new BlocsAdapter.BlocsViewHolder(li.inflate(R.layout.element_liste_bloc,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull BlocsAdapter.BlocsViewHolder holder, int position) {
        final Bloc blocAAfficher = blocArrayList.get(position);
        holder.tv_nom.setText(blocAAfficher.getNom());
        holder.rb_note.setRating(blocAAfficher.getNote());
        holder.tv_difficulte.setText(String.valueOf(blocAAfficher.getDifficulte()));
        
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onBlocClick(blocAAfficher);
            }
        });
    }

    @Override
    public int getItemCount() {
        return blocArrayList.size();
    }

    public interface OnBlocClickListener{
        void onBlocClick(Bloc bloc);
    }

    public void setBlocArrayList(List<Bloc> blocArrayList, Activity activity) {
        this.blocArrayList = blocArrayList;
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                notifyDataSetChanged();
            }
        });
    }


}
