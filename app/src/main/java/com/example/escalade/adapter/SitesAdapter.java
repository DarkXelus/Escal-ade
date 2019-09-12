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
import com.example.escalade.bo.Site;

import java.util.List;

public class SitesAdapter extends RecyclerView.Adapter<SitesAdapter.SitesViewHolder>  {
    List<Site> siteArrayList;
    OnSiteClickListener listener;

    public class SitesViewHolder extends RecyclerView.ViewHolder{


        private final TextView tv_nom;
        private final RatingBar rb_note;
        private final ImageView iv_interieur;

        public SitesViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_nom = itemView.findViewById(R.id.element_list_site_tv_nom);
            rb_note = itemView.findViewById(R.id.element_list_site_rb_note);
            iv_interieur = itemView.findViewById(R.id.element_list_site_iv_interieur);
        }
    }

    public SitesAdapter(List<Site> siteArrayList, OnSiteClickListener listener) {
        this.siteArrayList = siteArrayList;
        this.listener = listener;
    }



    @NonNull
    @Override
    public SitesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater li = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        return new SitesViewHolder(li.inflate(R.layout.element_liste_site,parent,false));
    }
    
    @Override
    public void onBindViewHolder(@NonNull SitesViewHolder holder, int position) {
        final Site siteAAfficher = siteArrayList.get(position);
        holder.tv_nom.setText(siteAAfficher.getNom());
        holder.rb_note.setRating(siteAAfficher.getNote());

        if(siteAAfficher.isInterieur())
        {
            holder.iv_interieur.setImageResource(R.drawable.climb);
        }
        else
        {
            holder.iv_interieur.setImageResource(R.drawable.mountains);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onSiteClick(siteAAfficher);
            }
        });
    }

    @Override
    public int getItemCount() {
        return siteArrayList.size();
    }

    public interface OnSiteClickListener{
        void onSiteClick(Site site);
    }

    public void setSiteArrayList(List<Site> siteArrayList, Activity activity) {
        this.siteArrayList = siteArrayList;
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                notifyDataSetChanged();
            }
        });
    }


}
