package lv.javaguru.java2.servlet;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.jdbc.CompanyDAOImpl;
import lv.javaguru.java2.domain.Company;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class AllCompaniesServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        resp.setContentType("text/html");
        // SOME DEFAULT TEST DATA
        Company company1 = new Company("FirstCompany", "asdf1234567890", "Riga, registred",
                "Riga, sdfdfsdfdsf", "FIGBANK", "BLABLA100500", "Latvia", "Transporter");
        Company company2 = new Company("SecondCompany", "222", "Barcelona, registred",
                "Riga, actual 2", "ABC", "666", "Spain", "Transporter");
        Company company3 = new Company("ThirdCompany", "333", "Moscow, registred",
                "Saint-P., actual 3", "Sberbank", "SBER100500", "Russia", "Transporter");
        CompanyDAOImpl companyDAO = new CompanyDAOImpl();
        List<Company> allCompanies = null;

        try {
            companyDAO.create(company1);
            companyDAO.create(company2);
            companyDAO.create(company3);
            allCompanies = companyDAO.getAll();
        } catch(DBException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < allCompanies.size(); i++) {
            out.println("<h1>" + allCompanies.get(i).getName() + "</h1>");
        }
    }
}
