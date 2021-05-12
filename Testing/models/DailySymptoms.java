package models;

import java.time.LocalDate;

public class DailySymptoms 
{
    private User user;
    private LocalDate date;
    private Symptoms symptoms;

    public DailySymptoms(User user, LocalDate date, Symptoms symptoms) {
        this.user = user;
        this.date = date;
        this.symptoms = symptoms;
    }

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

    public User getUser() {
        return user;
    }

    public LocalDate getDate() {
        return date;
    }

    public Symptoms getSymptoms() {
        return symptoms;
    }
}