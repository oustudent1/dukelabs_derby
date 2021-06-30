package duke.labs.jpa.daos;

import java.util.Collection;
import java.util.Optional;

public interface Dao<T> {

    Optional<T> findById(int id);

    Optional<Collection<T>> findByName(String name);

    Collection<T> getAll();

    T save(T t);

    void delete(T t);


}
