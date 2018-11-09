package com.example.pc.mbaas_api.ClientControllerClasses.entities;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

import org.greenrobot.greendao.annotation.ToMany;


import java.util.List;
import org.greenrobot.greendao.DaoException;

@Entity(nameInDb = "Objects")
public class Obj {

    @Id(autoincrement = true)
    private Long id;
    private String name;
    @ToMany(referencedJoinProperty = "idObject")
    private List<Field> fields;
    @ToMany(referencedJoinProperty = "idObject")
    private List<Instance> instances;
    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;
    /** Used for active entity operations. */
    @Generated(hash = 1003201405)
    private transient ObjDao myDao;

    @Generated(hash = 1815567231)
    public Obj(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    @Generated(hash = 1019705847)
    public Obj() {
    }

    public void AddField(Field field)
    {
        this.fields.add(field);
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 438799073)
    public List<Field> getFields() {
        if (fields == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            FieldDao targetDao = daoSession.getFieldDao();
            List<Field> fieldsNew = targetDao._queryObj_Fields(id);
            synchronized (this) {
                if (fields == null) {
                    fields = fieldsNew;
                }
            }
        }
        return fields;
    }
    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 170366910)
    public synchronized void resetFields() {
        fields = null;
    }
    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 132518932)
    public List<Instance> getInstances() {
        if (instances == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            InstanceDao targetDao = daoSession.getInstanceDao();
            List<Instance> instancesNew = targetDao._queryObj_Instances(id);
            synchronized (this) {
                if (instances == null) {
                    instances = instancesNew;
                }
            }
        }
        return instances;
    }
    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 92747055)
    public synchronized void resetInstances() {
        instances = null;
    }
    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 128553479)
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.delete(this);
    }
    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 1942392019)
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.refresh(this);
    }
    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 713229351)
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.update(this);
    }
    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 1770434180)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getObjDao() : null;
    }


}
