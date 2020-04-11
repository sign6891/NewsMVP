package com.example.newsmvp.news;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.Spinner;

import com.example.newsmvp.R;
import com.example.newsmvp.adapter.NewsAdapter;
import com.example.newsmvp.authentication.AuthenticationActivity;
import com.example.newsmvp.model.Result;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class NewsActivity extends AppCompatActivity implements NewsView {

    private Spinner spinner;

    private ProgressBar progressBar;
    private RecyclerView recyclerView;
    private NewsPresenter newsPresenter;
    private String spinnerName = "home";
    private NewsAdapter adapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private Snackbar snackbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_list);

        newsPresenter = new NewsPresenter(this);

        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);
        recyclerView = findViewById(R.id.recycler_view);
        spinner = findViewById(R.id.spinner);

        ArrayAdapter adapterSpinner = ArrayAdapter.createFromResource(this, R.array.array_them_news, R.layout.spinner_item);
        //adapterSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapterSpinner);

        progressBar = findViewById(R.id.progress_bar);

        showSpinner(spinner);
        newsPresenter.showNewNews(spinnerName);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                newsPresenter.showNewNews(spinnerName);
            }
        });
    }

    public void showSpinner(Spinner spinner) {
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String[] choose = getResources().getStringArray(R.array.array_them_news);

                spinnerName = choose[position];
                newsPresenter.showNewNews(spinnerName);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        newsPresenter.onDetachView();
    }

    @Override
    public void showError() {
        snackbar = Snackbar.make(swipeRefreshLayout, "Connection problems, only cached data is shown." +
                " Check the connection or try again later.", Snackbar.LENGTH_LONG)
                .setAction("OK", (d) -> snackbar.dismiss());
        snackbar.show();

        progressBar.setVisibility(View.GONE);
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void fillRecyclerView(ArrayList<Result> resultArrayList) {
        progressBar.setVisibility(View.GONE);
        adapter = new NewsAdapter(this, resultArrayList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        //adapter = new NewsAdapter(resultArrayList -> newsPresenter.onNewsSelected(resultArrayList));
        recyclerView.setAdapter(adapter);
        swipeRefreshLayout.setRefreshing(false);
    }

    //реализация кнопки меню
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            //реализация кнопки меню выхода из аккаунта
            case R.id.sign_out:
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(NewsActivity.this, AuthenticationActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
