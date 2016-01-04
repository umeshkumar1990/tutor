package com.app.studio.tutor.model;

import android.app.Activity;
import android.content.Context;

import com.app.studio.tutor.interfaces.OnWebServiceListener;

import java.util.HashMap;

/**
 * Created by Umesh Prajapati on 19-07-2015.
 */
public class WebServiceModel {

    private Context  context;
    private Activity activity;
    private String   url;
    private boolean  progressVisibility;
    private String   progressMessage;
    private String   requestTag;
    private HashMap<String, String> params;
    private OnWebServiceListener webServiceListener;

    public String getRequestTag() {
        return requestTag;
    }

    public void setRequestTag(String requestTag) {
        this.requestTag = requestTag;
    }

    public OnWebServiceListener getWebServiceListener() {
        return webServiceListener;
    }

    public void setWebServiceListener(OnWebServiceListener webServiceListener) {
        this.webServiceListener = webServiceListener;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isProgressVisibility() {
        return progressVisibility;
    }

    public void setProgressVisibility(boolean progressVisibility) {
        this.progressVisibility = progressVisibility;
    }

    public String getProgressMessage() {
        return progressMessage;
    }

    public void setProgressMessage(String progressMessage) {
        this.progressMessage = progressMessage;
    }

    public HashMap<String, String> getParams() {
        return params;
    }

    public void setParams(HashMap<String, String> params) {
        this.params = params;
    }

}
