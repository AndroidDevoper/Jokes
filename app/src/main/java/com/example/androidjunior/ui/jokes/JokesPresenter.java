package com.example.androidjunior.ui.jokes;

import android.app.Activity;

import androidx.loader.app.LoaderManager;

import com.example.androidjunior.InternetAccess;

import java.util.List;

class JokesPresenter implements InternetAccess.OnListener, JokesAPI.Callback{

    private JokesContract view;
    private InternetAccess access;
    private Adapter jokesAdapter;
    private JokesAPI api;

    public JokesPresenter(Activity activity) {
        access = new InternetAccess(activity.getBaseContext(),
                this);
        jokesAdapter = new Adapter(activity.getLayoutInflater());
        api = new JokesAPI(activity.getBaseContext(),
                this);
    }

    public void onClickReload() {
        access.scan();
    }

    @Override
    public void isAccess(boolean ch) {
        if (ch) {
            String count = view.getCount();
            if (count.matches("\\d+")) {
                int ct = Integer.parseInt(count);
                if (ct > 15)
                    view.showErrorCount("max 15");
                else
                    api.downloadJokes(ct);
            }
            else
                view.showErrorCount("error");
        }
        else
            view.showToast("Network error");
    }

    @Override
    public LoaderManager getLoaderManager() {
        return view.loaderManager();
    }

    @Override
    public void onLoad(List<String> jokes) {
        jokesAdapter.setJokes(jokes);
        view.setAdapter(jokesAdapter);
    }

    public void detach() {
        view = null;
    }

    public void attach(JokesContract view) {
        this.view = view;
        view.setAdapter(jokesAdapter);
    }
}
