package com.example.pc.mbaas_api.ClientControllerClasses.entities;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Keep;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.ToOne;
import org.greenrobot.greendao.DaoException;

@Entity(nameInDb = "Values")
public class Value {

    @Id(autoincrement = true)
    private Long id;

    private long idField;
    @ToOne(joinProperty = "idField")
    private Field field;

    private long idInstance;
    @ToOne(joinProperty = "idInstance")
    private Instance instance;

    private String value;

    private Long idValueInstance;
    @ToOne(joinProperty = "idValueInstance")
    private Instance valueInstance;

    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    @Generated(hash = 765159173)
    private transient ValueDao myDao;
    @Generated(hash = 671183978)
    public Value(Long id, long idField, long idInstance, String value, Long idValueInstance) {
        this.id = id;
        this.idField = idField;
        this.idInstance = idInstance;
        this.value = value;
        this.idValueInstance = idValueInstance;
    }
    @Generated(hash = 318810525)
    public Value() {
    }

    @Keep
    public Value(Field field, Instance instance, String value )
    {
        this.instance = instance;
        this.field = field;
        this.field.setValue(this);
        this.value = value;
        this.valueInstance = null;
    }

    public Value(Field field, Instance instance, Instance value )
    {
        this.instance = instance;
        this.field = field;
        this.field.setValue(this);
        this.valueInstance = value;
        this.value = null;
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public long getIdField() {
        return this.idField;
    }
    public void setIdField(long idField) {
        this.idField = idField;
    }

    public String getValue() {
        return this.value;
    }



    public void setValue(String value) {
        this.value = value;
    }

    public Long getIdValueInstance() {
        return this.idValueInstance;
    }
    public void setIdValueInstance(Long idValueInstance) {
        this.idValueInstance = idValueInstance;
    }
    @Generated(hash = 338712148)
    private transient Long field__resolvedKey;
    /** To-one relationship, resolved on first access. */
    @Generated(hash = 418723214)
    public Field getField() {
        long __key = this.idField;
        if (field__resolvedKey == null || !field__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            FieldDao targetDao = daoSession.getFieldDao();
            Field fieldNew = targetDao.load(__key);
            synchronized (this) {
                field = fieldNew;
                field__resolvedKey = __key;
            }
        }
        return field;
    }
    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 883590137)
    public void setField(@NotNull Field field) {
        if (field == null) {
            throw new DaoException(
                    "To-one property 'idField' has not-null constraint; cannot set to-one to null");
        }
        synchronized (this) {
            this.field = field;
            idField = field.getId();
            field__resolvedKey = idField;
        }
    }
    @Generated(hash = 767870968)
    private transient Long valueInstance__resolvedKey;

    @Generated(hash = 1444639356)
    private transient Long instance__resolvedKey;
    /** To-one relationship, resolved on first access. */

    @Generated(hash = 1846666058)
    public Instance getValueInstance() {
        Long __key = this.idValueInstance;
        if (valueInstance__resolvedKey == null
                || !valueInstance__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            InstanceDao targetDao = daoSession.getInstanceDao();
            Instance valueInstanceNew = targetDao.load(__key);
            synchronized (this) {
                valueInstance = valueInstanceNew;
                valueInstance__resolvedKey = __key;
            }
        }
        return valueInstance;
    }
    public Instance getValueInstanceFromCache(){return this.valueInstance;}
    /** called by internal mechanisms, do not call yourself. */
    @Keep@Generated(hash = 1502123449)
    public void setValue(Instance valueInstance) {
        this.setValueInstance(valueInstance);
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
    public long getIdInstance() {
        return this.idInstance;
    }
    public void setIdInstance(long idInstance) {
        this.idInstance = idInstance;
    }
    /** To-one relationship, resolved on first access. */
    @Generated(hash = 678096755)
    public Instance getInstance() {
        long __key = this.idInstance;
        if (instance__resolvedKey == null || !instance__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            InstanceDao targetDao = daoSession.getInstanceDao();
            Instance instanceNew = targetDao.load(__key);
            synchronized (this) {
                instance = instanceNew;
                instance__resolvedKey = __key;
            }
        }
        return instance;
    }
    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 981104984)
    public void setInstance(@NotNull Instance instance) {
        if (instance == null) {
            throw new DaoException(
                    "To-one property 'idInstance' has not-null constraint; cannot set to-one to null");
        }
        synchronized (this) {
            this.instance = instance;
            idInstance = instance.getId();
            instance__resolvedKey = idInstance;
        }
    }
    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 1502123449)
    public void setValueInstance(Instance valueInstance) {
        synchronized (this) {
            this.valueInstance = valueInstance;
            idValueInstance = valueInstance == null ? null : valueInstance.getId();
            valueInstance__resolvedKey = idValueInstance;
        }
    }
    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 2122403398)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getValueDao() : null;
    }

    
}
