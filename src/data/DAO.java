package src.data;


import java.io.Serializable;
import java.util.List;

public interface DAO<T> {
    
    T get(Serializable id) throws NotFoundInDBException;
    
    List<T> getAll();
    
    void save(T t) throws IdentifierAlreadyInDBException;
    
    void update(T t) throws NotFoundInDBException;
    
    void delete(T t) throws NotFoundInDBException;

    boolean containsID(Serializable id);
}
