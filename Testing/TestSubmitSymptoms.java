import models.*;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.*;

public class TestSubmitSymptoms {

    private  User user;

    @Test
    public void validSymptoms(){
        user = new User("kenneth@gmail.com", "329129293", "Kenneth K", 28, Gender.MALE);
        DailySymptoms symptoms = new DailySymptoms(user, LocalDate.now(), new Symptoms(36.5f, Coughing.LOW, Headache.NONE,
                false, false, false, false, false));

        Tools.login(user);
        Result result = Tools.registerDailySymptoms(symptoms);
        assertTrue(result.getResult());
        assertEquals("Success", result.getMessage());
    }

    @Test
    public void unregisteredSubmit(){
        user = new User("k@gmail.com", "329129293", "Kenneth K", 28, Gender.MALE);
        DailySymptoms symptoms = new DailySymptoms(user, LocalDate.now(), new Symptoms(36.5f, Coughing.LOW, Headache.NONE,
                false, false, false, false, false));
        Result result = Tools.registerDailySymptoms(symptoms);
        assertFalse(result.getResult());
        assertEquals("Invalid user", result.getMessage());
    }
}
