package models;

public class UserResult 
{
    public User user;
    public String message;

    public UserResult setUser(User user) 
    {
        this.user = user;
        return this;
    }
    
    public UserResult setMessage(String message) 
    {
        this.message = message;
        return this;
    }

}
