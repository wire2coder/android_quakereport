package com.example.android.quakereport;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import java.util.List;

/**
 * Created by tv2 on 1/29/2018.
 */

public class EarthquakeLoader extends AsyncTaskLoader< List<Earthquake> > {

    /** Tag for log messages */
    private static final String LOG_TAG = EarthquakeLoader.class.getName();

    /** Query URL */
    private String mUrl;

    // The new maker
    public EarthquakeLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }

    // because of AsyncTaskLoader
    @Override
    protected void onStartLoading() {
        Log.e(LOG_TAG, "LOG_TAG: " + "onStartLoading" );
        forceLoad();
    }

    // because of AsyncTaskLoader
    @Override
    public List<Earthquake> loadInBackground() {
        if (mUrl == null) {
            return null;
        }

        // Perform the network request, parse the response, and extract a list of earthquakes.
        Log.e(LOG_TAG, "LOG_TAG: " + "loadInBackground -> fetching data" );
        List<Earthquake> earthquakes = QueryUtils.fetchEarthquakeData(mUrl);
        Log.e(LOG_TAG, "LOG_TAG: " + "::: earthquakes DATA :::" + earthquakes.toString() );
        return earthquakes;
    }

} // class EarthquakeLoader
