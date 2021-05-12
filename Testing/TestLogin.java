import models.Gender;
import models.User;
import models.UserResult;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestLogin {

    private User user;

    @Before
    public void init(){
        user = new User();
        user.setName("Burak Mutlu");
        user.setAge(23);
        user.setGender(Gender.MALE);
    }

    @Test
    public void successfulLogin(){
        user.setEmail("burak@gmail.com");
        user.setPassword("123456");

        UserResult result = Tools.login(user);

        assertEquals(user.getEmail(), result.getUser().getEmail());
        assertEquals("Successful Login", result.getMessage());
    }

    @Test
    public void wrongPassword(){
        user.setEmail("burak@gmail.com");
        user.setPassword("999");

        UserResult result = Tools.login(user);

        assertNull(result.getUser());
        assertEquals("Wrong password. Please try again or reset your password.", result.getMessage());
    }

    @Test
    public void unregisteredEmail(){
        user.setEmail("buraktest@gmail.com");
        user.setPassword("999");

        UserResult result = Tools.login(user);

        assertNull(result.getUser());
        assertEquals("There is no such account. Please try signup.", result.getMessage());
    }
}
