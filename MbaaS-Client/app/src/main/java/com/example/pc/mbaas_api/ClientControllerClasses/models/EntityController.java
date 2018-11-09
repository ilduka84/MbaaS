package com.example.pc.mbaas_api.ClientControllerClasses.models;

import android.app.Application;

import com.example.pc.mbaas_api.ClientControllerClasses.entities.DaoMaster;
import com.example.pc.mbaas_api.ClientControllerClasses.entities.DaoSession;

import org.greenrobot.greendao.database.Database;

public class EntityController extends Application {

    private static final String DBNAME = "entity_data";
    public static final boolean ENCRYPTED = true;
    private DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();

        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this,DBNAME);
        Database db = helper.getWritableDb();
        daoSession = new DaoMaster(db).newSession();
    }

   /* public EntityController(Application app)
    {

        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(app,DBNAME);
        Database db = helper.getWritableDb();
        daoSession = new DaoMaster(db).newSession();
    }
*/
    public DaoSession getDaoSession() {
        return daoSession;
    }
}
