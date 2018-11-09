package com.example.pc.mbaas_api;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.pc.mbaas_api.ClientControllerClasses.entities.Field;
import com.example.pc.mbaas_api.ClientControllerClasses.entities.Instance;
import com.example.pc.mbaas_api.ClientControllerClasses.entities.Obj;
import com.example.pc.mbaas_api.ClientControllerClasses.entities.Value;
import com.example.pc.mbaas_api.ClientControllerClasses.models.PersistanceFacade;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView text =  findViewById(R.id.text);
        PersistanceFacade persistanceFacade = new PersistanceFacade(this.getApplication());
        Obj object = new Obj(null,"test");
        Long idObject = persistanceFacade.put(object);
        Field field = new Field( null,"field","String",0,idObject);
        Long idField = persistanceFacade.put(field);
        Instance instanceExpected = new Instance( null,null,idObject);
        //Value value = new Value(null,idField,"asd",null);
        //Long idValue =persistanceFacade.put(value);
        //field.setIdValue(idValue);
        try {
            persistanceFacade.update(field,idField);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Long id = persistanceFacade.put(instanceExpected);

        Instance instanceGet = null;
        try {
            instanceGet = persistanceFacade.get(Instance.class,id);
            instanceGet.getObject().getFields().get(0).getValue().setValue("cambio");
            persistanceFacade.update(instanceGet,id);
            instanceGet = persistanceFacade.get(Instance.class,id);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        //String name = instanceGet.getObject().getFields().get(0).getValue().getValue();
        //text.setText(name);

    }
}
