package com.l.l_theme.app;

import android.app.Application;
import android.graphics.Bitmap;

/**
 * Created by shaozi on 15/11/17.
 */
public class LApplication extends Application {
    private static LApplication instance;
    public Bitmap themeBitmap;

    public void clearThemeBitmap() {
        if (themeBitmap != null)
            themeBitmap.recycle();
        themeBitmap = null;
    }

    public static LApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }
}
