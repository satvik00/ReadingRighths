package com.example.readingrighths;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.util.List;

public class mAdapter extends RecyclerView.Adapter<mAdapter.ViewHolder> {
    LayoutInflater inflater;
    Context c;
    List<food> meal;
    public mAdapter(Context context, List<food> f){
        this.inflater=LayoutInflater.from(context);
        c=context;
        this.meal=f;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vh=inflater.inflate(R.layout.food_item,parent,false);
        return new ViewHolder(vh);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.name.setText(meal.get(position).getName());
        holder.category.setText(meal.get(position).getCategory());
        Glide.with(c)
                .load(meal.get(position).getImageUrl())
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        return false;
                    }
                }).into(holder.iv);
    }

    @Override
    public int getItemCount() {
        return meal.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView name,category;
        ImageView iv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.name);
            category=itemView.findViewById(R.id.category);
            iv=itemView.findViewById(R.id.img);
        }
    }
}
