package com.example.minimo2;
import android.content.Context;
import android.renderscript.Element;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.minimo2.GithubRepos;

import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> {
    List<GithubRepos> repositorios = new ArrayList<>();
    Context context;


    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView rep;
        public TextView len;

        public ViewHolder(View itemLayoutView) {
            super(itemLayoutView);
            rep = (TextView) itemLayoutView.findViewById(R.id.repositorio);
            len = (TextView) itemLayoutView.findViewById(R.id.lenguaje);
        }
    }

    public MyRecyclerViewAdapter(List<GithubRepos> followers, Context context) {
        this.repositorios = followers;
        this.context = context;
    }

    @NotNull
    @Override
    public MyRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemLayoutView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_row, null);

        ViewHolder viewHolder = new ViewHolder(itemLayoutView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        viewHolder.rep.setText(repositorios.get(position).getName());
        viewHolder.len.setText(repositorios.get(position).getLanguage());
    }

    @Override
    public int getItemCount() {
        return repositorios.size();
    }


}