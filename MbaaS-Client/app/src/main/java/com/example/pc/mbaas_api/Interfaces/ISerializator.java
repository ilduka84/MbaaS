package com.example.pc.mbaas_api.Interfaces;

public interface ISerializator {

   public <T>String to(T object);
   public <T>T from(String data, Class<T> type);
}
