package models;

public class UserResult 
{
    private User user;
    private String message;

    public User getUser() {
        return user;
    }

    public String getMessage() {
        return message;
    }

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
