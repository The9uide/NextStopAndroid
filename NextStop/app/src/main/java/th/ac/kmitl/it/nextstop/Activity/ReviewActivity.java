package th.ac.kmitl.it.nextstop.Activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import th.ac.kmitl.it.nextstop.Model.JSONAsyncTask;
import th.ac.kmitl.it.nextstop.Model.Station;
import th.ac.kmitl.it.nextstop.Model.StationList;
import th.ac.kmitl.it.nextstop.R;
import th.ac.kmitl.it.nextstop.databinding.ActivityReviewBinding;

public class ReviewActivity extends AppCompatActivity {

    ActivityReviewBinding binding;
    String departName;
    String desName;
    Station departStation;
    Station destinationStation;
    StationList stationList;
    private int timeToArrive;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initInstance();
    }

    private void initInstance() {
        Intent intent = getIntent();
        departName = intent.getStringExtra("departName");
        desName = intent.getStringExtra("desName");

        stationList = StationList.getStations();
        departStation = stationList.getStationFormName(departName);
        destinationStation = stationList.getStationFormName(desName);

        int imageResource = stationList.getImageResourceFormName(desName);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_review);
        binding.stationImage.setImageResource(imageResource);
        binding.departButton.setText(departName + " ▼");
        binding.departButton.setOnClickListener(listener);
        binding.startButton.setOnClickListener(listener);
        binding.nameStation.setText(desName);
        try {
            if(destinationStation.getConnection() != null){
                binding.connection.setText("จุดเชื่อมต่อ "+ destinationStation.getConnection());
            }else{
                binding.frameConnection.setVisibility(View.GONE);
            }
        }catch (Exception e){
            binding.frameConnection.setVisibility(View.GONE);
        }

        binding.backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        setTimeToArrive();
        setStair();
    }

    private void setTimeToArrive() {
        String origin = departStation.getLatitude() + "," + departStation.getLongitude();
        String destination = destinationStation.getLatitude() + "," + destinationStation.getLongitude();

        String url = getString(R.string.url_api) + "&origin=" + origin + "&destination=" + destination;
        Log.e("URL", url);

        JSONAsyncTask task = new JSONAsyncTask(this);
        task.execute(url);
    }

    public void setStair(){
        String stair = stationList.getStationStair(departStation,destinationStation);
        binding.stair.setText("ขึ้นบันไดฝั่ง \"" + stair + "\"");
    }

    public void updateArriveTime(int time) {
        binding.timeToArrive.setText(time + " นาที");
        timeToArrive = time;
    }

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (binding.departButton == view) {
                Intent intent = new Intent(ReviewActivity.this, DepartSelectActivity.class);
                startActivityForResult(intent, 1);
            } else if (binding.startButton == view) {
                Intent intent = new Intent(ReviewActivity.this, TravelActivity.class);
                intent.putExtra("departName", departName);
                intent.putExtra("desName",desName);
                intent.putExtra("timeToArrive",timeToArrive);
                startActivity(intent);
            }
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                departName = data.getStringExtra("station");
                binding.departButton.setText(departName + " ▼");
            }
        }
    }
}
