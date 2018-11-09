package com.example.pc.mbaas_api.AsinCallBeans;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

public class Put {

    private final String url = "http://192.168.1.10/www/MbaaS-Server/Put.php";

    public int execute(String json,String token) throws Exception {
        String result = null;
        try {
            PostRequestAsin request = new PostRequestAsin();
            result = request.execute(this.url,json,token).get();
            if(result.contains("Exception")) throw new Exception(result);
            return Integer.parseInt(result.substring(1,1));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return -1;
    }

}
