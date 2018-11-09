package com.example.pc.mbaas_api.ClientControllerClasses.interfaces;

public interface IConverter {

    public Object fromJson(Class objectName,String json) throws Exception;
    public String toJson(Object object) throws Exception;
}
