package models;

public class User 
{
    private String email;
    private String password;
    private String name;
    private int age;
    private Gender gender;

    public User(String email, String password, String name, int age, Gender gender) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

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

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public Gender getGender() {
        return gender;
    }
}
