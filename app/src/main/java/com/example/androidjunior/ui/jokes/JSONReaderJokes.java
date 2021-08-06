package com.example.androidjunior.ui.jokes;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JSONReaderJokes {
    public static List<String> read(JSONObject object) {
        List<String> list = new ArrayList<>();
        try {
            JSONArray array = object.getJSONArray("value");
            for (int i = 0; i < array.length(); i++) {
                JSONObject joke = array.getJSONObject(i);
                list.add(joke.get("joke").toString());
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }
}
