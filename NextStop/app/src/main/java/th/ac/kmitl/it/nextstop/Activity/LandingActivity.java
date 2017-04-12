package th.ac.kmitl.it.nextstop.Activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

import th.ac.kmitl.it.nextstop.Model.Station;
import th.ac.kmitl.it.nextstop.Model.StationManager;
import th.ac.kmitl.it.nextstop.R;
import th.ac.kmitl.it.nextstop.databinding.ActivityLandingBinding;

public class LandingActivity extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    ActivityLandingBinding binding;
    private GoogleApiClient mGoogleApiClient;
    private Location mLastLocation;
    private static final int PERMISSION_ACCESS_COARSE_LOCATION = 1;
    private StationManager stationManager;
    private Station currentStation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initInstances();
    }

    private void initInstances() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_landing);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                    PERMISSION_ACCESS_COARSE_LOCATION);
        }

        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
        }

        binding.yesButton.setOnClickListener(listener);
        binding.noButton.setOnClickListener(listener);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case PERMISSION_ACCESS_COARSE_LOCATION:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // All good!
                } else {
                    Toast.makeText(this, "Need your location!", Toast.LENGTH_SHORT).show();
                }

                break;
        }
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

    @Override
    public void onConnected(Bundle bundle) {
        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        Log.i(LandingActivity.class.getSimpleName(), mLastLocation.toString());
        if (mLastLocation != null) {
            binding.confirmStation.setText(String.valueOf(mLastLocation.getLatitude()));
            Log.i(LandingActivity.class.getSimpleName(), String.valueOf(mLastLocation.getLongitude()) + ":" + String.valueOf(mLastLocation.getLatitude()));
            setUpCurrentLocation(mLastLocation.getLatitude(), mLastLocation.getLongitude());
        } else {
            binding.confirmStation.setText("Cannot get location");
        }
    }

    public void setUpCurrentLocation(double latitude, double longitude) {
        stationManager = new StationManager(latitude, longitude);
        currentStation = stationManager.getCurrentStation();
        binding.confirmStation.setText("คุณอยู่สถานี" + currentStation.getName() + "ใช่หรือไม่ ?");
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Log.i(LandingActivity.class.getSimpleName(), "Can't connect to Google Play Services!");
    }

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (binding.yesButton == view) {
                Intent intent = new Intent(LandingActivity.this, MainActivity.class);
                intent.putExtra("station", currentStation.getName());
                startActivity(intent);
            } else if (binding.noButton == view) {
                Intent intent = new Intent(LandingActivity.this, DepartSelectActivity.class);
                intent.putExtra("first", true);
                startActivity(intent);
            }
        }
    };
}
