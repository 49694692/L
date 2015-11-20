package com.l.l_theme.activity;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import com.l.l_theme.R;
import com.l.l_theme.theme.ThemeManager;

/**
 * Created by shaozi on 15/11/20.
 * <p>
 * 中间动画层
 */
public class CenterAnimActivity extends Activity {
    private ImageView imageView;
    private Bitmap bitmap;
    ////

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_center);
        imageView = (ImageView) findViewById(R.id.imageView);
        bitmap = ThemeManager.getInstance().bitmap;
        if (bitmap == null)
            finish();
        imageView.setImageBitmap(ThemeManager.getInstance().bitmap);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    finish();
                    overridePendingTransition(R.anim.empty, R.anim.alpha_to_zero);
                }
            }, 100);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (bitmap != null)
            bitmap.recycle();
        bitmap = null;
    }
}
