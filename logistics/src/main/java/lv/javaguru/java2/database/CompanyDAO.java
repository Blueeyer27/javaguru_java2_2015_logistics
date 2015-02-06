package lv.javaguru.java2.database;

import lv.javaguru.java2.domain.Company;

import java.util.List;

public interface CompanyDAO {

    void create(Company company) throws DBException;

    Company getById(Long id) throws DBException;

    List<Company> getAll() throws DBException;

    void delete(Long id) throws DBException;

    void update(Company company) throws DBException;

}