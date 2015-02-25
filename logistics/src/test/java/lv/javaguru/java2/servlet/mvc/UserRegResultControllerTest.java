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


@RunWith(MockitoJUnitRunner.class)
public class UserRegResultControllerTest {

    @Mock
    private UserDAO userDAO;

    @InjectMocks
    private UserRegResultController controller = new UserRegResultController();


    @Test
    public void shouldCreateNewUserInDB() throws DBException {
        User user = createUser("ABC", "123");

        Mockito.doReturn(user).when(userDAO).getById(user.getUserId());

        User userToDB = controller.createNewUserInDB(user);

        assertEquals(user == userToDB, true);
        assertNotNull(userToDB);

/*
        User userFromDB = userDAO.getById(user.getUserId());
        assertEquals(user.getUserId(), userFromDB.getUserId());
*/


    }

    private User createUser(String login, String password) {
/*
        User userMock = Mockito.mock(User.class);
        Mockito.doReturn(login).when(userMock).setLogin(login);
        Mockito.doReturn(password).when(userMock).setPassword(password);
        return userMock;
*/
        User user = new User();
        user.setLogin(login);
        user.setPassword(password);
        return user;
    }

}
