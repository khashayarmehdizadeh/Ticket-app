package mft.model.tools;

import mft.model.entity.Customer;
import mft.model.entity.Event;

import java.sql.Timestamp;
import java.util.List;

public interface CRUD<T> {
    T save(T t) throws Exception;
    T edit(T t) throws Exception;
    T remove(int id) throws Exception;
    List<T> findAll()throws Exception;
    T findById(int id)throws Exception;


    //    todo : findByDateTime
    Event findByCategory(String category) throws Exception;

    //    todo : findByCategory
    Event findByDateTime(Timestamp datetime) throws Exception;
}
