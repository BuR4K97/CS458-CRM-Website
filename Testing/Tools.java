import models.User;

import java.io.IOException;

import helper.HTTPConnection;
import helper.JSONHandler;
import models.Condition;
import models.DailySymptoms;
import org.json.simple.JSONObject;

public class Tools 
{
    private static final String SERVER_URL = "http://localhost:3002";
    private static final String SIGNIN_ROUTE = "/login";
    private static final String SIGNUP_ROUTE = "/signup"; 
    private static final String REGISTER_SYMPTOMS_ROUTE = "/registerDailySymptoms";
    private static final String CHECK_CONDITION_ROUTE = "/checkCondition";

    public static boolean login(User user) 
    {
        try 
        {
            HTTPConnection connection = new HTTPConnection(SERVER_URL + SIGNIN_ROUTE);
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
            HTTPConnection connection = new HTTPConnection(SERVER_URL + SIGNUP_ROUTE);
            String result = connection.sendRequest(JSONHandler.generateJSON(user));
            return JSONHandler.parse(result);
        } 
        catch (IOException exception) 
        {
            exception.printStackTrace();
        }
        return null;
    }

    public static boolean registerDailySymptoms(DailySymptoms dailySymptoms) 
    {
        try 
        {
            HTTPConnection connection = new HTTPConnection(SERVER_URL + REGISTER_SYMPTOMS_ROUTE);
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
            HTTPConnection connection = new HTTPConnection(SERVER_URL + CHECK_CONDITION_ROUTE);
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
