package com.example.pc.mbaas_api.AsinCallBeans;

import android.os.AsyncTask;
import android.util.Log;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class PostRequestAsin extends AsyncTask<String, Void, String> {
    private URL url;
    private int responseCode;
    private String error;
    private String response;
    private String request;

    protected String doInBackground(String...param)
    {
        String urlString = param[0];
        String json =param[1];
        String header = "";
        if (param.length ==3) header = param[2];
        if(android.os.Debug.isDebuggerConnected())
            android.os.Debug.waitForDebugger();
        try {
            URL url = new URL(urlString);//cambiato
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(15000 /* milliseconds */);
            conn.setConnectTimeout(15000 /* milliseconds */);
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setRequestProperty("Content-Type","application/json");
            conn.setRequestProperty("Authorization", "Bearer "+header );

            OutputStream os = conn.getOutputStream();
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(os, "UTF-8"));
            writer.write(json);
            writer.flush();
            writer.close();
            os.close();

            this.responseCode=conn.getResponseCode();

            InputStream inputStream;
            if (this.responseCode == 200)  inputStream= conn.getInputStream();
            else inputStream = conn.getErrorStream();
            BufferedReader in=new BufferedReader(
                new InputStreamReader(
                    inputStream));
                StringBuffer sb = new StringBuffer();
                String line="";
            while((line = in.readLine()) != null) {
                Log.e("line:",line);
                sb.append(line+ "\n");
                }
            in.close();
            Log.e("out:",sb.toString());
            this.response = sb.toString();
            return this.response;
        }
        catch(Exception e) {

            e.getStackTrace();
            return null;
        }
    }

    public String getResponse()
    {
        return response;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public void setUrl(URL url) {
        this.url = url;
    }

}
