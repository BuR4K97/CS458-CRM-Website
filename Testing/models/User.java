package models;

public class User 
{
    public String email;
    public String password;
    public String name;
    public int age;
    public Gender gender;
    
    public User setEmail(String email) 
    {
        this.email = email;
        return this;
    }

    public User setPassword(String password) 
    {
        this.password = password;
        return this;
    }

    public User setName(String name) 
    {
        this.name = name;
        return this;
    }

    public User setAge(int age) 
    {
        this.age = age;
        return this;
    }

    public User setGender(Gender gender) 
    {
        this.gender = gender;
        return this;
    }
}
