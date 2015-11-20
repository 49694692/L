package com.l.l_theme.theme;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.Fragment;

import com.l.l_theme.R;
import com.l.l_theme.activity.CenterAnimActivity;


public class ThemeManager {
    static ThemeManager instance;
    public static final int NULLACTIVITYCODE = -1;
    public static final int NOTMAINTHREADCODE = -2;
    public static final int OK = 1;

    public static ThemeManager getInstance() {
        if (instance == null)
            instance = new ThemeManager();
        return instance;
    }

    public static boolean isMainThread() {
        return Looper.myLooper() == Looper.getMainLooper();
    }

    /**
     * @param isDayTheme   true:白天模式 false:夜间模式
     * @param needReCreate true:需要重新创建 false:不需要重新创建
     * @param activity     当前活动窗口
     * @param delay        延迟执行时间
     * @return
     */
    public synchronized static int setDayOrNightTheme(final boolean isDayTheme, final boolean needReCreate, final Fragment activity, long delay) {
        if (activity == null || activity.getActivity() == null || activity.getActivity().isFinishing())
            return NULLACTIVITYCODE;

        if (!isMainThread())
            return NOTMAINTHREADCODE;

        if (delay == 0l) {
            changeTheme(isDayTheme, activity);
            reCreate(needReCreate);
            return OK;
        }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                changeTheme(isDayTheme, activity);
                reCreate(needReCreate);
            }
        }, delay);

        return OK;
    }

    /**
     * @param isDayTheme   true:白天模式 false:夜间模式
     * @param needReCreate true:需要重新创建 false:不需要重新创建
     * @param activity     当前活动窗口
     * @param delay        延迟执行时间
     * @return
     */
    public synchronized static int setDayOrNightTheme(final boolean isDayTheme, final boolean needReCreate, final Activity activity, long delay) {
        if (activity == null || activity.isFinishing())
            return NULLACTIVITYCODE;

        if (!isMainThread())
            return NOTMAINTHREADCODE;

        if (delay == 0l) {
            changeTheme(isDayTheme, activity);
            reCreate(needReCreate);
            return OK;
        }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                changeTheme(isDayTheme, activity);
                reCreate(needReCreate);
            }
        }, delay);

        return OK;
    }

    /**
     * @param isDayTheme   true:白天模式 false:夜间模式
     * @param needReCreate true:需要重新创建 false:不需要重新创建
     * @param activity     当前活动窗口
     * @return
     */
    public synchronized static int setDayOrNightTheme(final boolean isDayTheme, final boolean needReCreate, final Fragment activity) {
        return setDayOrNightTheme(isDayTheme, needReCreate, activity, 0l);
    }

    /**
     * @param isDayTheme   true:白天模式    false:夜间模式
     * @param needReCreate true:需要重新创建  false:不需要重新创建
     * @param activity     当前活动窗口
     * @return
     */
    public synchronized static int setDayOrNightTheme(final boolean isDayTheme, final boolean needReCreate, final Activity activity) {
        return setDayOrNightTheme(isDayTheme, needReCreate, activity, 0l);
    }

    /**
     * @param isDayTheme true:白天模式    false:夜间模式
     * @param activity   当前活动窗口
     * @return
     */
    public synchronized static int setDayOrNightTheme(final boolean isDayTheme, final Fragment activity) {
        return setDayOrNightTheme(isDayTheme, false, activity, 0l);
    }

    /**
     * @param isDayTheme true:白天模式 false:夜间模式
     * @param activity   当前活动窗口
     * @return
     */
    public synchronized static int setDayOrNightTheme(final boolean isDayTheme, final Activity activity) {
        return setDayOrNightTheme(isDayTheme, false, activity, 0l);
    }

    /**
     * @param activity 当前活动窗口
     * @return
     */
    public synchronized static int setDayOrNightTheme(final Fragment activity) {
        return setDayOrNightTheme(false, false, activity, 0l);
    }

    /**
     * @param activity 当前活动窗口
     * @return
     */
    public synchronized static int setDayOrNightTheme(final Activity activity) {
        return setDayOrNightTheme(false, false, activity, 0l);
    }

    private static void changeTheme(boolean isDayTheme, Activity activity) {
        if (isDayTheme) {
            activity.setTheme(R.style.DayTheme);
        } else
            activity.setTheme(R.style.NightTheme);
    }

    private static void changeTheme(boolean isDayTheme, Fragment activity) {
        if (isDayTheme) {
            activity.getActivity().setTheme(R.style.DayTheme);
        } else
            activity.getActivity().setTheme(R.style.NightTheme);
    }

    private static void reCreate(boolean needReCreate) {
        if (!needReCreate) {
            return;
        }
        ActivityManager.getInstance().reCreate();
    }

    private ThemeDialog themeDialog;
    public static final int TYPE_DIALOG = 101;
    public static final int TYPE_ACTIVITY = 102;

    private ThemeManager() {
        themeDialog = new ThemeDialog();
    }

    public Bitmap bitmap;

    public void show(Bitmap bitmap, int type) {
        if (bitmap == null)
            return;
        switch (type) {
            case TYPE_DIALOG:
                themeDialog.show(bitmap);
                break;
            case TYPE_ACTIVITY:
                Activity activity = ActivityManager.getInstance().getLast();
                if (activity == null)
                    return;
                this.bitmap = bitmap;
                activity.startActivity(new Intent(activity, CenterAnimActivity.class));
                centerAnim(activity);
                break;
        }
    }

    public void dissmiss() {
        themeDialog.dissmiss();
    }

    private void centerAnim(Activity activity) {
        activity.overridePendingTransition(R.anim.empty, R.anim.empty);
    }
}
