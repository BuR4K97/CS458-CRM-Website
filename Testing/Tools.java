import models.User;

import java.io.IOException;

import helper.HTTPConnection;
import helper.JSONHandler;
import models.Condition;
import models.DailySymptoms;
import org.json.simple.JSONObject;

public class Tools 
{
    public static boolean signin(User user) 
    {
        try 
        {
            HTTPConnection connection = new HTTPConnection("http://localhost:3002/test/signin");
            String result = connection.sendRequest(JSONHandler.generateJSON(user));
            return JSONHandler.extractResult(result);
        } 
        catch (IOException exception) 
        {
            exception.printStackTrace();
        }
        return false;
    }

    public static JSONObject signup(User user)
    {
        try 
        {
            HTTPConnection connection = new HTTPConnection("http://localhost:3002/test/signup");
            String result = connection.sendRequest(JSONHandler.generateJSON(user));
            return JSONHandler.parse(result);
        } 
        catch (IOException exception) 
        {
            exception.printStackTrace();
        }
        return null;
    }

    public static boolean registerSymptoms(DailySymptoms dailySymptoms) 
    {
        try 
        {
            HTTPConnection connection = new HTTPConnection("http://localhost:3002/test/registerSymptoms");
            String result = connection.sendRequest(JSONHandler.generateJSON(dailySymptoms));
            return JSONHandler.extractResult(result);
        } 
        catch (IOException exception) 
        {
            exception.printStackTrace();
        }
        return false;
    }

    public static Condition checkCondition(User user) 
    {
        try 
        {
            HTTPConnection connection = new HTTPConnection("http://localhost:3002/test/checkCondition");
            String result = connection.sendRequest(JSONHandler.generateJSON(user));
            return JSONHandler.extractCondition(result);
        } 
        catch (IOException exception) 
        {
            exception.printStackTrace();
        }
        return null;
    }
}
