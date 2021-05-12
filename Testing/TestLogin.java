import models.Gender;
import models.User;
import org.json.simple.JSONObject;
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

        JSONObject json = Tools.signin(user);

        assertTrue(json.containsKey("email"));
        assertEquals(user.getEmail(), json.get("email"));
    }

    @Test
    public void wrongPassword(){
        user.setEmail("burak@gmail.com");
        user.setPassword("999");

        JSONObject json = Tools.signin(user);

        assertFalse(json.containsKey("email"));
        assertEquals("Wrong password. Please try again or reset your password.", json.get("message"));
    }

    @Test
    public void unregisteredEmail(){
        user.setEmail("buraktest@gmail.com");
        user.setPassword("999");

        JSONObject json = Tools.signin(user);

        assertFalse(json.containsKey("email"));
        assertEquals("There is no such account. Please try signing up.", json.get("message"));
    }
}
