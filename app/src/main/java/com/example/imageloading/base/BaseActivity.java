package com.example.imageloading.base;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.LayoutRes;
import androidx.appcompat.app.AppCompatActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseActivity extends AppCompatActivity {

    Unbinder unbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutRes());
    }

    @LayoutRes
    protected abstract int getLayoutRes();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder = ButterKnife.bind(this);
        unbinder.unbind();
    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        ButterKnife.bind(this);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }
}
