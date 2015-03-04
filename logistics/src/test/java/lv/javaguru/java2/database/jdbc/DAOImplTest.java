package lv.javaguru.java2.database.jdbc;

import lv.javaguru.java2.servlet.mvc.SpringAppConfig;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringAppConfig.class)
@TransactionConfiguration(defaultRollback = false)
public class DAOImplTest {

}
