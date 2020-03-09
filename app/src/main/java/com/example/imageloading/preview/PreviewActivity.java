package com.example.imageloading.preview;

import android.net.Uri;
import android.os.Bundle;

import com.example.imageloading.R;
import com.example.imageloading.base.BaseActivity;
import com.example.imageloading.utils.Constants;
import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;

public class PreviewActivity extends BaseActivity {

    @BindView(R.id.sdv_preview)
    SimpleDraweeView sdvPreview;

    String imageUrl;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_preview;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getIntent().hasExtra(Constants.IMAGE_URL)) {
            imageUrl = getIntent().getStringExtra(Constants.IMAGE_URL);
        }

        sdvPreview.setImageURI(Uri.parse(imageUrl));
    }
}
