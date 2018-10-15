package charstars.uscfit;

import java.util.Date;
import java.util.Map;

public class Goal {
    private String description;
    private String category;
    private Map<Activity, Date> dueDates;

    public Goal(String description, String category, Map<Activity, Date> dueDates) {
        this.description = description;
        this.category = category;
        this.dueDates = dueDates;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Map<Activity, Date> getDueDates() {
        return dueDates;
    }

    public void setDueDates(Map<Activity, Date> dueDates) {
        this.dueDates = dueDates;
    }

    public void addActivity(Activity a, Date d){
        this.dueDates.put(a, d);
    }
    public void removeActivity(Activity a){
        this.dueDates.remove(a);
    }



}
