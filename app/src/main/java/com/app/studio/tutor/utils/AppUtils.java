package com.app.studio.tutor.utils;

import android.content.Context;

/**
 * Created by Umesh Prajapati on 19-07-2015.
 */
public class AppUtils {

    public int getStatusBarHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }
}
