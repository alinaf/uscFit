package charstars.uscfit.RootObjects;

public class Date {
    private int year;
    private int month;
    private int day;
    private int hour;
    private int minute;


    public Date(int y, int m, int d, int h, int min) {
        this.year = y;
        this.month = m;
        this.day = d;
        this.hour = h;
        this.minute = min;
    }

    public Date(){

    }

    public int getYear() {
        return year;
    }

    public void setYear(int y) {
        this.year = y;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int m) {
        this.month = m;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int d) {
        this.day = d;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int h) {
        this.hour = h;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int m) {
        this.minute = m;
    }

    public String toStringDate() {
        String h = "";
        String m = "";
        if(hour == 0)
            h = "12";
        else if(hour > 12)
            h = Integer.toString(hour - 12);
        else
            h = Integer.toString(hour);
        if(minute < 10)
            m = "0" + minute;
        else
            m = Integer.toString(minute);

        return "" + h + ":" + m + " " + day + "/" + month + "/" + year;
    }

}
