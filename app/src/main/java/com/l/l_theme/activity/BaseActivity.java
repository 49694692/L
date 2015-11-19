package com.l.l_theme.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;

import com.l.l_theme.theme.ActivityManager;
import com.l.l_theme.theme.ThemeManager;


/**
 * Created by shaozi on 15/11/19.
 */
public class BaseActivity extends FragmentActivity implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ThemeManager.setDayOrNightTheme(MainActivity.themeBoolean, this);

        ActivityManager.getInstance().add(this);
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityManager.getInstance().delete();
    }
}
