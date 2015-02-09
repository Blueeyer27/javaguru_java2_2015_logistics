package lv.javaguru.java2.database;

import lv.javaguru.java2.domain.Cargo;
import lv.javaguru.java2.domain.User;

import java.util.List;

/**
 * Created by Dinjvald on 07/02/15.
 */
public interface CargoDAO {

    void create (Cargo cargo, User user) throws DBException;

    Cargo getById(Long id) throws DBException;

    void delete(Long id) throws DBException;

    void update(Cargo cargo) throws DBException;

    List<Cargo> getAll() throws DBException;

}
