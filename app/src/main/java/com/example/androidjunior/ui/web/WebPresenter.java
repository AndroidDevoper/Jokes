package com.example.androidjunior.ui.web;

class WebPresenter {
    private WebContract view;
    private WebClient client;
    private String url = "https://www.icndb.com/api/";

    public WebPresenter() {
        client = new WebClient(url);
    }

    public void onBackPressed() {
        if (url.equals(client.getLastUrl()))
            view.onBackPressed();
        else
            view.loadURL(client.goBack());
    }

    public void attach(WebContract view) {
        this.view = view;
        view.setWebClient(client);
        view.loadURL(client.getLastUrl());
    }

    public void detach() {
        view = null;
    }
}
