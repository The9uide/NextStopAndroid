package th.ac.kmitl.it.nextstop.Activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

import th.ac.kmitl.it.nextstop.Model.JSONAsyncTask;
import th.ac.kmitl.it.nextstop.Model.Station;
import th.ac.kmitl.it.nextstop.Model.StationList;
import th.ac.kmitl.it.nextstop.R;
import th.ac.kmitl.it.nextstop.databinding.ActivityTravelBinding;

public class TravelActivity extends AppCompatActivity {

    ActivityTravelBinding binding;
    String departName;
    String desName;
    Station departStation;
    Station destinationStation;
    StationList stationList;

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

        stationList = StationList.getStations();
        departStation = stationList.getStationFormName(departName);
        destinationStation = stationList.getStationFormName(desName);

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
        Log.e("Route",route[0]+route[1]+route[2]);
        binding.nextStationLabel.setText(route[1]);
    }

}
