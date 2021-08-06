package com.example.androidjunior.ui.jokes;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

import com.example.androidjunior.R;

import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

import java.util.List;

class JokesAPI implements LoaderManager.LoaderCallbacks<JSONObject> {

    public interface Callback {
        LoaderManager getLoaderManager();
        void onLoad(List<String> jokes);
    }

    private Callback callback;
    private ConnectionLoader connectionLoader;

    public JokesAPI(Context context, Callback callback) {
        this.connectionLoader = new ConnectionLoader(context);
        this.callback = callback;
    }

    public void downloadJokes(int count) {
        connectionLoader.setUrl(RequestAPI.randomJokes(count));
        callback.getLoaderManager().initLoader(R.integer.id_json_loader,
                new Bundle(),
                this);
    }

    @NonNull
    @NotNull
    @Override
    public Loader<JSONObject> onCreateLoader(int id,
                                             @Nullable @org.jetbrains.annotations.Nullable Bundle args) {
        return connectionLoader;
    }

    @Override
    public void onLoadFinished(@NonNull @NotNull Loader<JSONObject> loader, JSONObject data) {
        callback.onLoad(JSONReaderJokes.read(data));
    }

    @Override
    public void onLoaderReset(@NonNull @NotNull Loader<JSONObject> loader) {

    }
}
