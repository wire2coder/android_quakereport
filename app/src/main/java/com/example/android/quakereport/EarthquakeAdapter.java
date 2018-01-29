package com.example.android.quakereport;


import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {

    // mPrivate variables
    private static final String LOCATION_SEPARATOR = " of";

    // constructor
    public EarthquakeAdapter(Context context, List<Earthquake> earthquakes) {
        super(context, 0, earthquakes);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;

        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.earthquake_list_item, parent, false);
        }

        Earthquake currentEarthquake = getItem(position);

        TextView magnitudeView = (TextView) listItemView.findViewById(R.id.magnitude);
        String magnitude = formatMagnitude(currentEarthquake.getMagnitude());
        magnitudeView.setText( magnitude );

        // Get the appropriate background color based on the current earthquake magnitude
        // currentEarthquake.getMagnitude() >> 7
        // getMagnitudeColor(7.1)
        int magnitudeColor = getMagnitudeColor( currentEarthquake.getMagnitude() );

        // Set the proper background color on the magnitude circle.
        // Fetch the background from the TextView, which is a GradientDrawable.
        GradientDrawable magnitudeCircle = (GradientDrawable) magnitudeView.getBackground();

        // Set the color on the magnitude circle
        magnitudeCircle.setColor(magnitudeColor);


        TextView tv_primary_location = (TextView) listItemView.findViewById(R.id.primary_location);
        TextView tv_offset_location = (TextView) listItemView.findViewById(R.id.offset_location);

        String originalLocation = currentEarthquake.getLocation();

        String primary_location;    // " "
        String offset_location;     // " "

        if ( originalLocation.contains(LOCATION_SEPARATOR) ) {
            // 74km NW of Rumoi, Japan
            String[] parts = originalLocation.split(LOCATION_SEPARATOR);
            offset_location = parts[0] + LOCATION_SEPARATOR;    // 74km NW of
            primary_location = parts[1];    // Rumoi, Japan

        } else {
            // Rumoi, Japan
            offset_location = getContext().getString(R.string.near_me); // Near the
            primary_location = originalLocation;    // Rumoi, Japan

        }

        tv_primary_location.setText(primary_location);
        tv_offset_location.setText(offset_location);

        TextView dateView = (TextView) listItemView.findViewById(R.id.date);
        Date dateObject = new Date(currentEarthquake.getTimeInMilliseconds() );
        String formattedDate = formatDate(dateObject);
        dateView.setText(formattedDate);

        TextView timeView = (TextView) listItemView.findViewById(R.id.time);
        String formattedTime = formatTime(dateObject);
        timeView.setText(formattedTime);


        return listItemView;
    } // getView

    private String formatDate(Date date) {

        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");

        return dateFormat.format(date);
    }

    private String formatTime(Date time) {

        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");

        return timeFormat.format(time);
    }

    private String formatMagnitude(double magnitude) {

        DecimalFormat magnitudeFormat = new DecimalFormat("0.0");

        return magnitudeFormat.format(magnitude);
    }

    private int getMagnitudeColor(double magnitude) {

        int magnitudeColor;
        int magnitude_int = (int) Math.floor(magnitude);

        // do switch control flow with magnitude
        switch (magnitude_int) {
            case 1:
                magnitudeColor = ContextCompat.getColor( getContext(), R.color.magnitude1);
                break;

            case 2:
                magnitudeColor = ContextCompat.getColor( getContext(), R.color.magnitude2);
                break;

            case 3:
                magnitudeColor = ContextCompat.getColor( getContext(), R.color.magnitude3);
                break;

            case 4:
                magnitudeColor = ContextCompat.getColor( getContext(), R.color.magnitude4);
                break;

            case 5:
                magnitudeColor = ContextCompat.getColor( getContext(), R.color.magnitude5);
                break;

            case 6:
                magnitudeColor = ContextCompat.getColor( getContext(), R.color.magnitude6);
                break;

            case 7:
                magnitudeColor = ContextCompat.getColor( getContext(), R.color.magnitude7);
                break;

            case 8:
                magnitudeColor = ContextCompat.getColor( getContext(), R.color.magnitude8);
                break;

            case 9:
                magnitudeColor = ContextCompat.getColor( getContext(), R.color.magnitude9);
                break;

            default:
                magnitudeColor = ContextCompat.getColor( getContext(), R.color.magnitude10plus);
                break;
        }


        // return a color
        return magnitudeColor;
    }

} // class
