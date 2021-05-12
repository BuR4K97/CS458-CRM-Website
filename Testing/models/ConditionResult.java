package models;

public class ConditionResult 
{
    public Condition condition;
    public String message;

    public ConditionResult setCondition(Condition condition) 
    {
        this.condition = condition;
        return this;
    }
   
    public ConditionResult setMessage(String message) 
    {
        this.message = message;
        return this;
    }

}
