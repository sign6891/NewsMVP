package com.example.newsmvp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Spinner;

import com.example.newsmvp.adapter.NewsAdapter;
import com.example.newsmvp.model.Multimedium;
import com.example.newsmvp.model.NewsList;
import com.example.newsmvp.model.Result;
import com.example.newsmvp.service.RetrofitInstance;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private Spinner spinner;
    private String spinnerName = "";
    private static final String API_KEY = "sVNYUCDqQngxDsUy0yfmp3piOCrlWAIg";
    private ArrayList<Result> resultArrayList;
    private RecyclerView recyclerView;
    private NewsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_list);

        resultArrayList = new ArrayList<>();

        spinner = findViewById(R.id.spinner);
        spinnerName = spinner.getSelectedItem().toString();
        getNewNews();
    }

    private Object getNewNews() {

        RetrofitInstance.getInstance()
                .getNewsList(API_KEY)
                .enqueue(new Callback<NewsList>() {
                    @Override
                    public void onResponse(Call<NewsList> call, Response<NewsList> response) {

                        NewsList newsList = response.body();
                        if (newsList != null) {
                            resultArrayList = (ArrayList<Result>) newsList.getResults();
                            fillRecyclerView();
                        }
                    }

                    @Override
                    public void onFailure(Call<NewsList> call, Throwable t) {

                    }
                });
        return resultArrayList;
    }

    private void fillRecyclerView() {
        recyclerView = findViewById(R.id.recycler_view);
        adapter = new NewsAdapter(this, resultArrayList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

    }
}
