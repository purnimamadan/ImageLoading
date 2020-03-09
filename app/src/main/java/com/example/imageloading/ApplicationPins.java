package com.example.imageloading;

import android.app.Application;
import android.os.Environment;
import android.util.Log;

import com.example.imageloading.webapi.RetrofitClient;
import com.facebook.cache.common.CacheEventListener;
import com.facebook.cache.disk.DiskCacheConfig;
import com.facebook.common.util.ByteConstants;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipelineConfig;

import java.io.File;

public class ApplicationPins extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        initializeFresco();
        new RetrofitClient(getApplicationContext());
    }

    public void initializeFresco() {
        DiskCacheConfig diskCacheConfig = DiskCacheConfig.newBuilder(getApplicationContext())
                .setBaseDirectoryPath(new File(Environment.getExternalStorageDirectory().getAbsoluteFile(), "Android/data/" + getPackageName()))
                .setBaseDirectoryName("cache")
                .setMaxCacheSize(100 * ByteConstants.MB)
                .setCacheEventListener(new CacheEventListener() {
                    @Override
                    public void onHit() {
                        Log.e("Pins", "onHit");
                    }

                    @Override
                    public void onMiss() {
                        Log.e("Pins", "onMiss");
                    }

                    @Override
                    public void onWriteAttempt() {
                        Log.e("Pins", "onWriteAttempt");
                    }

                    @Override
                    public void onReadException() {
                        Log.e("Pins", "onReadException");
                    }

                    @Override
                    public void onWriteException() {
                        Log.e("Pins", "onWriteException");
                    }

                    @Override
                    public void onEviction(EvictionReason evictionReason, int i, long l) {
                        Log.e("Pins", "evictionReason " + evictionReason.toString());
                    }
                })
                .setMaxCacheSizeOnLowDiskSpace(100 * ByteConstants.MB)
                .setMaxCacheSizeOnVeryLowDiskSpace(50 * ByteConstants.MB)
                .setVersion(1)
                .build();

        ImagePipelineConfig imagePipelineConfig = ImagePipelineConfig.newBuilder(getApplicationContext()).setMainDiskCacheConfig(diskCacheConfig)
                .build();
        Fresco.initialize(getApplicationContext(), imagePipelineConfig);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        Fresco.shutDown();
    }
}