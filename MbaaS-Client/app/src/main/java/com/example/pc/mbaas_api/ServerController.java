package com.example.pc.mbaas_api;

import com.example.pc.mbaas_api.AsinCallBeans.Delete;
import com.example.pc.mbaas_api.AsinCallBeans.Get;
import com.example.pc.mbaas_api.AsinCallBeans.GetFromName;
import com.example.pc.mbaas_api.AsinCallBeans.Login;
import com.example.pc.mbaas_api.AsinCallBeans.Put;
import com.example.pc.mbaas_api.AsinCallBeans.Update;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class ServerController {

    public JSONArray login(String user, String password) throws Exception,ExecutionException, InterruptedException
    {
        try {
            JSONArray result = new Login().execute(user,password);
            return result;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int put(String json, String token) throws Exception {
        return new Put().execute(json, token);

    }

    public String get(int id,String token) throws Exception {

        String result = null;
        try {
            result = new Get().execute(id, token) ;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public String get(String className, String token)
    {
        String result = null;
        try
        {
            result = new GetFromName().execute(className, token);
        }catch(Exception e){
            e.printStackTrace();
        }
        return result;
    }

    public void update(int id, String json, String token) throws Exception
    {
        try {
            new Update().execute(id, json, token);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void delete(int id, String token)
    {
        try {
            new Delete().execute(id, token);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}

