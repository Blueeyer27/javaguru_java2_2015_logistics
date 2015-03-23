package lv.javaguru.java2.database;

import lv.javaguru.java2.domain.Company;

import java.util.List;

public interface CompanyDAO extends BaseDAO<Company> {

    List<Company> getAllClientCompanies() throws DBException;

}