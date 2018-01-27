package ua.com.owu.dao;

import ua.com.owu.entity.Book;

import java.util.List;

public interface DaoInterface<T> {

    public void save(T object) throws Exception;
    public void update(T object, int id) throws Exception;
    public void delete(int id);
    public T getById(int id);
    public List<T> getAll();
}
