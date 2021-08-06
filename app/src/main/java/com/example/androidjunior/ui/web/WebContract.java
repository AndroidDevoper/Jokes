package com.example.androidjunior.ui.web;

import android.webkit.WebViewClient;

interface WebContract {
    void loadURL(String url);
    void setWebClient(WebViewClient client);
    void onBackPressed();
}
