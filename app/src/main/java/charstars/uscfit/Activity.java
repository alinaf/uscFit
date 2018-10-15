package charstars.uscfit;

public class Activity {

    private String name;
    private int defaultCalorieValue;
    private String category;

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    private boolean completed;

    public Activity(String name, int defaultCalorieValue, String category) {
        this.name = name;
        this.defaultCalorieValue = defaultCalorieValue;
        this.category = category;
        completed = false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDefaultCalorieValue() {
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


}
