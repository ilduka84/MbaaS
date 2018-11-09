package com.example.pc.mbaas_api;

import android.app.Application;

import com.example.pc.mbaas_api.ClientControllerClasses.entities.Field;
import com.example.pc.mbaas_api.ClientControllerClasses.entities.Instance;
import com.example.pc.mbaas_api.ClientControllerClasses.entities.Value;
import com.example.pc.mbaas_api.ClientControllerClasses.models.JsonMapperIConverter;
import com.example.pc.mbaas_api.ClientControllerClasses.models.PersistanceFacade;
import com.example.pc.mbaas_api.ClientControllerClasses.entities.Field;
import com.example.pc.mbaas_api.ClientControllerClasses.entities.Instance;
import com.example.pc.mbaas_api.ClientControllerClasses.models.JsonMapperIConverter;
import com.example.pc.mbaas_api.ClientControllerClasses.models.PersistanceFacade;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class ClientController {

    private JsonMapperIConverter converter;
    private PersistanceFacade persistanceFacade;

    public ClientController(Application app) {
        this.converter = new JsonMapperIConverter();
        this.persistanceFacade = new PersistanceFacade(app);
    }

    public Long put(String request) throws Exception {
        Long idInstance = new Long(-1);
        try {
            Instance instance = (Instance) this.converter.fromJson(Instance.class, request);
            Long idObject = this.persistanceFacade.put(instance.getObjectFromCache());
            instance.setIdObject(idObject);
            for (Field field:instance.getObjectFromCache().getFields()) {
                Long idField = this.persistanceFacade.put(field);
                Long idValue = this.persistanceFacade.put(field.getValue());
                field.setIdValue(idValue);
                idInstance = this.persistanceFacade.put(instance);
            }
            return idInstance;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public String get(Long id) throws Exception {
        try {
            Instance instance = (Instance) this.persistanceFacade.get(Instance.class, id);
            if (instance == null) throw new Exception("Instance with id: " + id + " doesn't exist");
            return this.converter.toJson(instance);
        } catch (Exception e) {
            throw new Exception(e.getMessage());

        }
    }

    public String getAllInstancesFromName(String name) throws Exception {
        try {
            List<Instance> instances = this.persistanceFacade.getAllInstancesFromName(name);
            if (instances == null)
                throw new Exception("Instance with name " + name + " doesn't exist");
            return this.converter.toJson(instances);
        } catch (Exception e) {
            throw new Exception(e.getMessage());

        }
    }

    public Long getIdFromIdServer(Long idServer) throws Exception
    {
        Long idInstance = null;
        try{
            idInstance = persistanceFacade.getIdFromIdServer(idServer);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
        return idInstance;
    }

    public Long getIdServerFromId(Long idClient) throws Exception
    {
        Long idInstance = null;
        try{
            idInstance = persistanceFacade.getIdServerFromId(idClient);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
        return idInstance;
    }

    public String getInstancesWithoutIdServer() throws Exception
    {
        List<Instance>instances = persistanceFacade.getAllInstanceWithoutIdServer();
        return converter.toJson(instances);
    }

    public String getInstancesNotInsertIntoServer()
    {
        String result = null;
        List<Instance> instances = persistanceFacade.getAllInstancesNotInsertIntoServer();
        try {
            result = converter.toJson(instances);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public String getInstancesNotUpdateIntoServer()
    {
        String result = null;
        List<Instance> instances = persistanceFacade.getAllInstancesNotUpdateIntoServer();
        try {
            result = converter.toJson(instances);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public String getInstancesNotDeleteIntoServer()
    {
        String result = null;
        List<Instance> instances = persistanceFacade.getAllInstancesNotDeleteIntoServer();
        try {
            result = converter.toJson(instances);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public void update( Long idInstance, String request) throws Exception {
        try {
            Instance instance = (Instance) this.converter.fromJson(Instance.class, request);
            this.persistanceFacade.update(instance, idInstance);
            return;
        } catch (Exception e) {
            throw new Exception(e.getMessage());

        }
    }

    public void update(Long idInstance, Long idInstanceServer) throws Exception {
        try {
            this.persistanceFacade.update(idInstance,idInstanceServer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete(Long id) throws Exception {
        try {
            Instance instance = this.persistanceFacade.get(Instance.class, id);
            List<Field> fields= instance.getObjectFromCache().getFields();
            for(Field field:fields)
                this.persistanceFacade.delete(field.getValueFromCache());
            this.persistanceFacade.delete(instance);
            return;
        } catch (Exception e) {
            throw new Exception(e.getMessage());

        }
    }

    public void setToBeInsert(Long id) throws ClassNotFoundException {this.persistanceFacade.setToBeInsert(id);}

    public void setToBeNotInsert(Long id) throws ClassNotFoundException {this.persistanceFacade.setToBeNotInsert(id);}

    public boolean isToBeInsert(Long id) throws ClassNotFoundException {return this.persistanceFacade.isToBeInsert(id);}

    public void setToBeUpdate(Long id) throws ClassNotFoundException {this.persistanceFacade.setToBeUpdate(id);}

    public void setToBeNotUpdate(Long id) throws ClassNotFoundException {this.persistanceFacade.setToBeNotUpdate(id);}

    public boolean isToBeUpdate(Long id) throws ClassNotFoundException {return this.persistanceFacade.isToBeUpdate(id);}

    public void setToBeDelete(Long id) throws ClassNotFoundException {this.persistanceFacade.setToBeDelete(id);}

    public void setToBeNotDelete(Long id) throws ClassNotFoundException {this.persistanceFacade.setToBeNotUpdate(id);}

    public boolean isToBeDelete(Long id) throws ClassNotFoundException {return this.persistanceFacade.isToBeDelete(id);}

}