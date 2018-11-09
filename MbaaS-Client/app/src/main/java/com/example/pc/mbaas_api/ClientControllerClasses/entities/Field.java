package com.example.pc.mbaas_api.ClientControllerClasses.entities;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.ToOne;


import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.NotNull;



@Entity(nameInDb = "Fields")
public class Field {
    @Id(autoincrement = true)
    private Long id;

    private String name;
    private String type;

    private long idValue;
    @ToOne(joinProperty = "idValue")
    private Value value;

    private long idObject;
    @ToOne(joinProperty = "idObject")
    private Obj obj;

    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    @Generated(hash = 2013929739)
    private transient FieldDao myDao;

    @Generated(hash = 1754212570)
    public Field(Long id, String name, String type, long idValue, long idObject) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.idValue = idValue;
        this.idObject = idObject;
    }

    @Generated(hash = 522955764)
    public Field() {
    }

    public Field(String name, String type, Obj object)
    {
        this.name = name;
        this.type = type;
        this.obj = object;
        this.obj.AddField(this);
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
    public String getType() {
        return this.type;
    }
    public void setType(String type) {
        this.type = type;
    }

    public long getIdObject() {
        return this.idObject;
    }
    public void setIdObject(long idObject) {
        this.idObject = idObject;
    }
    @Generated(hash = 432219228)
    private transient Long value__resolvedKey;
    /** To-one relationship, resolved on first access. */

    @Generated(hash = 297592112)
    public Value getValue() {

        long __key = this.idValue;
        if (value__resolvedKey == null || !value__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            ValueDao targetDao = daoSession.getValueDao();
            Value valueNew = targetDao.load(__key);
            synchronized (this) {
                value = valueNew;
                value__resolvedKey = __key;
            }
        }
        return value;
    }
    public Value getValueFromCache(){return this.value;}
    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 994564335)
    public void setValue(@NotNull Value value) {
        if (value == null) {
            throw new DaoException(
                    "To-one property 'idValue' has not-null constraint; cannot set to-one to null");
        }
        synchronized (this) {
            this.value = value;
            idValue = value.getId();
            value__resolvedKey = idValue;
        }
    }
    @Generated(hash = 101707263)
    private transient Long obj__resolvedKey;
    /** To-one relationship, resolved on first access. */
    @Generated(hash = 834832282)
    public Obj getObj() {
        long __key = this.idObject;
        if (obj__resolvedKey == null || !obj__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            ObjDao targetDao = daoSession.getObjDao();
            Obj objNew = targetDao.load(__key);
            synchronized (this) {
                obj = objNew;
                obj__resolvedKey = __key;
            }
        }
        return obj;
    }
    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 28211917)
    public void setObj(@NotNull Obj obj) {
        if (obj == null) {
            throw new DaoException(
                    "To-one property 'idObject' has not-null constraint; cannot set to-one to null");
        }
        synchronized (this) {
            this.obj = obj;
            idObject = obj.getId();
            obj__resolvedKey = idObject;
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
    public long getIdValue() {
        return this.idValue;
    }
    public void setIdValue(long idValue) {
        this.idValue = idValue;
    }
    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 36217313)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getFieldDao() : null;
    }


}
