package com.example.newsmvp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.newsmvp.model.Result;

public class DetailActivity extends AppCompatActivity {

    private TextView detailDateTextView;
    private TextView detailTitleTextView;
    private TextView detailPreviewTextView;
    private ImageView detailImageView;
    private ProgressBar progressBar;
    private Button buttonGoWebsite;

    //private Result result;
    private String urlWebsite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        detailPreviewTextView = findViewById(R.id.detailPreviewTextView);
        detailTitleTextView = findViewById(R.id.detailTitleTextView);
        detailDateTextView = findViewById(R.id.detailDateTextView);
        detailImageView = findViewById(R.id.detailImageView);
        progressBar = findViewById(R.id.progressBar);
        buttonGoWebsite = findViewById(R.id.buttonGoWebsite);

        Intent intent = getIntent();
        if (intent != null) {
            // result = (Result)intent.getParcelableExtra("resultNews");

            // if (result != null) {

            String preview = intent.getStringExtra("Abstract");
            String title = intent.getStringExtra("Title");
            String date = intent.getStringExtra("PublishedDate");
            urlWebsite = intent.getStringExtra("UrlWebsite");
            String url = intent.getStringExtra("Url");

            if (!url.equals("NonNull")) {

                Glide.with(this)
                        .load(url)
                        .listener(new RequestListener<Drawable>() {
                            @Override
                            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                                progressBar.setVisibility(View.GONE);
                                return false;
                            }

                            @Override
                            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                                progressBar.setVisibility(View.GONE);
                                return false;
                            }
                        })
                        .into(detailImageView);
            } else {
                detailImageView.setImageResource(R.mipmap.logo  );
            }

            date = date.substring(0, 10);
            detailDateTextView.setText(date);

            detailTitleTextView.setText(title);
            detailPreviewTextView.setText(preview);
        }
    }

    public void goWebsite(View view) {

        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(urlWebsite));
        DetailActivity.this.startActivity(intent);

    }
}
