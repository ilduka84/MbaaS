package com.example.pc.mbaas_api.AsinCallBeans;

import org.json.JSONArray;
import org.json.JSONObject;

public class Delete {
    private final String url = "http://192.168.1.10/www/MbaaS-Server/Delete.php";

    public void execute(int id, String token) throws Exception {
        String jsonString = "[\""+id+"\"]";
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
