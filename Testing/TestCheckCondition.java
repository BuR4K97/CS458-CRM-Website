import models.*;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.*;

public class TestCheckCondition {

    private User user;
    private DailySymptoms dailySymptoms;
    private Symptoms symptoms;

    @Before
    public void init(){
        user = new User("kenneth@gmail.com", "329129293", "Kenneth K", 28, Gender.MALE);
        dailySymptoms = new DailySymptoms(user, LocalDate.now(), new Symptoms(36.5f, Coughing.LOW, Headache.NONE,
                false, false, false, false, false));
        Tools.login(user);
    }

    @Test
    public void healty(){
        Tools.registerDailySymptoms(dailySymptoms);
        assertEquals(Condition.HEALTHY, Tools.checkCondition(user));
    }

    @Test
    public void chestPainSevere(){
        symptoms.setChestPain(true);
        dailySymptoms.setSymptoms(symptoms);
        Tools.registerDailySymptoms(dailySymptoms);
        assertEquals(Condition.SEVERE, Tools.checkCondition(user));
    }

    @Test
    public void breatheDifficultySevere(){
        symptoms.setChestPain(false);
        symptoms.setBreatheDifficulty(true);
        dailySymptoms.setSymptoms(symptoms);
        Tools.registerDailySymptoms(dailySymptoms);
        assertEquals(Condition.SEVERE, Tools.checkCondition(user));
    }

    @Test
    public void dizzinessEarlyCovid(){
        symptoms.setBreatheDifficulty(false);
        symptoms.setDizziness(true);
        dailySymptoms.setSymptoms(symptoms);
        Tools.registerDailySymptoms(dailySymptoms);
        assertEquals(Condition.EARLY_COVID, Tools.checkCondition(user));
    }

    @Test
    public void tasteLossEarlyCovid(){
        symptoms.setDizziness(false);
        symptoms.setTasteLoss(true);
        dailySymptoms.setSymptoms(symptoms);
        Tools.registerDailySymptoms(dailySymptoms);
        assertEquals(Condition.EARLY_COVID, Tools.checkCondition(user));
    }

    @Test
    public void headAcheLowEarlyCovid(){
        symptoms.setTasteLoss(false);
        symptoms.setHeadache(Headache.LOW);
        dailySymptoms.setSymptoms(symptoms);
        Tools.registerDailySymptoms(dailySymptoms);
        assertEquals(Condition.EARLY_COVID, Tools.checkCondition(user));
    }

    @Test
    public void headAcheHighEarlyCovid(){
        symptoms.setHeadache(Headache.HIGH);
        dailySymptoms.setSymptoms(symptoms);
        Tools.registerDailySymptoms(dailySymptoms);
        assertEquals(Condition.EARLY_COVID, Tools.checkCondition(user));
    }

    @Test
    public void highFeverInRisk(){
        symptoms.setHeadache(Headache.NONE);
        symptoms.setFever(38.0f);
        dailySymptoms.setSymptoms(symptoms);
        Tools.registerDailySymptoms(dailySymptoms);
        assertEquals(Condition.IN_RISK, Tools.checkCondition(user));
    }

    @Test
    public void lowCoughingInRisk(){
        symptoms.setFever(37.0f);
        symptoms.setCoughing(Coughing.LOW);
        dailySymptoms.setSymptoms(symptoms);
        Tools.registerDailySymptoms(dailySymptoms);
        assertEquals(Condition.IN_RISK, Tools.checkCondition(user));
    }

    @Test
    public void highCoughingInRisk(){
        symptoms.setCoughing(Coughing.HIGH);
        dailySymptoms.setSymptoms(symptoms);
        Tools.registerDailySymptoms(dailySymptoms);
        assertEquals(Condition.IN_RISK, Tools.checkCondition(user));
    }

    @Test
    public void quickTiringingInRisk(){
        symptoms.setCoughing(Coughing.NONE);
        symptoms.setQuickTiring(true);
        dailySymptoms.setSymptoms(symptoms);
        Tools.registerDailySymptoms(dailySymptoms);
        assertEquals(Condition.IN_RISK, Tools.checkCondition(user));
    }


}
