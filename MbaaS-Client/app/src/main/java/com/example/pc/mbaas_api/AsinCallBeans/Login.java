package com.example.pc.mbaas_api.AsinCallBeans;

import org.json.JSONArray;

public class Login {

    private final String url = "http://192.168.1.10/www/MbaaS-Server/Login.php";

    public JSONArray execute(String user, String password) throws Exception {
        String jsonString = "{\"user\":\""+user+"\",\"password\":\""+password+"\"}";
        String result = null;
        try {
            PostRequestAsin request = new PostRequestAsin();
            result = request.execute(this.url,jsonString).get();

            return new JSONArray(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(result.contains("Exception")) throw new Exception(result);
        return null;
    }

}


