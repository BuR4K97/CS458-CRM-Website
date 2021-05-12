import java.time.LocalDate;

import models.User;
import models.Gender;
import models.Headache;
import models.Coughing;
import models.DailySymptoms;
import models.Symptoms;

public class Test 
{
    public static void main(String[] args) 
    {
        User burak = new User().setEmail("burak@gmail.com").setPassword("123456").setName("Burak").setAge(23).setGender(Gender.MALE);
        DailySymptoms symptoms = new DailySymptoms().setUser(burak).setDate(LocalDate.now()).setSymptoms
        (
            new Symptoms().setFever(36.5f).setCoughing(Coughing.LOW).setHeadache(Headache.NONE).setDizziness(false)
                    .setTasteLoss(false).setBreatheDifficulty(false).setChestPain(false).setQuickTiring(false)
        );

        System.out.println(Tools.signin(burak));
        System.out.println(Tools.signup(burak));
        System.out.println(Tools.registerSymptoms(symptoms));
        System.out.println(Tools.checkCondition(burak));
    }
}