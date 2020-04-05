package com.example.newsmvp;

import android.content.Context;

import com.example.newsmvp.model.Result;

import java.util.ArrayList;

public interface NewsView {
    void fillRecyclerView(ArrayList<Result> resultArrayList);
    void showError();
}
