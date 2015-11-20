package com.l.l_theme.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.l.l_theme.R;
import com.l.l_theme.theme.ScreenUtil;
import com.l.l_theme.theme.ThemeManager;


public class MainActivity extends BaseActivity implements View.OnClickListener {
    private TextView tv_test;
    public static boolean themeBoolean = false;
    private Button bt_next;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_test = (TextView) findViewById(R.id.tv_test);
        tv_test.setOnClickListener(this);

        bt_next = (Button) findViewById(R.id.bt_next);
        bt_next.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_test:
                changeTheme();
                break;
            case R.id.bt_next:
                startActivity(new Intent(this, SecActivity.class));
                break;
        }
    }

    private void changeTheme() {

        themeBoolean = !themeBoolean;

        //snapShotWithoutStatusBar 全屏Dialog使用
        //snapShotWithoutStatusBar Activity跳转使用
        ThemeManager.getInstance().show(ScreenUtil.snapShotWithoutStatusBar(this), ThemeManager.TYPE_ACTIVITY);

        ThemeManager.setDayOrNightTheme(themeBoolean, true, MainActivity.this, 50);

    }

}
