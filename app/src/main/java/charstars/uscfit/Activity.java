package charstars.uscfit;

public class Activity {

    private String category;
    private long defaultCalorieValue;

    public Activity(String category, long defaultCalorieValue) {
        this.category = category;
        this.defaultCalorieValue = defaultCalorieValue;
    }

    public Activity() {

    }

    public long getDefaultCalorieValue() {
        return defaultCalorieValue;
    }

    public void setDefaultCalorieValue(int defaultCalorieValue) {
        this.defaultCalorieValue = defaultCalorieValue;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return this.category;
    }
}
