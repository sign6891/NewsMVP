package com.example.newsmvp.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.newsmvp.R;
import com.example.newsmvp.model.Result;

import java.io.IOException;
import java.util.ArrayList;

import static com.example.newsmvp.R.drawable.logo_activity;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder>{

    private Context context;
    private ArrayList<Result> resultArrayList;

    public NewsAdapter(Context context, ArrayList<Result> resultArrayList) {
        this.context = context;
        this.resultArrayList = resultArrayList;
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news,
                parent, false);
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {

        holder.item_category.setText(resultArrayList.get(position).getSection());
        holder.item_title.setText(resultArrayList.get(position).getTitle());
        holder.item_preview.setText(resultArrayList.get(position).getAbstract());

        String publishedDate =  resultArrayList.get(position).getPublishedDate();
        publishedDate = publishedDate.substring(0, 10);
        holder.item_date.setText(publishedDate);

        try {
            String image = resultArrayList.get(position).getMultimedia().get(2).getUrl();
            Glide.with(context)
                    .load(image)
                    .into(holder.item_image);
        } catch (NullPointerException e) {
            holder.item_image.setImageResource(R.mipmap.logo);
        }


    }

    @Override
    public int getItemCount() {
        return resultArrayList.size();
    }

    public class NewsViewHolder extends RecyclerView.ViewHolder {

        ImageView item_image;
        TextView item_category;
        TextView item_title;
        TextView item_preview;
        TextView item_date;


        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);

            item_category = itemView.findViewById(R.id.item_category);
            item_title = itemView.findViewById(R.id.item_title);
            item_preview = itemView.findViewById(R.id.item_preview);
            item_date = itemView.findViewById(R.id.item_date);
            item_image = itemView.findViewById(R.id.item_image);

        }
    }
}
