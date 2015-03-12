package lv.javaguru.java2.servlet.mvc;

import lv.javaguru.java2.database.CompanyDAO;
import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.UserDAO;
import lv.javaguru.java2.domain.Company;
import lv.javaguru.java2.domain.User;
import lv.javaguru.java2.servlet.model.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by user on 19.02.2015.
 */
@Component
@URL(value="/getallcompanywithuserlist")

//@Transactional
public class GetAllCompanyWithUserListController implements MVCController {

    @Autowired
    @Qualifier("HibCompanyDAO")
    private CompanyDAO companyDAO;

    @Autowired
    @Qualifier("HibernateUserDAO")
    private UserDAO userDAO;

    @Override
    public MVCModel processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {


        Random rn = new Random();
        int nr =rn.nextInt(100000);int nr1=nr+1;int nr2=nr+2;int nr3=nr+3;
        System.out.println(nr + " " + nr1 + " " + nr2 + " " + nr3 );


        Company company1 = new Company("CompanyCargo"+nr, "123", "Reg address 1", "Actual address 2", "Hansabank", "HABA21", "Latvia", "cargo");
        try {
            companyDAO.create(company1);
        } catch (DBException e) {
            System.out.println("Exception while creating new company or new users GetAllCompanyWithUserListController");
            e.printStackTrace();
        }

        User user1 = new User("user"+nr1, "pass1", "Foo", "Barsky", "fb@email.com", "+371234567890", company1);
        User user2 = new User("user"+nr2, "pass1", "Foo", "Barsky", "fb@email.com", "+371234567890", company1);
        User user3 = new User("user"+nr3, "pass1", "Foo", "Barsky", "fb@email.com", "+371234567890", company1);
        try {
            userDAO.create(user1);
            userDAO.create(user2);
            userDAO.create(user3);
        } catch (DBException e) {
            System.out.println("Exception while creating new company or new users GetAllCompanyWithUserListController");
            e.printStackTrace();
        }



        List<Company> companyList = new ArrayList<Company>();
        try {
            companyList = companyDAO.getAll();
        } catch (DBException e) {
            e.printStackTrace();
        }

        return new MVCModel("/jsp/getAllCompanyWithUserList.jsp", companyList);
    }
}
