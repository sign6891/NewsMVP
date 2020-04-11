package com.example.newsmvp.detailed;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.newsmvp.R;

public class DetailPresenter {

    private DetailView detailView;
    private Context context;


    public DetailPresenter(DetailView detailView, Context context) {
        this.detailView = detailView;
        this.context = context;
    }

    public void loadImage(String url, ImageView detailImageView) {
        if (!url.equals("NonNull")) {

            Glide.with(context)
                    .load(url)
                    .listener(new RequestListener<Drawable>() {
                        @Override
                        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                             detailView.setProgressBar();
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                            detailView.setProgressBar();
                            return false;
                        }
                    })
                    .into(detailImageView);
        } else {
            detailImageView.setImageResource(R.mipmap.logo);
        }
    }

    public String removeDate(String date) {
        date = date.substring(0, 10);
        return date;
    }
}
