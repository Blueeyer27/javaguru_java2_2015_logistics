package lv.javaguru.java2.servlet.mvc;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.UserDAO;
import lv.javaguru.java2.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;


@RunWith(MockitoJUnitRunner.class)
public class UserRegResultControllerTest {

    @Mock
    private UserDAO userDAO;

    @InjectMocks
    private UserRegResultController controller = new UserRegResultController();



    @Test
    public void shouldCreateUserInDB() throws DBException {
        User user = createUser("ABC", "123");
        Mockito.doNothing().when(userDAO).create(user);
    }


    @Test
    public void shouldReturnUserFromDB() throws DBException {

        User user = createUser("A", "1");

        Mockito.doNothing().when(userDAO).create(user);
//        Mockito.doReturn(user).when(userDAO).getById(user.getUserId());
//
        User userFromDB = controller.getNewUserFromDB(user.getUserId());

//       assertEquals(user == userFromDB, true);
//        assertNotNull(user);

    }



    private User createUser(String login, String password) {
        User userMock = Mockito.mock(User.class);
        Mockito.doNothing().when(userMock).setLogin(login);
        Mockito.doNothing().when(userMock).setPassword(password);
//        Mockito.doReturn(login).when(userMock).setLogin(login);
//        Mockito.doReturn(password).when(userMock).setPassword(password);
        return userMock;
    }

}
