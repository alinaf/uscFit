package charstars.uscfit;

import java.util.Date;

public class Badge {

    // Store the id of the trophy
    private int mTrophyNum;
    // Store the name of the badge
    private String mName;
    // Store the date of the badge
    private String mDate;

    // Constructor that is used to create an instance of the Movie object
    public Badge(int mTrophyNum, String mName, String mDate) {
        this.mTrophyNum = mTrophyNum;
        this.mName = mName;
        this.mDate = mDate;
    }

    public int getmTrophyNum() {
        return mTrophyNum;
    }

    public void setmTrophyNum(int mTrophyNum) {
        this.mTrophyNum = mTrophyNum;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmDate() {
        return mDate;
    }

    public void setmDate(String mDate) {
        this.mDate = mDate;
    }

    }
