package com.l.l_theme.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.l.l_theme.R;


/**
 * Created by shaozi on 15/11/19.
 */
public class ThrActivity extends BaseActivity {
    Button bt_next;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thr);
        bt_next = (Button) findViewById(R.id.bt_next);
        bt_next.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.bt_next:
                startActivity(new Intent(this, MainActivity.class));
                break;
        }

    }
}

