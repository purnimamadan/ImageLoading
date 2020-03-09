package com.example.imageloading.adapters;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.imageloading.R;
import com.example.imageloading.pins.PinsContract;
import com.example.imageloading.utils.AppUtils;
import com.example.imageloading.webapi.models.Pin;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
public class PinsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<Pin> pinsList;
    PinsContract.View pinsView;
    Context mContext;

    private final int ITEM_PROGRESS = -1;


    public PinsAdapter(List<Pin> pinsList, PinsContract.View pinsView, Context context) {
        this.pinsList = pinsList;
        this.mContext = context;
        this.pinsView = pinsView;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == ITEM_PROGRESS) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_progress, parent, false);
            return new ProgressViewHolder(v);
        } else {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pin, parent, false);
            return new PinsViewHolder(v);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (null == pinsList.get(position)) {
            return ITEM_PROGRESS;
        } else {
            return super.getItemViewType(position);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof PinsViewHolder) {
            PinsViewHolder pinsViewHolder = (PinsViewHolder) holder;
            Pin pin = pinsList.get(position);

            if (null != pin) {
                if (null != pin.getCategories() && pin.getCategories().size() > 0) {
                    pinsViewHolder.tvCategory.setVisibility(View.VISIBLE);
                    pinsViewHolder.tvCategory.setText(AppUtils.getCategoryAsString(pin.getCategories()));
                } else {
                    pinsViewHolder.tvCategory.setVisibility(View.GONE);
                }

                if (!TextUtils.isEmpty(pin.getColor())) {
                    pinsViewHolder.sdvPin.setBackgroundColor(Color.parseColor(pin.getColor()));
                } else {
                    pinsViewHolder.sdvPin.setBackgroundColor(ContextCompat.getColor(mContext, R.color.lightGrey));
                }
                if (pin.getHeight() != 0 && pin.getWidth() != 0) {
                    pinsViewHolder.sdvPin.getLayoutParams().height = AppUtils.pxToDp(mContext, pin.getHeight());
                } else {
                    pinsViewHolder.sdvPin.getLayoutParams().height = AppUtils.dpToPx(mContext, 240);
                }
                if (null != pin.getUrls() && !TextUtils.isEmpty(pin.getUrls().getRegular())) {
                    pinsViewHolder.sdvPin.setImageURI(Uri.parse(pin.getUrls().getRegular()));
                } else {
                    pinsViewHolder.sdvPin.setImageURI(null);
                }
                if (null != pin.getUser()) {
                    if (null != pin.getUser().getProfile_image() && !TextUtils.isEmpty(pin.getUser().getProfile_image().getSmall())) {
                        pinsViewHolder.sdvPp.setImageURI(Uri.parse(pin.getUser().getProfile_image().getSmall()));
                    } else {
                        pinsViewHolder.sdvPp.setImageURI(null);
                    }
                    if (!TextUtils.isEmpty(pin.getUser().getName())) {
                        pinsViewHolder.tvName.setText(pin.getUser().getName());
                    } else {
                        pinsViewHolder.tvName.setText("Name");
                    }
                    if (!TextUtils.isEmpty(pin.getUser().getUsername())) {
                        pinsViewHolder.tvDetails.setText(pin.getUser().getUsername());
                    } else {
                        pinsViewHolder.tvDetails.setText("Pin");
                    }
                }
            }
            setListeners(position, pinsViewHolder);
        }
    }

    public void setListeners(final int position, PinsViewHolder pinsViewHolder) {
        pinsViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pinsView.onPinClicked(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return pinsList.size();
    }

    public class PinsViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.sdv_pin)
        SimpleDraweeView sdvPin;
        @BindView(R.id.sdv_pp)
        SimpleDraweeView sdvPp;
        @BindView(R.id.tv_category)
        TextView tvCategory;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_details)
        TextView tvDetails;

        public PinsViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public class ProgressViewHolder extends RecyclerView.ViewHolder {

        public ProgressViewHolder(View view) {
            super(view);
        }
    }
}
