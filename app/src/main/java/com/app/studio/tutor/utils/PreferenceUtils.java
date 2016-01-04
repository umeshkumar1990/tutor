package com.app.studio.tutor.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by Umesh Prajapati on 19-07-2015.
 */
public class PreferenceUtils {

    /**
     * Preference file name for tutor app.
     */
    private static final String TUTOR_PREF_FILE_NAME  =   "com.app.studio.tutor";
    private static final int    PREFERENCE_MODE       =    Context.MODE_PRIVATE;
    private static final String EMPTY_STRING          =    "";

    //Preference key to store value in preferences.
    private static final String KEY_USERNAME          =   "userName";


    public static void setUserName(Context context, String userName) {
        SharedPreferences prefs = context.getSharedPreferences(TUTOR_PREF_FILE_NAME, PREFERENCE_MODE);
        prefs.edit().putString(KEY_USERNAME, userName).commit();
    }

    public static String getUserName(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(TUTOR_PREF_FILE_NAME, PREFERENCE_MODE);
        return preferences.getString(KEY_USERNAME, EMPTY_STRING);
    }
}
