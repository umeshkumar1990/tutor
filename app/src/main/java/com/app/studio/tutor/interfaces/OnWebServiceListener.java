package com.app.studio.tutor.interfaces;

/**
 * Created by Umesh Prajapati on 19-07-2015.
 */
public interface OnWebServiceListener {

    void onWebServiceSuccess(int statusCode, String response, String tag);
    void onWebServiceFailure(int statusCode, String errorMsg, String tag);
}
