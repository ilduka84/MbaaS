package com.example.pc.mbaas_api.AsinCallBeans;

import org.json.JSONArray;
import org.json.JSONObject;

public class Get {

    private final String url = "http://192.168.1.10/www/MbaaS-Server/Get.php";

    public String execute(int id, String token) throws Exception {
        String jsonString = "[\"" + id + "\"]";
        String result = null;
        try {
            PostRequestAsin request = new PostRequestAsin();
            result = request.execute(this.url, jsonString, token).get();
            if (result.contains("Exception")) {
                throw new Exception(result);
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}