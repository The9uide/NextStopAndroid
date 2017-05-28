package th.ac.kmitl.it.nextstop.Service;

import android.app.Activity;
import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderApi;
import com.google.android.gms.location.LocationResult;

import th.ac.kmitl.it.nextstop.Activity.TravelActivity;
import th.ac.kmitl.it.nextstop.Model.Station;
import th.ac.kmitl.it.nextstop.Model.StationList;
import th.ac.kmitl.it.nextstop.Model.StationManager;
import th.ac.kmitl.it.nextstop.R;

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
    private NotificationCompat.Builder mBuilder;
    private LocationReceiver rec;

    public LocationService() {
        super(".Service.Location");
        stationList = StationList.getStations();
//        Log.e("Location in Service", "Create Object");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

        if (LocationResult.hasResult(intent)) {
            LocationResult locationResult = LocationResult.extractResult(intent);
            final Location location = locationResult.getLastLocation();

            departStation = stationList.departStation;
            destinationStation = stationList.destinationStation;
//            Log.e("Location in Service", departStation + " : "+ destinationStation);
            rec = stationList.locationReceiver;
            int time = intent.getIntExtra("time",20);
            try{
                location.getLatitude();
                location.getLongitude();

                if (location != null) {
                    Log.e("Location in Service", "Latitude : " + location.getLatitude() + ", Longitude : " + location.getLongitude());
                    updateLocation(location, rec, time);
                }
            }catch (Exception e){
                Log.e("Location in Service","Cant get LOCATION");
                return;
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

        notificationArriveStation();

        Bundle bundle = new Bundle();
        bundle.putInt("time", timeToArrive);
        bundle.putStringArray("route",route);
        rec.send(Activity.RESULT_OK, bundle);

    }

    public void notificationArriveStation() {

        if (mBuilder == null && route.length == 2 && stationList.countNoti == 0) {
            mBuilder = new NotificationCompat.Builder(this)
                    .setSmallIcon(R.drawable.iconnextstaion)
                    .setContentTitle("เตรียมตัวให้พร้อม!!!")
                    .setContentText("สถานีต่อไปคือสถานีปลายทาง");
            Log.e("Notification", "FIRST NOTI");
            stationList.countNoti++;
            Bundle bundle = new Bundle();
            bundle.putInt("notification", 0);
            rec.send(Activity.RESULT_FIRST_USER,bundle);
            notifyNotification();
        } else if (route.length == 1 && stationList.countNoti == 1) {
            mBuilder = new NotificationCompat.Builder(this)
                    .setSmallIcon(R.drawable.iconnextstaion)
                    .setContentTitle("ถึงสถานีปลายทางแล้ว!!!")
                    .setContentText("สถานีนี้คือสถานีปลายของท่าน");
            Log.e("Notification", "SECOND NOTI");
            stationList.countNoti++;
            Bundle bundle = new Bundle();
            bundle.putInt("notification", 1);
            rec.send(Activity.RESULT_FIRST_USER,bundle);
            notifyNotification();
        }
    }

    private void notifyNotification() {
        Intent resultIntent = new Intent(this, TravelActivity.class);
        PendingIntent resultPendingIntent = PendingIntent.getActivity(this, 0, resultIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        mBuilder.setContentIntent(resultPendingIntent);
        mBuilder.setVibrate(new long[]{1000, 1000, 1000, 1000});

        NotificationManager mNotifyMgr = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        mNotifyMgr.notify(001, mBuilder.build());
    }
}
