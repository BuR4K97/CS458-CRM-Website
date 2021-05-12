import models.*;
import org.junit.*;

import static org.junit.Assert.*;

public class TestSignUp 
{
    private User user;

    @Before
    public void init()
    {
        user = new User("burak@gmail.com", "123456", "Burak Mutlu", 23, Gender.MALE);
    }

    @Test
    public void validSignUp()
    {
        UserResult result = Tools.signup(user);
        assertNotNull(result.getUser().getEmail());
        assertEquals(user.getEmail(), result.getUser().getEmail());
    }

    @Test
    public void emailRegistered()
    {
        UserResult result = Tools.signup(user);
        assertNull(result.getUser());
        assertEquals("This e-mail is already registered. Please try login.", result.getMessage());
    }

}