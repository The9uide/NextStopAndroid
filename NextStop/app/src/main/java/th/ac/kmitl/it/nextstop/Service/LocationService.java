package th.ac.kmitl.it.nextstop.Service;

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

import th.ac.kmitl.it.nextstop.Model.StationList;
import th.ac.kmitl.it.nextstop.Model.StationManager;

import static th.ac.kmitl.it.nextstop.R.id.timeToArrive;

/**
 * Created by The9uide on 15-Apr-17.
 */

public class LocationService extends IntentService
{
    private final StationList stationList;
    private Location mCurrentLocation;
    private StationManager stationManager;
    private String[] route;


    public LocationService() {
        super(".Service.Location");
        stationList = StationList.getStations();
//        departStation = stationList.getStationFormName(departName);
//        destinationStation = stationList.getStationFormName(desName);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        if (LocationResult.hasResult(intent)) {
            LocationResult locationResult = LocationResult.extractResult(intent);
            Location location = locationResult.getLastLocation();
            if (location != null) {
                Log.e("Location in Service","Latitude : " + location.getLatitude() +", Longitude : " + location.getLongitude());
                
            }
        }
//        intent.getStringExtra("")
    }
    private void updateLocation(Location location) {
        mCurrentLocation = location;
        
        double latitude = mCurrentLocation.getLatitude();
        double longitude = mCurrentLocation.getLongitude();
        stationManager.updateLocation(latitude, longitude);

        route = stationManager.updateNexttation(route);
//        setRouteTravel();
//        notificationArriveStation();
//        timeToArrive = stationManager.updateTimeToArrive();
//        binding.estimateTime.setText("ถึงสถานี" + desName + " ในอีก " + timeToArrive + " นาที");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return super.onBind(intent);
    }
//    public void setRouteTravel() {
//        String tmp = "";
//        for (String s : route) {
//            tmp += " " + s;
//        }
//        Log.e("Route", tmp);
//        if (route.length > 1) {
//            binding.nextStationLabel.setText(route[1]);
//        } else {
//            binding.nextStationLabel.setText(route[0]);
//        }
//
//        binding.subEstimateTime.setText("ถัดไปอีก " + (route.length - 1) + "ป้าย");
//    }
}
