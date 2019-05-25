package com.example.finalproject;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

public class Play extends Activity {

    ViewClass viewClass;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewClass = new ViewClass(this);
        setContentView(viewClass);
    }
}

