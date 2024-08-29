package ru.cardinalnsk.jooq_test.repo;

import java.util.List;
import org.jooq.Condition;

public interface CrudRepository<T> {
    Integer SUCCESS_CODE = 1;
    T insert(T entity);

    T find(Long id);

    T update(T entity);

    List<T> findAll(Condition condition);
    Boolean delete(Long id);

}
