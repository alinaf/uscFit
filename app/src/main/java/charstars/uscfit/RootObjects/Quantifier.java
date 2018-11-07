package charstars.uscfit.RootObjects;

import java.io.Serializable;

public enum Quantifier implements Serializable
{
    MILES("miles"), MINUTES("minutes"), STEPS("steps"), DAYS("days");

    private String measurement;

    // getter method
    public String getMeasurement()
    {
        return this.measurement;
    }

    // enum constructor - cannot be public or protected


    // enum constructor - cannot be public or protected
    Quantifier(String action)
    {
        this.measurement = action;
    }
}
