package com.example.imageloading.widgets.progress;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Window;

import com.example.imageloading.R;

public class CustomProgressDialog extends Dialog {
    Context context;

    public CustomProgressDialog(Context context) {
        super(context);
        this.context = context;
        initializeView(context);
    }

    public CustomProgressDialog(Context context, int theme) {
        super(context, theme);
        this.context = context;
        initializeView(context);
    }

    protected CustomProgressDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        this.context = context;
        initializeView(context);
    }

    public void initializeView(Context ctx) {

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        this.setCancelable(false);
        this.setContentView(R.layout.progress_dialog);
    }
}
