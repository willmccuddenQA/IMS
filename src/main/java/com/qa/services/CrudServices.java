package com.qa.services;

import java.util.List;

public interface CrudServices<T> {

    public List<T> readAll();
     
    void create(T t);
     
    void update(long id, T t);
 
    void delete(T t);

}
