package com.example.pc.mbaas_api.ClientControllerClasses.models;

import com.example.pc.mbaas_api.ClientControllerClasses.entities.Field;
import com.example.pc.mbaas_api.ClientControllerClasses.entities.Instance;

import java.util.List;

public class FromInstanceToJson {

    public static String convert(Instance instance)
    {
        String json = "";
        List<Field> fields = instance.getObject().getFields();

        Boolean instanceIsArray = true;
        Integer numberOfField = 0;
        for (Field field:fields)
        {
            String fieldName = field.getName();
            instanceIsArray = instanceIsArray && (fieldName == numberOfField.toString());
            numberOfField++;
        }
        if(instanceIsArray) json+='[';
        else json+='{';

        for (Field field:fields)
        {   Boolean fieldIsObject = (field.getType() == "object");
            Boolean fieldIsArray =(field.getType() == "array");
            String fieldName = field.getName();
            String value;
            if ((fieldIsObject)||(fieldIsArray)) value = convert(field.getValueFromCache().getValueInstance());
            else value = (String)field.getValueFromCache().getValue();
            if(!instanceIsArray && !fieldIsArray ) json+="\""+fieldName+"\""+":";
            if (field.getType()=="string") json+="\""+value+"\""+",";
            else json+=value+",";

        }
        json = json.substring(0,-1);
        if(instanceIsArray) json+="]";
        else json+="}";

        return json;
    }

}
