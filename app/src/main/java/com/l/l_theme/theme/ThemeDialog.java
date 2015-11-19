package com.l.l_theme.theme;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.LinearLayout;

import com.l.l_theme.R;
import com.l.l_theme.app.LApplication;


public class ThemeDialog {
    public static final int BITMAPNULLCODE = -1;
    public static final int OK = 1;

    private AlertDialog alertDialog;
    private Context mContext;
    private Animation alphaAnimation;
    private LinearLayout view;
    private Bitmap cacheBitmap;

    public ThemeDialog() {
        this.mContext = LApplication.getInstance().getApplicationContext();
        init();
    }

    private void init() {
        if (mContext == null)
            return;

        if (alertDialog == null) {
            alertDialog = new AlertDialog.Builder(mContext, R.style.DialogTransparent).create();
            alertDialog.setCanceledOnTouchOutside(false);
        }

        if (view == null) {
            WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
            view = new LinearLayout(mContext);
            view.setBackgroundResource(android.R.color.transparent);
            view.setLayoutParams(layoutParams);
        }
    }

    public int show(Bitmap bitmap) {
        if (bitmap == null)
            return BITMAPNULLCODE;

        cacheBitmap = bitmap;

        init();
        if (alertDialog.isShowing())
            alertDialog.dismiss();

        if (alphaAnimation == null) {
            alphaAnimation = new AlphaAnimation(1f, 0f);
            alphaAnimation.setRepeatMode(AlphaAnimation.INFINITE);
            alphaAnimation.setStartOffset(200);
            alphaAnimation.setDuration(1000);

            alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    dissmiss();
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
        }

        Window window = alertDialog.getWindow();
        window.setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);

        alertDialog.show();

        window.setContentView(view);
        view.setBackgroundDrawable(new BitmapDrawable(bitmap));
        view.startAnimation(alphaAnimation);

        return OK;
    }

    public void dissmiss() {
        if (alertDialog == null)
            return;
        if (alertDialog.isShowing())
            alertDialog.dismiss();

        if (view != null)
            view.setBackgroundResource(0);

        if (cacheBitmap != null) {
            cacheBitmap.recycle();
            cacheBitmap = null;
        }
    }
}
