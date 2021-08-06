package com.example.androidjunior.ui.jokes;

import androidx.loader.app.LoaderManager;

interface JokesContract {
    String getCount();
    void setAdapter(Adapter adapter);
    void showToast(String text);
    void showErrorCount(String s);
    LoaderManager loaderManager();
}
