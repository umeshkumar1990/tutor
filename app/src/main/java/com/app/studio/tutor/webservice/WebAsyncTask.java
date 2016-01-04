package com.app.studio.tutor.webservice;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.app.studio.tutor.interfaces.OnWebServiceListener;
import com.app.studio.tutor.model.WebServiceModel;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Umesh Prajapati on 19-07-2015.
 */
public class WebAsyncTask extends AsyncTask<String, Integer, String> {

    private String USER_AGENT = "Mozilla/5.0";

    private ProgressDialog  progressDialog;
    private Context         context;
    private String          progressMsg;
    private boolean         progressVisibility  =   false;
    private Activity        activity;
    private String          webUrl;
    private String          requestTag;
    private HashMap<String, String>  params;
    private OnWebServiceListener     webServiceListener;

    public WebAsyncTask(WebServiceModel webServiceModel) {
        //Retrieve whole data from Bean class
        context     =   webServiceModel.getContext();
        activity    =   webServiceModel.getActivity();
        progressMsg =   webServiceModel.getProgressMessage();
        webUrl      =   webServiceModel.getUrl();
        params      =   webServiceModel.getParams();
        requestTag  =   webServiceModel.getRequestTag();
        progressVisibility  =   webServiceModel.isProgressVisibility();
        webServiceListener  =   webServiceModel.getWebServiceListener();
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        if(progressVisibility) {
            progressDialog = new ProgressDialog(context);
            progressDialog.setMessage(progressMsg);
            progressDialog.setTitle("");
            progressDialog.show();
        }
    }

    @Override
    protected String doInBackground(String... params) {
        return sendPOSTRequestToServer(webUrl);
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);

        if(progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
        if(webServiceListener != null) {
            webServiceListener.onWebServiceSuccess(0, "Success", webUrl);
        }
    }

    private String sendPOSTRequestToServer(String webURL) {

        String webResponse  =   "";

        // For POST only - START
        try {
            HttpURLConnection conn = null;

            URL url  =  new URL(webURL);
            conn     =  (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("User-Agent", USER_AGENT);
            conn.setReadTimeout(15000);
            conn.setConnectTimeout(15000);
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            OutputStream os = conn.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
            writer.write(getPostDataRequest(params));
            writer.flush();
            writer.close();
            os.close();
            // For POST only - END

            int responseCode = conn.getResponseCode();
            System.out.println("POST Response Code :: " + responseCode);

            if (responseCode == HttpURLConnection.HTTP_OK) { //success
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                webResponse  =   response.toString();
                // print result
                System.out.println(response.toString());
            } else {
                System.out.println("POST request not worked");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return webResponse;
    }

    private String getPostDataRequest(HashMap<String, String> params) throws UnsupportedEncodingException {

        StringBuilder result = new StringBuilder();
        boolean       first  = true;

        for(Map.Entry<String, String> entry : params.entrySet()) {
            if (first)
                first = false;
            else
                result.append("&");

            result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
        }
        return result.toString();
    }
}
