package com.example.pc.mbaas_api.ClientControllerClasses.models;

import com.example.pc.mbaas_api.ClientControllerClasses.entities.Instance;
import com.example.pc.mbaas_api.ClientControllerClasses.interfaces.IConverter;

import java.util.List;

public class JsonMapperIConverter implements IConverter {

    public Object fromJson(Class objectClass, String json) throws Exception {
        if((objectClass == Instance.class)){
            try {
                return fromJsonToInstance.convert(json);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        throw new Exception("Invalid objectClass: "+objectClass.getClass().toString());
    }

    public String toJson( Object entity) throws Exception {
        if (entity.getClass() == List.class) return fromArrayOfInstancesToJson.convert((List<Instance>)entity);
        if (entity == null) throw new Exception("Entity null, impossible to convert");
        if(entity.getClass() == Instance.class) return FromInstanceToJson.convert((Instance)entity);

        throw new Exception("Invalid Entity");
    }
}
