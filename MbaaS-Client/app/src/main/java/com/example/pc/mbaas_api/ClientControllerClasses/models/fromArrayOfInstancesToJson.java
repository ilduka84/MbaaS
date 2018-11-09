package com.example.pc.mbaas_api.ClientControllerClasses.models;

import com.example.pc.mbaas_api.ClientControllerClasses.entities.Instance;
import com.example.pc.mbaas_api.ClientControllerClasses.interfaces.IConverter;

import java.util.List;

public class fromArrayOfInstancesToJson {

    public static String convert(List<Instance> instances) throws Exception {
        IConverter converter = new JsonMapperIConverter();
        try {
            String result = "[";
            for (Instance instance:instances)
                result +=
                        "["+(instance.getId()-1)+",{\""+instance.getObject().getName()+"\":"+converter.toJson(instance)+"}],";
            result =result.substring(0,-1);
            result+="]";
            return result;
        }catch(Exception e)
        {
            throw new Exception(e.getMessage());

        }

    }
}
