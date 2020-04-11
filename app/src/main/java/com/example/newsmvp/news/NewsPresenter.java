package com.example.newsmvp.news;

import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.newsmvp.R;
import com.example.newsmvp.authentication.AuthenticationActivity;
import com.example.newsmvp.model.NewsList;
import com.example.newsmvp.model.Result;
import com.example.newsmvp.service.RetrofitInstance;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsPresenter {

    @Nullable
    private NewsView newsView;
    private static final String API_KEY = "sVNYUCDqQngxDsUy0yfmp3piOCrlWAIg";
    private ArrayList<Result> resultArrayList = new ArrayList<>();

    public NewsPresenter(NewsView newsView) {
        this.newsView = newsView;
    }

    //метод загрузки новостей
    Object showNewNews(String spinnerName) {

        RetrofitInstance.getInstance()
                .getNewsList(spinnerName, API_KEY)
                .enqueue(new Callback<NewsList>() {
                    @Override
                    public void onResponse(Call<NewsList> call, Response<NewsList> response) {

                        NewsList newsList = response.body();
                        if (newsList != null) {
                            resultArrayList = (ArrayList<Result>) newsList.getResults();
                            newsView.fillRecyclerView(resultArrayList);
                        }
                    }

                    @Override
                    public void onFailure(Call<NewsList> call, Throwable t) {
                        newsView.showError();
                    }
                });
        return resultArrayList;
    }

    public void onDetachView() {
        newsView = null;
    }
}
