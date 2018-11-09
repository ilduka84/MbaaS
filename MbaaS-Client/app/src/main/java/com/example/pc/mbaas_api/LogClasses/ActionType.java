package com.example.pc.mbaas_api.LogClasses;

public class ActionType {

    private boolean isPut;
    private boolean isUpdate;
    private boolean isDelete;

    public ActionType()
    {
        isPut = false;
        isUpdate = false;
        isDelete = false;
    }

    public void setPut(boolean put) {
        isPut = put;
    }

    public void setUpdate(boolean update) {
        isUpdate = update;
    }

    public void setDelete(boolean delete) {
        isDelete = delete;
    }

    public boolean isPut() {
        return isPut;
    }

    public boolean isDelete() {
        return isDelete;
    }

    public boolean isUpdate() {
        return isUpdate;
    }
}
