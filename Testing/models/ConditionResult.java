package models;

public class ConditionResult 
{
    private Condition condition;
    private String message;

    public Condition getCondition() {
        return condition;
    }

    public String getMessage() {
        return message;
    }

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
