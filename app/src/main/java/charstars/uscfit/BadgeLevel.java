package charstars.uscfit;

public enum BadgeLevel {

    FIFTY(50), HUNDRED(100), FIVEHUNDRED(500), ONETHOUSAND(1000);

    private int measurement;

    // getter method
    public int getMeasurement()
    {
        return this.measurement;
    }

    // enum constructor - cannot be public or protected


    // enum constructor - cannot be public or protected
    BadgeLevel(int action)
    {
        this.measurement = action;
    }
}
