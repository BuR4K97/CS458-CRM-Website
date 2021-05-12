import models.User;

import java.io.IOException;

import helper.HTTPConnection;
import helper.JSONHandler;
import models.Condition;
import models.DailySymptoms;

public class Tools 
{
    private static final String SERVER_URL = "http://localhost:3002";
    private static final String SIGNIN_ROUTE = "/test/signin";
    private static final String SIGNUP_ROUTE = "/test/signup"; 
    private static final String REGISTER_SYMPTOMS_ROUTE = "/test/registerSymptoms";
    private static final String CHECK_CONDITION_ROUTE = "/test/checkCondition";

    public static boolean signin(User user) 
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

    public static boolean signup(User user) 
    {
        try 
        {
            HTTPConnection connection = new HTTPConnection(SERVER_URL + SIGNUP_ROUTE);
            String result = connection.sendRequest(JSONHandler.generateJSON(user));
            return JSONHandler.extractResult(result);
        } 
        catch (IOException exception) 
        {
            exception.printStackTrace();
        }
        return false;
    }

    public static boolean registerSymptoms(DailySymptoms dailySymptoms) 
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
