package com.example.pc.mbaas_api;

import android.app.Application;
import android.os.Handler;
import com.example.pc.mbaas_api.Interfaces.ISerializator;
import com.example.pc.mbaas_api.LogClasses.ActionType;
import org.json.JSONArray;
import org.json.JSONException;

import java.util.List;

public class MbaaSController {

    private ServerController server;
    private ClientController client;
    private List<Log> log;
    private ISerializator serializator;
    private String token;
    private Handler handler=new Handler();
    private Runnable runnable;
    private Application app;

    public MbaaSController(Application app)
    {
        this.app = app;
        this.server=new ServerController();
        this.client=new ClientController(app);

        this.runnable=new Runnable() {
            @Override
            public void run() {
                try {
                    sync();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                handler.postDelayed(this,20000);
            }
        };
        handler.postDelayed(runnable,20000);

    }

    public String toJson(Object object)
    {
        return serializator.to(object);
    }

    public <ObjectType>ObjectType fromJson(String json, Class<ObjectType> type)
    {
        return serializator.from(json, type);
    }

    public String login(String user, String password) {

        try {
            this.token = server.login(user,password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this.token;
    }

    public Long put(String json) throws Exception {
        Long idServer;
        Long idClient;
        ActionType action = new ActionType();
        action.setPut(true);
        idClient = client.put(json);
        log.add(new Log(idClient,action));
        if (this.token == null) {
            client.setToBeInsert(idClient);
            return idClient;
        }
        try {
            idServer = Long.valueOf(server.put(this.token, json));
            log.add(new Log(idServer, action));
            client.update(idClient, idServer);
        } catch (Exception e) {
            e.printStackTrace();
            client.setToBeInsert(idClient);
        }
        return idClient;
    }

    public String getFromClient(String root){
        String result = null;
        try {
            result = client.getAllInstancesFromName(root);
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    public String getFromServer(String root)
    {
        try{
            return server.get(root,this.token);
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public void update(Long idClient,String json) throws Exception {
        ActionType action = new ActionType();
        action.setUpdate(true);
        client.update(idClient,json);
        log.add(new Log(idClient,action));
        if(this.token == null) {
            client.setToBeUpdate(idClient);
            return;
        }
        Long idServer = client.getIdServerFromId(idClient);
        if(client.isToBeInsert(idClient)) return;
        if(client.isToBeDelete(idClient)) return;
        try {
            server.update(idServer.intValue(), json, this.token);
            log.add(new Log(idServer,action));
        }catch (Exception e){
            e.printStackTrace();
            client.setToBeUpdate(idClient);
        }
    }

    public void delete(Long idClient) throws Exception {
        ActionType action = new ActionType();
        action.setDelete(true);
        if(this.token == null) {
            client.setToBeDelete(idClient);
            return;
        }
        if(client.isToBeInsert(idClient)){
            client.delete(idClient);
            return;
        }
        Long idServer = client.getIdServerFromId(idClient);
        try{
            server.delete(idServer.intValue(), this.token);
            log.add(new Log(idServer,action));
            client.delete(idClient);
            log.add(new Log(idClient,action));
        }catch (Exception e){
            e.printStackTrace();
            client.setToBeDelete(idClient);
        }
    }

    private void sync() throws Exception {
        this.insertSync();
        this.updateSync();
        this.deleteSync();
    }

    private void insertSync() throws JSONException {
        ActionType action = new ActionType();
        action.setPut(true);
        Long idClient;
        String instances;
        instances = client.getInstancesNotInsertIntoServer();
        JSONArray jsonArray = new JSONArray(instances);
        try {
            for(int i=0;i<jsonArray.length();i++){
                idClient = Long.parseLong(jsonArray.getJSONArray(i).get(0).toString());
                int idServer = server.put(jsonArray.getJSONArray(i).get(1).toString(), this.token);
                client.update(idClient,Long.valueOf(idServer));
                client.setToBeNotInsert(idClient);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void updateSync() throws JSONException {   Long idClient;
        ActionType action = new ActionType();
        action.setUpdate(true);
        String instances = client.getInstancesNotUpdateIntoServer();
        JSONArray jsonArray = new JSONArray(instances);
        try {
            for(int i=0;i<jsonArray.length();i++){
                idClient = Long.parseLong(jsonArray.getJSONArray(i).get(0).toString());
                int idServer = client.getIdServerFromId(idClient).intValue();
                server.update(idServer,jsonArray.getJSONArray(i).get(1).toString(), this.token);
                client.update(idClient,Long.valueOf( idServer));
                client.setToBeNotUpdate(idClient);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void deleteSync() throws JSONException {
        Long idClient;
        ActionType action = new ActionType();
        action.setDelete(true);
        String instances = client.getInstancesNotDeleteIntoServer();
        JSONArray jsonArray = new JSONArray(instances);
        try {
            for(int i=0;i<jsonArray.length();i++){
                idClient = Long.parseLong(jsonArray.getJSONArray(i).get(0).toString());
                int idServer = client.getIdServerFromId(idClient).intValue();
                server.delete(idServer, this.token);
                client.delete(idClient);
                client.setToBeNotDelete(idClient);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
