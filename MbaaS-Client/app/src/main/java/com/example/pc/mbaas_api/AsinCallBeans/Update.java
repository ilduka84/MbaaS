package com.example.pc.mbaas_api.AsinCallBeans;

import org.json.JSONArray;
import org.json.JSONObject;

public class Update {

    private final String url = "http://192.168.1.10/www/MbaaS-Server/Update.php";

    public void execute(int id,String json, String token) throws Exception {
        boolean isArray = json.substring(0,1).equals("[");
        boolean isObject = json.substring(0,1).equals("{");
        if(!(isArray)&&!(isObject))
            throw new IllegalArgumentException("object must be JSONArray or JSONObject");
        Object jsonInstance =(isArray)? new JSONArray(json):new JSONObject(json);
        JSONArray jsonRequest = new JSONArray();
        jsonRequest.put(id);
        jsonRequest.put(jsonInstance);
        String jsonString = jsonRequest.toString();
        String result = null;
        try {
            PostRequestAsin request = new PostRequestAsin();
            result = request.execute(this.url, jsonString, token).get();

            return ;
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (result.contains("Exception")) throw new Exception(result);
        return ;
    }
}
