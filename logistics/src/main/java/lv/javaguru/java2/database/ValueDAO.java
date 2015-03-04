package lv.javaguru.java2.database;

import lv.javaguru.java2.domain.Value;
import java.util.List;

public interface ValueDAO extends BaseDAO<Value> {

    List<Value> getLovByType(String type) throws DBException;

}
