package com.example.recyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.ViewHolder> {
    Context context;
    String[] title;
    String[] des;
    int[] img;
    public static class ViewHolder extends RecyclerView.ViewHolder{
             TextView TV1;
             TextView TV2;
             ImageView IV;
             public ViewHolder(@NonNull View itemView){
                 super(itemView);
                 TV1 = itemView.findViewById(R.id.caption);
                 TV2 = itemView.findViewById(R.id.description);
                 IV = itemView.findViewById(R.id.img);
             }
    }
    public RecycleAdapter(Context context, String[] title, String[] des, int[] img){
        this.context = context;
        this.title = title;
        this.des= des;
        this.img = img;
    }
    @NonNull
    @Override
    public RecycleAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_layout,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecycleAdapter.ViewHolder holder, int position) {
    holder.TV1.setText(title[position]);
    holder.TV2.setText(des[position]);
    holder.IV.setImageResource(img[position]);
    }
    
    @Override
    public int getItemCount() {
        return title.length;
    }
}
