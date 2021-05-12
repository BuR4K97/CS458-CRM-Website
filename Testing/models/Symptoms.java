package models;

public class Symptoms 
{
    public float fever;
    public Coughing coughing;
    public Headache headache;
    public boolean dizziness;
    public boolean tasteLoss;
    public boolean breatheDifficulty;
    public boolean chestPain;
    public boolean quickTiring;

    public Symptoms setFever(float fever) 
    {
        this.fever = fever;
        return this;
    }

    public Symptoms setCoughing(Coughing coughing) 
    {
        this.coughing = coughing;
        return this;
    }


    public Symptoms setHeadache(Headache headache) 
    {
        this.headache = headache;
        return this;
    }

    public Symptoms setDizziness(boolean dizziness) 
    {
        this.dizziness = dizziness;
        return this;
    }

    public Symptoms setTasteLoss(boolean tasteLoss) 
    {
        this.tasteLoss = tasteLoss;
        return this;
    }

    public Symptoms setBreatheDifficulty(boolean breatheDifficulty) 
    {
        this.breatheDifficulty = breatheDifficulty;
        return this;
    }

    public Symptoms setChestPain(boolean chestPain) 
    {
        this.chestPain = chestPain;
        return this;
    }

    public Symptoms setQuickTiring(boolean quickTiring) 
    {
        this.quickTiring = quickTiring;
        return this;
    }

}
