package th.ac.kmitl.it.nextstop.Activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.location.Location;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

import th.ac.kmitl.it.nextstop.Model.JSONAsyncTask;
import th.ac.kmitl.it.nextstop.Model.Station;
import th.ac.kmitl.it.nextstop.Model.StationList;
import th.ac.kmitl.it.nextstop.R;
import th.ac.kmitl.it.nextstop.databinding.ActivityTravelBinding;

public class TravelActivity extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener {

    ActivityTravelBinding binding;
    String departName;
    String desName;
    Station departStation;
    Station destinationStation;
    private StationList stationList;
    private GoogleApiClient mGoogleApiClient;
    private LocationRequest mLocationRequest;
    private Location mCurrentLocation;
    private boolean mRequestingLocationUpdates = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_travel);
        binding.setViewModel(StationList.getStations());
        initInstance();
    }

    public void initInstance() {
        Intent intent = getIntent();
        departName = intent.getStringExtra("departName");
        desName = intent.getStringExtra("desName");

        binding = DataBindingUtil.setContentView(this, R.layout.activity_travel);
        binding.nextStationLabel.setText(departName);
        binding.destinationStation.setText(desName);
        binding.setViewModel(StationList.getStations());
        binding.routeListView.setFocusable(false);

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

        setTimeToArrive();
        setRouteTravel();
    }

    private void setTimeToArrive() {
        String origin = departStation.getLatitude() + "," + departStation.getLongitude();
        String destination = destinationStation.getLatitude() + "," + destinationStation.getLongitude();

        String url = getString(R.string.url_api) + "&origin=" + origin + "&destination=" + destination;
        Log.e("URL", url);

        JSONAsyncTask task = new JSONAsyncTask(this);
        task.execute(url);
    }

    public void updateArriveTime(int time) {
        binding.estimateTime.setText(time + " นาที");
    }

    public void setRouteTravel() {
        String[] route = stationList.getRouteTravel(departStation, destinationStation);
        binding.nextStationLabel.setText(route[1]);
    }

    @Override
    public void onConnected(Bundle bundle) {
        mCurrentLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        if(mRequestingLocationUpdates){
            startLocationUpdates();
            mRequestingLocationUpdates = false;
        }

    }

    protected void startLocationUpdates() {
        LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }

    @Override
    public void onLocationChanged(Location location) {
        updateLocation(location);
    }

    private void updateLocation(Location location) {
        double latitude = location.getLatitude();
        double longitude = location.getLongitude();

    }
}
