package helper;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import models.Condition;
import models.DailySymptoms;
import models.Symptoms;
import models.User;

public class JSONHandler 
{

    public static String generateJSON(User user) 
    {
        JSONObject userJSON = new JSONObject();
        userJSON.put("email", user.email);
        userJSON.put("password", user.password);
        userJSON.put("name", user.name);
        userJSON.put("age", user.age);
        userJSON.put("gender", user.gender.ordinal());
        return userJSON.toJSONString();
    }

    public static String generateJSON(DailySymptoms dailySymptoms) 
    {
        Symptoms symptoms = dailySymptoms.symptoms;
        JSONObject symptomsJSON = new JSONObject();
        symptomsJSON.put("fever", symptoms.fever);
        symptomsJSON.put("coughing", symptoms.coughing.ordinal());
        symptomsJSON.put("headache", symptoms.headache.ordinal());
        symptomsJSON.put("dizziness", symptoms.dizziness);
        symptomsJSON.put("taste-loss", symptoms.tasteLoss);
        symptomsJSON.put("breathe-difficulty", symptoms.breatheDifficulty);
        symptomsJSON.put("chest-pain", symptoms.chestPain);
        symptomsJSON.put("quick-tiring", symptoms.quickTiring);

        JSONObject dailySymptomsJSON = new JSONObject();
        dailySymptomsJSON.put("email", dailySymptoms.user.email);
        dailySymptomsJSON.put("date", dailySymptoms.date.toString());
        dailySymptomsJSON.put("symptoms", symptomsJSON);
        return dailySymptomsJSON.toJSONString();
    }

    public static boolean extractResult(String text) 
    {
        JSONObject json = JSONHandler.parse(text);
        return Boolean.parseBoolean(json.get("result").toString());
    }

    public static Condition extractCondition(String text) 
    {
        JSONObject json = JSONHandler.parse(text);
        return Condition.values()[Integer.parseInt(json.get("condition").toString())];
    }

    private static JSONParser parser = new JSONParser();
    private static JSONObject parse(String text)
    {
        try
        {
            return (JSONObject) parser.parse(text);
        }
        catch (ParseException exception)
        {
            exception.printStackTrace();
        }
        return null;
    }

}