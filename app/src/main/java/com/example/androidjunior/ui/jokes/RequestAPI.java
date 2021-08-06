package com.example.androidjunior.ui.jokes;

class RequestAPI {
    public static String randomJokes(int count) {
        return "https://api.icndb.com/jokes/random/" + count;
    }
}
