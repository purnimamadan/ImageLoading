package com.example.imageloading;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.imageloading.adapters.PinsAdapter;
import com.example.imageloading.base.BaseActivity;
import com.example.imageloading.pins.PinsContract;
import com.example.imageloading.pins.PinsPresenterImpl;
import com.example.imageloading.preview.PreviewActivity;
import com.example.imageloading.utils.Constants;
import com.example.imageloading.webapi.models.Pin;
import com.example.imageloading.webapi.models.PinsResponse;
import com.example.imageloading.widgets.progress.CustomProgressDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MainActivity extends BaseActivity implements PinsContract.View {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.title_toolbar)
    TextView toolbarTitle;
    @BindView(R.id.rv_pins)
    RecyclerView rvPins;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;

    List<Pin> pinsList;
    PinsAdapter pinsAdapter;
    PinsContract.Presenter presenter;
    CustomProgressDialog mProgressDialog;

    public static final String pins_code = "wgkJgazE";

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new PinsPresenterImpl(this);
        setToolbar();
        setRecyclerView();
        setSwipeRefreshLayout();
        getPins();
    }

    public void setSwipeRefreshLayout() {
        if (swipeRefreshLayout == null)
            return;
        swipeRefreshLayout.setColorSchemeResources(
                R.color.colorPrimaryDark,
                R.color.colorPrimary,
                R.color.colorPrimaryLight);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getPins();
            }
        });
    }


    public void setToolbar() {
        if (null != toolbar) {
            this.setSupportActionBar(toolbar);
            this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            this.getSupportActionBar().setTitle("");
            toolbarTitle.setText(Constants.IMAGE_LOADING);
            final Drawable upArrow = getResources().getDrawable(R.drawable.ic_back_arrow_white);
            getSupportActionBar().setHomeAsUpIndicator(upArrow);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBackPressed();
                }
            });
        }
    }

    public void getPins() {
        presenter.getPins(pins_code);
    }

    public void setRecyclerView() {
        final StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        staggeredGridLayoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        staggeredGridLayoutManager.invalidateSpanAssignments();
        rvPins.setLayoutManager(staggeredGridLayoutManager);
        rvPins.setHasFixedSize(true);

        if (null == pinsList) {
            pinsList = new ArrayList<>();
        }
        if (null == pinsAdapter) {
            pinsAdapter = new PinsAdapter(pinsList, this, this);
        }
        rvPins.setAdapter(pinsAdapter);
    }

    @Override
    public void onGetPinsSuccess(PinsResponse pinsResponse) {
        if (null != pinsResponse && null != pinsResponse.getPins() && pinsResponse.getPins().size() > 0) {
            pinsList.addAll(pinsResponse.getPins());
            pinsAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onGetPinsError(String message) {
        showToast(message);
    }

    @Override
    public void onPinClicked(int position) {
        if (null != pinsList.get(position) && null != pinsList.get(position).getUrls()
                && !TextUtils.isEmpty(pinsList.get(position).getUrls().getFull())) {
            Intent intent = new Intent(this, PreviewActivity.class);
            intent.putExtra(Constants.IMAGE_URL, pinsList.get(position).getUrls().getRegular());
            startActivity(intent);
        }
    }

    @Override
    public void showProgress() {
        if (mProgressDialog == null) {
            mProgressDialog = new CustomProgressDialog(this);
        }
        if (!mProgressDialog.isShowing()) {
            mProgressDialog.show();
        }
    }

    @Override
    public void hideProgress() {
        if (null != mProgressDialog) {
            if (mProgressDialog.isShowing()) {
                mProgressDialog.dismiss();
            }
        }
        if (null == swipeRefreshLayout) {
            swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);
        }
        if (null != swipeRefreshLayout && swipeRefreshLayout.isRefreshing()) {
            pinsList.clear();
            swipeRefreshLayout.setRefreshing(false);
        }
    }

    @Override
    public void showToast(String message) {
        if (!TextUtils.isEmpty(message)) {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        }
    }
}

