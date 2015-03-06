package lv.javaguru.java2.servlet.mvc;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.UserDAO;
import lv.javaguru.java2.domain.User;


@RunWith(MockitoJUnitRunner.class)
public class UserLoginControllerTest {

    @Mock
    private UserDAO userDAO;

    @InjectMocks
    private UserLoginController controller = new UserLoginController();


    @Test
    public void shouldReturnUserFromDatabase() throws DBException {
        User user = createUser("A", "$2a$12$0EDKOqTMDTZYapdsRdgbR.Xy99qjaGbrE83y0sqZiN/b6irB9ht1m");
        Mockito.doReturn(user).when(userDAO).getByLogin("A");

        User userFromDB = controller.getUserIfExist("A",
                "$2a$12$0EDKOqTMDTZYapdsRdgbR.Xy99qjaGbrE83y0sqZiN/b6irB9ht1m"); // 123 hash
        assertEquals(user == userFromDB, true);
    }

    private User createUser(String login, String password) {
        User userMock = Mockito.mock(User.class);
        Mockito.doReturn(login).when(userMock).getLogin();
        Mockito.doReturn(password).when(userMock).getPassword();
        return userMock;
        /*
        User user = new User();
        user.setLogin(login);
        user.setPassword(password);
        return user;*/
    }

}
