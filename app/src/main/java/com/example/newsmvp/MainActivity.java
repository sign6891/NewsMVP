package com.example.newsmvp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

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
    private String spinnerName = "home";
    private static final String API_KEY = "sVNYUCDqQngxDsUy0yfmp3piOCrlWAIg";
    private ArrayList<Result> resultArrayList;
    private RecyclerView recyclerView;
    private NewsAdapter adapter;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_list);

        resultArrayList = new ArrayList<>();

        spinner = findViewById(R.id.spinner);
        ArrayAdapter adapterSpinner = ArrayAdapter.createFromResource(this, R.array.array_them_news, R.layout.spinner_item);
        //adapterSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapterSpinner);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String[] choose = getResources().getStringArray(R.array.array_them_news);

                spinnerName = choose[position];
                getNewNews();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //spinnerName = spinner.getSelectedItem().toString();
        //Log.d("SpinnerNameKey", spinnerName);

        progressBar = findViewById(R.id.progress_bar);

        getNewNews();
    }

    private Object getNewNews() {

        RetrofitInstance.getInstance()
                .getNewsList(spinnerName, API_KEY)
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
        progressBar.setVisibility(View.GONE);
        recyclerView = findViewById(R.id.recycler_view);
        adapter = new NewsAdapter(this, resultArrayList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

    }

}
