package com.example.sqlite;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public  class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    Context context;
    ArrayList username;
    ArrayList password;
    @NonNull
    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.single_row,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;


    }
    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView textView1;
        TextView textView2;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView1 = itemView.findViewById(R.id.username);
            textView2 = itemView.findViewById(R.id.password);
        }
    }
    public RecyclerAdapter(Context context, ArrayList username, ArrayList password){
        this.context = context;
        this.username = username;
        this.password = password;

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.ViewHolder holder, int position) {
        holder.textView1.setText(String.valueOf(username.get(position)));
        holder.textView2.setText(String.valueOf(password.get(position)));

    }

    @Override
    public int getItemCount() {

        return username.size();
    }
}


