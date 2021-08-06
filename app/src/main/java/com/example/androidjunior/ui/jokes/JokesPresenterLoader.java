package com.example.androidjunior.ui.jokes;

import android.app.Activity;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.loader.content.Loader;

import org.jetbrains.annotations.NotNull;

class JokesPresenterLoader extends Loader<JokesPresenter> {

    private JokesPresenter presenter;
    private Activity activity;

    public JokesPresenterLoader(@NonNull @NotNull Context context) {
        super(context);
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
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
        presenter = new JokesPresenter(activity);
        deliverResult(presenter);
    }

    @Override
    protected void onReset() {
        super.onReset();
        presenter = null;
    }
}
