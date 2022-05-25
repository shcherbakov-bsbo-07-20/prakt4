package ru.mirea.shcherbakov.loadermanager;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.loader.content.AsyncTaskLoader;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

public class MyLoader extends AsyncTaskLoader<String> {
    private String firstName;
    public static final String ARG_WORD = "word";

    public MyLoader(@NonNull Context context, Bundle args) {
        super(context);
        if (args != null)
            firstName = args.getString(ARG_WORD);
    }
    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }

    @Override
    @RequiresApi(api = Build.VERSION_CODES.N)
    public String loadInBackground() {
        List<Character> chars;
        chars = firstName.chars().mapToObj(c -> (char) c).collect(Collectors.toList());

        List<Character> new_word = new ArrayList<>();
        while (chars.size() > 0){
            int i = ThreadLocalRandom.current().nextInt(0, chars.size());
            new_word.add(chars.get(i));
            chars.remove(i);
        }

        String res = "";
        for (Character i: new_word) { res+=i; }
        firstName = res;
        return res;
    }
}