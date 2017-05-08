package th.ac.kmitl.it.nextstop.Activity;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.location.Location;
import android.os.Handler;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

import java.util.Arrays;

import th.ac.kmitl.it.nextstop.Model.JSONAsyncTask;
import th.ac.kmitl.it.nextstop.Model.Route;
import th.ac.kmitl.it.nextstop.Model.Station;
import th.ac.kmitl.it.nextstop.Model.StationList;
import th.ac.kmitl.it.nextstop.Model.StationManager;
import th.ac.kmitl.it.nextstop.R;
import th.ac.kmitl.it.nextstop.Service.LocationReceiver;
import th.ac.kmitl.it.nextstop.Service.LocationService;
import th.ac.kmitl.it.nextstop.databinding.ActivityTravelBinding;

public class TravelActivity extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener {

    ActivityTravelBinding binding;
    private String departName;
    private String desName;
    private Station departStation;
    private Station destinationStation;
    private StationList stationList;
    private GoogleApiClient mGoogleApiClient;
    private LocationRequest mLocationRequest;
    private Location mCurrentLocation;
    private boolean mRequestingLocationUpdates = true;
    private String[] route;
    private StationManager stationManager;
    private NotificationCompat.Builder mBuilder;
    private int timeToArrive;
    private int count = 0;
    private Route routeList;
    private LocationReceiver mLocationReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initInstance();
    }

    public void initInstance() {
        Intent intent = getIntent();
        departName = intent.getStringExtra("departName");
        desName = intent.getStringExtra("desName");

        binding = DataBindingUtil.setContentView(this, R.layout.activity_travel);
        binding.routeListView.setFocusable(false);
        binding.closeButton.setOnClickListener(listener);
        binding.agreeButton.setOnClickListener(listener);
        binding.imageStation.setOnClickListener(listener);

        binding.imageStation.setImageResource(R.drawable.a1);

        setupServiceReceiver();

        stationList = StationList.getStations();
        departStation = stationList.getStationFormName(departName);
        destinationStation = stationList.getStationFormName(desName);


        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
        }

        mLocationRequest = LocationRequest.create()
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                .setInterval(10 * 1000)
                .setFastestInterval(1 * 1000);

        stationList.setTravelDetail(departStation, destinationStation, timeToArrive, mLocationReceiver);
        route = stationList.getRouteTravel(departStation, destinationStation);
        routeList = new Route();
        routeList.addStation("กำลังคำนวนเส้นทาง");
        binding.setViewModel(routeList);


        setListViewHeight();
        setRouteTravel();
        setTimeToArrive();
    }

    private void setTimeToArrive() {
        String origin = departStation.getLatitude() + "," + departStation.getLongitude();
        String destination = destinationStation.getLatitude() + "," + destinationStation.getLongitude();

        String url = getString(R.string.url_api) + "&origin=" + origin + "&destination=" + destination;
        Log.e("URL", url);

        JSONAsyncTask task = new JSONAsyncTask(this);
        task.execute(url);
    }

    @Override
    protected void onStart() {
        mGoogleApiClient.connect();
        super.onStart();
    }

    @Override
    protected void onStop() {
        mGoogleApiClient.disconnect();
        super.onStop();
    }

    public void updateArriveTime(int time) {
        binding.estimateTime.setText("ถึงสถานี" + desName + " ในอีก " + time + " นาที");
    }

    public void setRouteTravel() {
        String tmp = "";
        String[] routeListview = null;
        for (String s : route) {
            tmp += " " + s;
        }
        Log.e("Route", tmp);
        if (route.length > 1) {
            setNextStationDetail(route[1]);
            if (route.length > 2) {
                routeListview = Arrays.copyOfRange(route, 2, route.length);
            }
        } else {
            setNextStationDetail(route[0]);
        }

        binding.subEstimateTime.setText("ถัดไปอีก " + (route.length - 1) + "ป้าย");
        routeList.updateRoute(routeListview);
        setListViewHeight();
    }

    @Override
    public void onConnected(Bundle bundle) {
        mCurrentLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        stationManager = new StationManager(mCurrentLocation.getLatitude(), mCurrentLocation.getLongitude());
        stationManager.setupBaseTime(getIntent().getIntExtra("timeToArrive", 0), departStation, destinationStation);
        if (mRequestingLocationUpdates) {
            startLocationUpdates();
            mRequestingLocationUpdates = false;
        }


        PendingIntent pendingIntent = PendingIntent.getService(this, 0, new Intent(this, LocationService.class), PendingIntent.FLAG_UPDATE_CURRENT);
        LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, pendingIntent);

    }

    protected void startLocationUpdates() {
//        LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }

    @Override
    public void onLocationChanged(Location location) {
//        updateLocation(location);
    }

    private void updateLocation(Location location) {
        mCurrentLocation = location;
        double latitude = mCurrentLocation.getLatitude();
        double longitude = mCurrentLocation.getLongitude();
        stationManager.updateLocation(latitude, longitude);

        route = stationManager.updateNexttation(route);
        setRouteTravel();
        timeToArrive = stationManager.updateTimeToArrive();
        updateArriveTime(timeToArrive);
    }

    public void setupServiceReceiver() {
        mLocationReceiver = new LocationReceiver(new Handler());
        // This is where we specify what happens when data is received from the service
        mLocationReceiver.setReceiver(new LocationReceiver.Receiver() {
            @Override
            public void onReceiveResult(int resultCode, Bundle resultData) {
                if (resultCode == RESULT_OK) {
                    String resultValue = resultData.getString("resultValue");
                }
            }
        });
    }

    public void notificationArriveStation() {

        if (mBuilder == null && route.length == 2 || count == 1) {
            mBuilder = new NotificationCompat.Builder(this)
                    .setSmallIcon(R.drawable.iconnextstaion)
                    .setContentTitle("เตรียมตัวให้พร้อม!!!")
                    .setContentText("สถานีต่อไปคือสถานีปลายทาง");
            Log.e("Notification", "FIRST NOTI");
            showNotification(0);
            notifyNotification();
        } else if (route.length == 1 || count == 2) {
            mBuilder = new NotificationCompat.Builder(this)
                    .setSmallIcon(R.drawable.iconnextstaion)
                    .setContentTitle("ถึงสถานีปลายทางแล้ว!!!")
                    .setContentText("สถานีนี้คือสถานีปลายของท่าน");
            Log.e("Notification", "SECOND NOTI");
            showNotification(1);
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

    private void showNotification(int i) {
        if (i == 0) {
            binding.titelNoti.setText("เตรียมตัวให้พร้อม");
            String subTitle = "อีก " + timeToArrive + " นาทีจะถึงสถานี \"" + desName + "\" ซึ่งเป็นสถานีปลายทางของคุณ โปรดเตรียมตัวลงจากขบวนรถ";
            binding.subTitleNoti.setText(subTitle);
        } else if (i == 1) {
            binding.titelNoti.setText("ถึงสถานีปลายทางแล้ว");
            String subTitle = "ถึงสถานี \"" + desName + "\" ซึ่งเป็นสถานีปลายทางของคุณ โปรดเลงจากขบวนรถไฟฟ้า";
            binding.subTitleNoti.setText(subTitle);
        }
        binding.modalNoti.setVisibility(View.VISIBLE);
    }

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (binding.closeButton == view) {
//                notificationArriveStation();
//                count++;
                finish();
            } else if (binding.agreeButton == view) {
                binding.modalNoti.setVisibility(View.GONE);
            } else if (binding.imageStation == view) {
                notificationArriveStation();
                count++;
            }
        }
    };

    private void setListViewHeight() {
        ListView listview = binding.routeListView;
        ViewGroup.LayoutParams params = listview.getLayoutParams();
        params.height = (int) (routeList.items.size() * getResources().getDimension(R.dimen.row_route_height));
    }

    private void setNextStationDetail(String name) {
        int imageResource = stationList.getImageResourceFormName(name);
        binding.imageStation.setImageResource(imageResource);
        binding.nextStationTitle.setText(name);
        binding.nextStationLabel.setText(name);
    }
}
