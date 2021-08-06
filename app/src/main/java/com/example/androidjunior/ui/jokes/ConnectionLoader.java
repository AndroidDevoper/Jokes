package com.example.androidjunior.ui.jokes;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

class ConnectionLoader extends AsyncTaskLoader<JSONObject> {

    private String url;

    public ConnectionLoader(@NonNull @NotNull Context context) {
        super(context);
    }

    public void setUrl(String url) {
        this.url = url;
        forceLoad();
    }

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public JSONObject loadInBackground() {
        JSONObject object = new JSONObject();
        try {
            HttpsURLConnection connection = (HttpsURLConnection) new URL(url).openConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            object = new JSONObject(reader.readLine());
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return object;
    }
}
