package com.example.pc.mbaas_api.ClientControllerClasses.models;

import com.example.pc.mbaas_api.ClientControllerClasses.entities.Field;
import com.example.pc.mbaas_api.ClientControllerClasses.entities.Instance;
import com.example.pc.mbaas_api.ClientControllerClasses.entities.Obj;
import com.example.pc.mbaas_api.ClientControllerClasses.entities.Value;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

public class fromJsonToInstance {

    public static Instance convert(String json) throws Exception
    {
        if (json == null) throw new Exception("impossible to convert");
        JSONArray jsonArray = null;
        JSONObject jsonObject = null;
        if(json.substring(0,1).equals("[")) jsonArray= new JSONArray(json);
        if(json.substring(0,1).equals("{")) jsonObject = new JSONObject(json);

        Instance instance= null;
        if (jsonArray!=null)    instance =convert(jsonArray);
        if (jsonObject!=null)   instance =convert(jsonObject);

        return instance;
    }

    public static Instance convert(JSONArray json) throws JSONException {
        Obj object = new Obj();
        Instance instance = new Instance(object);
        for(Integer i=0;i<json.length();i++)
        {
            Field fieldObject = new Field(i.toString(),null, object);
            Value valueObject;
            String type = json.get(i).getClass().toString();
            if(json.get(i).getClass().toString()=="JSONArray") {
                type = "array";
                fieldObject.setType(type);
                Instance valueInstance = convert((JSONArray) json.get(i));
                valueInstance.getObject().setName(fieldObject.getName());
                valueObject = new Value(fieldObject,instance,valueInstance);
            }
            else {
                if (json.get(i).getClass().toString() == "JSONObject") {
                    type = "object";
                    fieldObject.setType(type);
                    Instance valueInstance = convert((JSONObject) json.get(i));
                    valueInstance.getObject().setName(fieldObject.getName());
                    valueObject = new Value(fieldObject, instance, valueInstance);
                } else valueObject = new Value(fieldObject, instance, json.get(i).toString());
            }

        }
        return instance;
    }

    public static Instance convert(JSONObject json) throws JSONException {
        Obj object = new Obj();
        Instance instance = new Instance(object);
        Iterator<String>fieldNames = json.keys();
        while(fieldNames.hasNext())
        {
            String fieldName = fieldNames.next();
            Field fieldObject = new Field(fieldName,null, object);
            Value valueObject;
            String type = json.get(fieldName).getClass().toString();
            if(json.get(fieldName).getClass().toString()== "JSONArray") {
                type = "array";
                fieldObject.setType(type);
                Instance valueInstance = convert((JSONArray) json.get(fieldName));
                valueInstance.getObject().setName(fieldObject.getName());
                valueObject = new Value(fieldObject,instance,valueInstance);
            }
            else {
                if (json.get(fieldName).getClass().toString() == "JSONObject") {
                    type = "object";
                    fieldObject.setType(type);
                    Instance valueInstance = convert((JSONObject) json.get(fieldName));
                    valueInstance.getObject().setName(fieldObject.getName());
                    valueObject = new Value(fieldObject, instance, valueInstance);
                } else valueObject = new Value(fieldObject, instance, json.get(fieldName).toString());
            }

        }
        return instance;
    }

}
