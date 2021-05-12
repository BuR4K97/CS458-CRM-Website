import models.*;
import org.json.simple.JSONObject;
import org.junit.*;

import static org.junit.Assert.*;

public class TestSignUp 
{
    private User user;
    //private DailySymptoms symptoms;

    @Before
    public void init()
    {
        user = new User("burak@gmail.com", null, "123456", "Burak Mutlu", 23, Gender.MALE);
        //symptoms = new DailySymptoms(burak, LocalDate.now(), new Symptoms(36.5f, Coughing.LOW, Headache.NONE,
        //                false, false, false, false, false));
    }

    @Test
    public void validSignUp()
    {
        JSONObject json = Tools.signup(user);
        assertTrue(json.containsKey("email"));
        assertEquals(user.getEmail(), json.get("email"));
    }

    @Test
    public void emailRegistered(){
        JSONObject json = Tools.signup(user);
        assertFalse(json.containsKey("email"));
        assertEquals("This e-mail is already registered. Please try signing in.", json.get("message"));
    }

    @Test
    public void phoneRegistered(){
        user.setPhone("5554444444");
        user.setEmail("buraktest@gmail.com");
        JSONObject json = Tools.signup(user);
        assertFalse(json.containsKey("email"));
        assertEquals("This phone number is already registered. Please try signing in.", json.get("message"));
    }

}