package models;

import java.time.LocalDate;

public class DailySymptoms 
{
    public User user;
    public LocalDate date;
    public Symptoms symptoms;

    public DailySymptoms setUser(User user) 
    {
        this.user = user;
        return this;
    }

    public DailySymptoms setDate(LocalDate date) 
    {
        this.date = date;
        return this;
    }
   
    public DailySymptoms setSymptoms(Symptoms symptoms) 
    {
        this.symptoms = symptoms;
        return this;
    }

}