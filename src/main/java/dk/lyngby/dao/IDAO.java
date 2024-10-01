package dk.lyngby.dao;

import java.util.List;

public interface IDAO<T> {
    public List<T> getAll();
    public T getById(long id);

    public void create(T t);
    public void update(T t, T newT);
    public void delete(long id);
}
