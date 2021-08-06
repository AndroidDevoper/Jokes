package com.example.androidjunior.ui.web;

import android.annotation.SuppressLint;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.androidjunior.R;
import com.example.androidjunior.databinding.FragmentWebBinding;

import org.jetbrains.annotations.NotNull;

public class WebFragment extends Fragment implements WebContract{

    private FragmentWebBinding binding;
    private WebView webView;
    private WebPresenter presenter;
    private OnBackPressedCallback backPressedCallback;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentWebBinding.inflate(getLayoutInflater());
        webView = binding.fragmentWebWebView;
        initWebView();
        initOnBackPressed();
        initPresenter();
        return binding.getRoot();
    }

    private void initWebView() {
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setBuiltInZoomControls(true);
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT)
            webView.setInitialScale(100);
        else
            webView.setInitialScale(250);
    }

    private void initOnBackPressed() {
        backPressedCallback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                presenter.onBackPressed();
            }
        };
        requireActivity().getOnBackPressedDispatcher().
                addCallback(getViewLifecycleOwner(), backPressedCallback);
    }

    private void initPresenter() {
        getLoaderManager().initLoader(R.integer.id_presenter_web_loader,
                null,
                new LoaderManager.LoaderCallbacks<WebPresenter>() {
                    @NonNull
                    @NotNull
                    @Override
                    public Loader<WebPresenter> onCreateLoader(int id, @Nullable @org.jetbrains.annotations.Nullable Bundle args) {
                        return new WebPresenterLoader(getContext());
                    }

                    @Override
                    public void onLoadFinished(@NonNull @NotNull Loader<WebPresenter> loader, WebPresenter data) {
                        presenter = data;
                    }

                    @Override
                    public void onLoaderReset(@NonNull @NotNull Loader<WebPresenter> loader) {

                    }
                });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
        if (presenter != null)
            presenter.detach();
    }

    @Override
    public void loadURL(String url) {
        webView.loadUrl(url);
    }

    @Override
    public void setWebClient(WebViewClient client) {
        webView.setWebViewClient(client);
    }

    @Override
    public void onBackPressed() {
        backPressedCallback.setEnabled(false);
        requireActivity().getOnBackPressedDispatcher().onBackPressed();
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.attach(this);
    }
}