package com.example.androidjunior.ui.web;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.loader.content.Loader;


import org.jetbrains.annotations.NotNull;

class WebPresenterLoader extends Loader<WebPresenter> {

    private WebPresenter presenter;

    public WebPresenterLoader(@NonNull @NotNull Context context) {
        super(context);
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        if (presenter != null)
            deliverResult(presenter);
        else
            forceLoad();
    }

    @Override
    protected void onForceLoad() {
        super.onForceLoad();
        presenter = new WebPresenter();
        deliverResult(presenter);

    }
}
