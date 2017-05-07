package th.ac.kmitl.it.nextstop.Service;

import android.app.Activity;
import android.app.IntentService;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderApi;
import com.google.android.gms.location.LocationResult;

import th.ac.kmitl.it.nextstop.Model.Station;
import th.ac.kmitl.it.nextstop.Model.StationList;
import th.ac.kmitl.it.nextstop.Model.StationManager;

import static th.ac.kmitl.it.nextstop.R.id.timeToArrive;

/**
 * Created by The9uide on 15-Apr-17.
 */

public class LocationService extends IntentService {
    private final StationList stationList;
    private Station departStation;
    private Station destinationStation;
    private Location mCurrentLocation;
    private StationManager stationManager;
    private String[] route;


    public LocationService() {
        super(".Service.Location");
        stationList = StationList.getStations();

    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

        if (LocationResult.hasResult(intent)||true) {
            LocationResult locationResult = LocationResult.extractResult(intent);
            final Location location = locationResult.getLastLocation();

            Log.e("Location in Service", "get location " + location.toString());
            String departName = intent.getStringExtra("departName");
            String desName = intent.getStringExtra("desName");
            LocationReceiver rec = intent.getParcelableExtra("receiver");
            int time = intent.getIntExtra("time",20);


            departStation = stationList.getStationFormName(departName);
            destinationStation = stationList.getStationFormName(desName);
            Log.e("Location in Service", "Updating");
            if (location != null) {
                Log.e("Location in Service", "Latitude : " + location.getLatitude() + ", Longitude : " + location.getLongitude());
//                updateLocation(location, rec, time);
            }

        }
    }

    private void updateLocation(Location location, LocationReceiver rec, int time) {
        mCurrentLocation = location;

        double latitude = mCurrentLocation.getLatitude();
        double longitude = mCurrentLocation.getLongitude();
        stationManager = new StationManager(latitude,longitude);
        stationManager.setupBaseTime(time,departStation,destinationStation);
        Station nearestStation = stationManager.getNearestStation(latitude, longitude);
        route = stationList.getRouteTravel(nearestStation, destinationStation);
        int timeToArrive = stationManager.updateTimeToArrive();

        Log.e("UpdateLocationService",time + " : " + route.toString());

        Bundle bundle = new Bundle();
        bundle.putInt("time", timeToArrive);
        bundle.putStringArray("route",route);
        rec.send(Activity.RESULT_OK, bundle);

    }
}
