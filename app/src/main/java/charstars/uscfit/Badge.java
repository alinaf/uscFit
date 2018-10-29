package charstars.uscfit;

import java.util.Date;

public class Badge {

    String description;
    Date date;

    BadgeLevel level;

    public Badge(String description, Date date, BadgeLevel b) {
        this.description = description;
        this.date = date;
        this.level = b;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    public BadgeLevel getLevel() {
        return level;
    }

    public void setLevel(BadgeLevel level) {
        this.level = level;
    }

}
