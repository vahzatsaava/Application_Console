package company.repository;

import java.io.IOException;
import java.util.List;

public interface GenericRepository<T, ID> {
    T getById(ID id) throws IOException;
    List<T> getAll() throws IOException;
    void save(T t) throws IOException;
    T update(T t) throws IOException;
    void deleteById(ID id) throws IOException;
}
