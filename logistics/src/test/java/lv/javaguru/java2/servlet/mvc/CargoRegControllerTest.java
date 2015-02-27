package lv.javaguru.java2.servlet.mvc;

import lv.javaguru.java2.database.CargoDAO;
import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.UserDAO;
import lv.javaguru.java2.domain.Cargo;
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


@RunWith(MockitoJUnitRunner.class)
public class CargoRegControllerTest {

    @Mock
    private UserDAO userDAO;

    @InjectMocks
    private CargoRegController controller = new CargoRegController();


    @Test
    public void shouldReturnUserListFromDatabase() throws DBException {
        List<User> users = new ArrayList<User>();
        users.add(createUser("A", "A"));
        users.add(createUser("B", "B"));
        users.add(createUser("B", "B"));

        Mockito.doReturn(users).when(userDAO).getAll();

        List<User> usersFromDB = controller.getUserListFromDatabase();
        assertEquals(users == usersFromDB, true);
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
