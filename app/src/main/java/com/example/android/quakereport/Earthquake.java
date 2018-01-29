package com.example.android.quakereport;


public class Earthquake {

    // private variables
    private String mLocation;
    private double mMagnitude;
    private long mTimeInMilliseconds;
    private String mUrl;

    // constructor
    public Earthquake(double magnitude, String location, long timeInMilliseconds, String url) {
        mMagnitude = magnitude;
        mLocation = location;
        mTimeInMilliseconds = timeInMilliseconds;
        mUrl = url;
    }

    // methods for getting values
    public String getLocation() {
        return mLocation;
    }

    public double getMagnitude() {
        return mMagnitude;
    }

    public long getTimeInMilliseconds() {
        return mTimeInMilliseconds;
    }

    public String getUrl() {
        return mUrl;
    }

}
