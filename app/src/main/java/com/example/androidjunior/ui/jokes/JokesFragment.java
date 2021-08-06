package com.example.androidjunior.ui.jokes;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

import com.example.androidjunior.R;
import com.example.androidjunior.databinding.FragmentJokesBinding;

import org.jetbrains.annotations.NotNull;

public class JokesFragment extends Fragment implements JokesContract {

    private FragmentJokesBinding binding;
    private JokesPresenter presenter;

    private ListView listView;
    private EditText et_count;
    private Button btn_reload;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentJokesBinding.inflate(getLayoutInflater());
        initView();
        initPresenter();
        return binding.getRoot();
    }

    private void initView() {
        listView = binding.fragmentJokesListView;
        et_count = binding.fragmentJokesEtCount;
        et_count.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                et_count.setText("");
            }
        });
        et_count.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onClickReload();
            }
        });
        btn_reload = binding.fragmentJokesBtnReload;
        btn_reload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onClickReload();
            }
        });
    }

    private void initPresenter() {
        getLoaderManager().initLoader(R.integer.id_presenter_jokes_loader,
                null,
                new LoaderManager.LoaderCallbacks<JokesPresenter>() {
                    @NonNull
                    @NotNull
                    @Override
                    public Loader<JokesPresenter> onCreateLoader(int id, @Nullable @org.jetbrains.annotations.Nullable Bundle args) {
                        JokesPresenterLoader loader = new JokesPresenterLoader(getContext());
                        loader.setActivity(getActivity());
                        return loader;
                    }

                    @Override
                    public void onLoadFinished(@NonNull @NotNull Loader<JokesPresenter> loader, JokesPresenter data) {
                        presenter = data;
                    }

                    @Override
                    public void onLoaderReset(@NonNull @NotNull Loader<JokesPresenter> loader) {
                    }
                });
    }

    @Override
    public String getCount() {
        return et_count.getText().toString();
    }

    @Override
    public void setAdapter(Adapter adapter) {
        listView.setAdapter(adapter);
    }

    @Override
    public void showToast(String text) {
        Toast.makeText(getContext(), text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showErrorCount(String s) {
        et_count.setError(s);
    }

    @Override
    public LoaderManager loaderManager() {
        return getLoaderManager();
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.attach(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
        if (presenter != null)
            presenter.detach();
    }
}