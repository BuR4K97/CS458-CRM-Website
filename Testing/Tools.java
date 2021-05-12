import models.*;

import java.io.IOException;

import helper.HTTPConnection;
import helper.JSONHandler;

public class Tools 
{
    private static final String SERVER_URL = "http://localhost:3002";
    private static final String SIGNIN_ROUTE = "/login";
    private static final String SIGNUP_ROUTE = "/signup"; 
    private static final String REGISTER_SYMPTOMS_ROUTE = "/registerDailySymptoms";
    private static final String CHECK_CONDITION_ROUTE = "/checkCondition";

    public static UserResult login(User user) 
    {
        try 
        {
            HTTPConnection connection = new HTTPConnection(SERVER_URL + SIGNIN_ROUTE);
            String result = connection.sendRequest(JSONHandler.generateJSON(user));
            System.out.println(result);
            return JSONHandler.extractUserResult(result);
        } 
        catch (IOException exception) 
        {
            exception.printStackTrace();
        }
        return null;
    }

    public static UserResult signup(User user)
    {
        try 
        {
            HTTPConnection connection = new HTTPConnection(SERVER_URL + SIGNUP_ROUTE);
            String result = connection.sendRequest(JSONHandler.generateJSON(user));
            return JSONHandler.extractUserResult(result);
        } 
        catch (IOException exception) 
        {
            exception.printStackTrace();
        }
        return null;
    }

    public static Result registerDailySymptoms(DailySymptoms dailySymptoms)
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
        return null;
    }

    public static ConditionResult checkCondition(User user)
    {
        try 
        {
            HTTPConnection connection = new HTTPConnection(SERVER_URL + CHECK_CONDITION_ROUTE);
            String result = connection.sendRequest(JSONHandler.generateJSON(user));
            return JSONHandler.extractConditionResult(result);
        } 
        catch (IOException exception) 
        {
            exception.printStackTrace();
        }
        return null;
    }
}
