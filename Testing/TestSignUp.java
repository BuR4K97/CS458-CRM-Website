import models.*;
import org.junit.*;

import static org.junit.Assert.*;

public class TestSignUp 
{
    private User user;
    //private DailySymptoms symptoms;

    @Before
    public void init()
    {
        user = new User("burak@gmail.com", "123456", "Burak Mutlu", 23, Gender.MALE);
        //symptoms = new DailySymptoms(burak, LocalDate.now(), new Symptoms(36.5f, Coughing.LOW, Headache.NONE,
        //                false, false, false, false, false));
    }

    @Test
    public void validSignUp()
    {
        UserResult result = Tools.signup(user);
        assertNotNull(result.user.email);
        assertEquals(user.email, result.user.email);
    }

    @Test
    public void emailRegistered()
    {
        UserResult result = Tools.signup(user);
        assertNull(result.user.email);
        assertEquals("This e-mail is already registered. Please try signing in.", result.message);
    }

}