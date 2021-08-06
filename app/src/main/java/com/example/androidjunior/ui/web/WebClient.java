package com.example.androidjunior.ui.web;

import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.util.ArrayList;
import java.util.List;

class WebClient extends WebViewClient {

    private String firstURL;
    private List<String> history;

    public WebClient(String firstURL) {
        this.firstURL = firstURL;
        history = new ArrayList<>();
        history.add(firstURL);
    }

    public String getLastUrl() {
        return history.get(history.size() - 1);
    }

    public String goBack() {
        history.remove(history.size() - 1);
        return history.get(history.size() - 1);
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
        history.add(request.getUrl().toString());
        return super.shouldOverrideUrlLoading(view, request);
    }

}
