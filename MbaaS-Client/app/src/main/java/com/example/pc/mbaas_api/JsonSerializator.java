package com.example.pc.mbaas_api;

import com.example.pc.mbaas_api.Interfaces.ISerializator;
import com.google.gson.Gson;

public class JsonSerializator implements ISerializator {

    private Gson serializator;

    public JsonSerializator(){this.serializator = new Gson();}

    public <T>String to(T object)
    {
        return serializator.toJson(object);

    }

    public <T>T from(String json,Class <T> type)
    {
        return serializator.fromJson(json,type);
    }

}
