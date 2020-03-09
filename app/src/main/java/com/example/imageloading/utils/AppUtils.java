package com.example.imageloading.utils;

import android.content.Context;
import android.text.TextUtils;
import android.util.DisplayMetrics;

import com.example.imageloading.webapi.models.Category;

import java.util.List;

public class AppUtils {

    public static int dpToPx(Context context, int dp) {
        return Math.round(dp * (context.getResources().getDisplayMetrics().xdpi / DisplayMetrics.DENSITY_DEFAULT));
    }

    public static int pxToDp(final Context context, final float px) {
        return Math.round(px / context.getResources().getDisplayMetrics().density);
    }

    public static String getCategoryAsString(List<Category> categories) {
        String cat = "";
        for (Category category : categories) {
            if (!TextUtils.isEmpty(category.getTitle())) {
                cat += category.getTitle() + ", ";
            }
        }
        return cat.substring(0, cat.lastIndexOf(", "));
    }
}

