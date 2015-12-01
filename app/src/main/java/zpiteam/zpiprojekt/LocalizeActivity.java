package zpiteam.zpiprojekt;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.widget.TextView;
import android.widget.Button;
import android.view.View;
import android.view.View.OnClickListener;
import android.util.Log;
import java.util.Calendar;

public class LocalizeActivity extends Activity implements LocationListener{

    private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 10; // 10 meters
    // The minimum time between updates in milliseconds
    private static final long MIN_TIME_BW_UPDATES = 1000 * 1 * 1; // 1 minute/60

    protected LocationManager locationManager;
    TextView txtLat;
    TextView txtLong;
    TextView txtTime;


    protected double latitude,longitude;
    protected boolean gps_enabled,network_enabled;
    //Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_localize);
        txtLat = (TextView) findViewById(R.id.textviewLat);
        txtLong = (TextView) findViewById(R.id.textviewLong);
        txtTime = (TextView) findViewById(R.id.textviewTime);

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        gps_enabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        network_enabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

        if (gps_enabled) {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, MIN_TIME_BW_UPDATES, MIN_DISTANCE_CHANGE_FOR_UPDATES, this);
        }
        else if (network_enabled) {
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, MIN_TIME_BW_UPDATES, MIN_DISTANCE_CHANGE_FOR_UPDATES, this);
        }
        //addListenerOnButton();
    }

    /*public void addListenerOnButton()
    {

        button = (Button) findViewById(R.id.buttonBack);

        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                //Powrot z aktywnosci
            }
        });
    }*/

    @Override
    public void onLocationChanged(Location location) {

        latitude = location.getLatitude();
        longitude = location.getLongitude();

        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        int second = calendar.get(Calendar.SECOND);
        int minute = calendar.get(Calendar.MINUTE);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);

        String time = year + "-" + month + "-" + day + " " + hour + ":" + minute + ":" + second;

        txtLat.setText("Latitude: " + latitude);
        txtLong.setText("Longitude: " + longitude);
        txtTime.setText(time);

        DBAdapter.getInstance().createLocalization(latitude, longitude, time, CurrentUser.getInstance().getID());
    }

    @Override
    public void onProviderDisabled(String provider) {
        Log.d("Latitude","disable");
    }

    @Override
    public void onProviderEnabled(String provider) {
        Log.d("Latitude","enable");
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        Log.d("Latitude","status");
    }
}