package th.ac.kmitl.it.nextstop.Activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;


import th.ac.kmitl.it.nextstop.Model.JSONAsyncTask;
import th.ac.kmitl.it.nextstop.R;
import th.ac.kmitl.it.nextstop.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    String departStation;
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

        Intent intent = getIntent();
        departStation = intent.getStringExtra("station");

        initInstances();
        new JSONAsyncTask().execute("https://maps.googleapis.com/maps/api/directions/json?" +
                "origin=13.697904,100.752115" +
                "&destination=13.755151,100.541822" +
                "&mode=transit" +
                "&transit_mode=subway" +
                "&key=AIzaSyDJeJe29vIwfDDZ75g1MCtPWVZklhukzQY");

    }

    private void initInstances() {
        binding.departButton.setText(departStation+ " ▼");
        binding.departButton.setOnClickListener(listener);
        binding.destinationButtion.setOnClickListener(listener);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == 1){
            if (resultCode == RESULT_OK){
                binding.departButton.setText(data.getStringExtra("station")+ " ▼");
            }
        }
    }

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(binding.departButton == view){
                Intent intent = new Intent(MainActivity.this,DepartSelectActivity.class);
                startActivityForResult(intent,1);
            }else if(binding.destinationButtion == view){
                Intent intent = new Intent(MainActivity.this,DestinationActivity.class);
                intent.putExtra("departStation",departStation);
                startActivity(intent);
            }

        }
    };
}