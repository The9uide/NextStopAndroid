package th.ac.kmitl.it.nextstop.Activity;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import th.ac.kmitl.it.nextstop.Model.JSONAsyncTask;
import th.ac.kmitl.it.nextstop.Model.StationList;
import th.ac.kmitl.it.nextstop.R;
import th.ac.kmitl.it.nextstop.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    String url = "https://maps.googleapis.com/maps/api/directions/json?\n" +
            "origin=13.697904,100.752115\n" +
            "&destination=13.755151,100.541822\n" +
            "&mode=transit\n" +
            "&transit_mode=subway\n" +
            "&key=AIzaSyDJeJe29vIwfDDZ75g1MCtPWVZklhukzQY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        new JSONAsyncTask().execute("https://maps.googleapis.com/maps/api/directions/json?" +
                "origin=13.697904,100.752115" +
                "&destination=13.755151,100.541822" +
                "&mode=transit" +
                "&transit_mode=subway" +
                "&key=AIzaSyDJeJe29vIwfDDZ75g1MCtPWVZklhukzQY");

    }
}