package com.casestudy.admin.WrapperModel;

import com.casestudy.admin.model.Ratings;

import java.util.List;

public class WasherRatings {
    private String WasherID;
    private String WasherName;
    private String WasherEmailID;
    private List<com.casestudy.admin.model.Ratings> Ratings;

    //Default Constructor
    public WasherRatings(){

    }

    public WasherRatings(String washerID, String washerName, String washerEmailID, List<com.casestudy.admin.model.Ratings> ratings) {
        WasherID = washerID;
        WasherName = washerName;
        WasherEmailID = washerEmailID;
        Ratings = ratings;
    }

    public String getWasherID() {
        return WasherID;
    }

    public void setWasherID(String washerID) {
        WasherID = washerID;
    }

    public String getWasherName() {
        return WasherName;
    }

    public void setWasherName(String washerName) {
        WasherName = washerName;
    }

    public String getWasherEmailID() {
        return WasherEmailID;
    }

    public void setWasherEmailID(String washerEmailID) {
        WasherEmailID = washerEmailID;
    }

    public List<com.casestudy.admin.model.Ratings> getRatings() {
        return Ratings;
    }

    public void setRatings(List<com.casestudy.admin.model.Ratings> ratings) {
        Ratings = ratings;
    }
    @Override
    public String toString() {
        return "WasherRatings{" +
                "WasherID='" + WasherID + '\'' +
                ", WasherName='" + WasherName + '\'' +
                ", WasherEmailID='" + WasherEmailID + '\'' +
                ", Ratings=" + Ratings +
                '}';
    }
}
