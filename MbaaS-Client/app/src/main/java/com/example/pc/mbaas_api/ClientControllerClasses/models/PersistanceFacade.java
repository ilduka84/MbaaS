package com.example.pc.mbaas_api.ClientControllerClasses.models;

import android.app.Application;

import com.example.pc.mbaas_api.ClientControllerClasses.entities.DaoSession;
import com.example.pc.mbaas_api.ClientControllerClasses.entities.Field;
import com.example.pc.mbaas_api.ClientControllerClasses.entities.Instance;
import com.example.pc.mbaas_api.ClientControllerClasses.entities.InstanceDao;
import com.example.pc.mbaas_api.ClientControllerClasses.entities.ObjDao;
import com.example.pc.mbaas_api.ClientControllerClasses.entities.Value;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;

import java.util.List;

public class PersistanceFacade {

    private DaoSession daoSession;

    public PersistanceFacade(Application app)
    {
        this.daoSession = ((EntityController)app).getDaoSession();
    }

    public <EntityType>EntityType get(Class<EntityType> entity, Long id) throws ClassNotFoundException {

        AbstractDao<EntityType,Long> dao =(AbstractDao<EntityType,Long>) daoSession.getDao(entity);
        return dao.load(id);

    }

    public Long getIdFromIdServer(Long idServer)
    {
        return daoSession.getInstanceDao()
                .queryBuilder()
                .where(InstanceDao.Properties.IdServer.eq(idServer)).list().get(0).getIdServer();
    }

    public Long getIdServerFromId(Long idClient) throws ClassNotFoundException {
        return this.get(Instance.class,idClient).getIdServer();
    }

    public List<Instance> getAllInstanceWithoutIdServer()
    {
        return daoSession.getInstanceDao()
                .queryBuilder()
                .where(InstanceDao.Properties.IdServer.eq(null)).list();
    }

    public<EntityType> Long put(EntityType entity)
    {

        AbstractDao<EntityType,Long> dao =(AbstractDao<EntityType,Long>)daoSession.getDao(entity.getClass());
        Long id =dao.insert(entity);
        return id;
    }

    public <EntityType>void update(EntityType entityNew, Long id)throws ClassNotFoundException
    {
        EntityType entityOld = (EntityType) this.get(entityNew.getClass(), id);
        if (entityOld != null) {
            if (entityNew.getClass() == getClass().forName("Instance")){
                this.updateInstance((Instance)entityOld,(Instance) entityNew);
                return;
            }
        }
        throw new IllegalArgumentException("The id of entity doesn't exist. Make sure you have insert a correct id into entity");
    }

    private void updateInstance(Instance instanceOld, Instance instanceNew)
    {
        List<Field> fieldsOld = instanceOld.getObjectFromCache().getFields();
        List<Field> fieldsNew = instanceNew.getObjectFromCache().getFields();
        for (int i=0;i<fieldsOld.size();i++) {
            Field fieldOld = fieldsOld.get(i);
            Field fieldNew = fieldsNew.get(i);
            Value valueOld = fieldOld.getValueFromCache();
            Value valueNew = fieldNew.getValueFromCache();
        if (fieldOld.getType() == "object" || fieldOld.getType() == "array")
            this.updateInstance(valueOld.getValueInstanceFromCache(), valueNew.getValueInstanceFromCache());
        else {
            if (valueOld.getValue() != valueNew.getValue()){
                if(valueOld.getValueInstanceFromCache()!=null) valueOld.setValueInstance(valueNew.getValueInstanceFromCache());
                else valueOld.setValue((String)valueNew.getValue());
                this.daoSession.getValueDao().update(valueOld);
            }
        }
    }
        this.daoSession.getInstanceDao().update(instanceOld);
        return;
    }

    public void update(Long idInstance, Long idInstanceServer) throws Exception {
        Instance instance =this.daoSession.getInstanceDao().load(idInstance);
        if (instance == null) throw new Exception(idInstance+" don't exist");
        instance.setIdServer(idInstanceServer);
        this.daoSession.getInstanceDao().update(instance);
    }

    public <EntityType>void delete(EntityType entity)
    {
        if (entity != null) {
            AbstractDao<EntityType,Long> dao =(AbstractDao<EntityType,Long>)daoSession.getDao(entity.getClass());
            dao.delete(entity);
            return;
        }
        throw new IllegalArgumentException("The id of entity doesn't exist. Make sure you have insert a correct id into entity");
    }

    public List<Instance> getAllInstancesFromName(String name)
    {
        return daoSession.getInstanceDao().queryBuilder()
                .where(ObjDao.Properties.Name.eq(name)).list();

    }

    public List<Instance> getAllInstancesNotInsertIntoServer()
    {
        return daoSession.getInstanceDao()
                .queryBuilder()
                .where(InstanceDao.Properties.ActionIntoServer.eq("insert")).list();
    }

    public List<Instance> getAllInstancesNotUpdateIntoServer()
    {
        return daoSession.getInstanceDao()
                .queryBuilder()
                .where(InstanceDao.Properties.ActionIntoServer.eq("update")).list();
    }

    public List<Instance> getAllInstancesNotDeleteIntoServer()
    {
        return daoSession.getInstanceDao()
                .queryBuilder()
                .where(InstanceDao.Properties.ActionIntoServer.eq("delete")).list();
    }

    public void setToBeInsert(Long id) throws ClassNotFoundException {
        Instance instance = this.get(Instance.class,id);
        instance.setActionIntoServer("insert");
        daoSession.getInstanceDao().update(instance);
    }

    public void setToBeNotInsert(Long id) throws ClassNotFoundException {
        Instance instance = this.get(Instance.class,id);
        instance.setActionIntoServer(null);
        daoSession.getInstanceDao().update(instance);
    }

    public boolean isToBeInsert(Long id) throws ClassNotFoundException {
        Instance instance = this.get(Instance.class,id);
        return instance.getActionIntoServer().equals("insert")?true:false;
    }



    public void setToBeUpdate(Long id) throws ClassNotFoundException {
        Instance instance = this.get(Instance.class,id);
        instance.setActionIntoServer("update");
        daoSession.getInstanceDao().update(instance);
    }

    public void setToBeNotUpdate(Long id) throws ClassNotFoundException {
        Instance instance = this.get(Instance.class,id);
        instance.setActionIntoServer(null);
        daoSession.getInstanceDao().update(instance);
    }

    public boolean isToBeUpdate(Long id) throws ClassNotFoundException {
        Instance instance = this.get(Instance.class,id);
        return instance.getActionIntoServer().equals("update")?true:false;
    }

    public void setToBeDelete(Long id) throws ClassNotFoundException {
        Instance instance = this.get(Instance.class,id);
        instance.setActionIntoServer("delete");
        daoSession.getInstanceDao().update(instance);
    }

    public void setToBeNotDelete(Long id) throws ClassNotFoundException {
        Instance instance = this.get(Instance.class,id);
        instance.setActionIntoServer(null);
        daoSession.getInstanceDao().update(instance);
    }

    public boolean isToBeDelete(Long id) throws ClassNotFoundException {
        Instance instance = this.get(Instance.class,id);
        return instance.getActionIntoServer().equals("delete")?true:false;
    }

}
