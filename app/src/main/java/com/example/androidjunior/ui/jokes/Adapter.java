package com.example.androidjunior.ui.jokes;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.androidjunior.databinding.ItemJokeBinding;

import java.util.ArrayList;
import java.util.List;

class Adapter extends BaseAdapter {

    private List<String> jokes;
    private LayoutInflater inflater;
    private ItemJokeBinding binding;

    public Adapter(LayoutInflater inflater) {
        this.inflater = inflater;
        jokes = new ArrayList<>();
    }

    public void setJokes(List<String> jokes) {
        this.jokes = jokes;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return jokes.size();
    }

    @Override
    public Object getItem(int i) {
        return jokes.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        binding = ItemJokeBinding.inflate(inflater);
        if (view == null)
           view = binding.getRoot();
        binding.itemJokeTvNumber.setText((i + 1) + ")");
        binding.itemJokeTvText.setText(jokes.get(i));
        return view;
    }
}
