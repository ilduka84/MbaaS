package com.example.pc.mbaas_api;

import com.example.pc.mbaas_api.LogClasses.ActionType;

import java.sql.Timestamp;

public class Log {

    private Long idInstance;
    private Timestamp time;
    private ActionType action;

    public Log(Long idInstance,ActionType action)
    {
        this.idInstance = idInstance;
        this.action = action;
        this.time = new Timestamp((int)System.currentTimeMillis());
    }

    public Long getIdInstance() {
        return idInstance;
    }

    public ActionType getAction() {
        return action;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setAction(ActionType action) {
        this.action = action;
    }

    public void setIdInstance(Long idInstance) {
        this.idInstance = idInstance;
    }

}
