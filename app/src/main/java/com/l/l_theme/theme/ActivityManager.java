package com.l.l_theme.theme;

import android.app.Activity;
import android.support.v4.app.FragmentActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shaozi on 15/11/19.
 */
public class ActivityManager {
    static ActivityManager instance;

    public static ActivityManager getInstance() {
        if (instance == null)
            instance = new ActivityManager();
        return instance;
    }

    List<Activity> activities = new ArrayList<Activity>();

    private ActivityManager() {
        if (activities.size() != 0)
            activities.clear();
    }

    public void add(FragmentActivity activity) {
        activities.add(activity);
    }

    public void delete() {
        if (activities.size() > 0)
            activities.remove(activities.size() - 1);
    }

    public void clear() {
        activities.clear();
    }

    public void reCreate() {
        for (Activity activity : activities) {
            if (activity != null && !activity.isFinishing()) {
                activity.recreate();
            }
        }
    }
}
