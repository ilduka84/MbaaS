package com.example.pc.mbaas_api.ClientControllerClasses.entities;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.ToOne;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.NotNull;

@Entity(nameInDb = "Instances")
public class Instance {

    @Id(autoincrement = true)
    private Long id;

    private Long idServer;

    private long idObject;
    @ToOne(joinProperty = "idObject")
    private Obj object;

    private String actionIntoServer;
    
    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    @Generated(hash = 747789162)
    private transient InstanceDao myDao;
    @Generated(hash = 214185628)
    public Instance(Long id, Long idServer, long idObject, String actionIntoServer) {
        this.id = id;
        this.idServer = idServer;
        this.idObject = idObject;
        this.actionIntoServer = actionIntoServer;
    }
    @Generated(hash = 1918071548)
    public Instance() {
    }
    public Instance(Obj object)
    {
        this.setActionIntoServer(null);
        this.object = object;

    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getIdServer() {
        return this.idServer;
    }
    public void setIdServer(Long idServer) {
        this.idServer = idServer;
    }
    public long getIdObject() {
        return this.idObject;
    }
    public void setIdObject(long idObject) {
        this.idObject = idObject;
    }
    @Generated(hash = 1704558231)
    private transient Long object__resolvedKey;
    /** To-one relationship, resolved on first access. */

    @Generated(hash = 394376505)
    public Obj getObject() {
        long __key = this.idObject;
        if (object__resolvedKey == null || !object__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            ObjDao targetDao = daoSession.getObjDao();
            Obj objectNew = targetDao.load(__key);
            synchronized (this) {
                object = objectNew;
                object__resolvedKey = __key;
            }
        }
        return object;
    }
    public Obj getObjectFromCache(){return this.object;}
    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 1936313543)
    public void setObject(@NotNull Obj object) {
        if (object == null) {
            throw new DaoException(
                    "To-one property 'idObject' has not-null constraint; cannot set to-one to null");
        }
        synchronized (this) {
            this.object = object;
            idObject = object.getId();
            object__resolvedKey = idObject;
        }
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
    public String getActionIntoServer() {
        return this.actionIntoServer;
    }
    public void setActionIntoServer(String actionIntoServer) {
        this.actionIntoServer = actionIntoServer;
    }
    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 158792113)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getInstanceDao() : null;
    }

}
