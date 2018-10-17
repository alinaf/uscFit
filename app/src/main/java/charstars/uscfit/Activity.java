package charstars.uscfit;

public class Activity {

    private String category;
    private int defaultCalorieValue;

    public Activity(String category, int defaultCalorieValue) {
        this.category = category;
        this.defaultCalorieValue = defaultCalorieValue;
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
