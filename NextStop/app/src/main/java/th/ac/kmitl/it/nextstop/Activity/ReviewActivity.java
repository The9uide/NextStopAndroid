package th.ac.kmitl.it.nextstop.Activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import th.ac.kmitl.it.nextstop.Model.JSONAsyncTask;
import th.ac.kmitl.it.nextstop.Model.Station;
import th.ac.kmitl.it.nextstop.Model.StationList;
import th.ac.kmitl.it.nextstop.R;
import th.ac.kmitl.it.nextstop.databinding.ActivityReviewBinding;

public class ReviewActivity extends AppCompatActivity {

    ActivityReviewBinding binding;
    String departStation;
    String desStation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initInstance();
    }

    private void initInstance() {
        Intent intent = getIntent();
        departStation = intent.getStringExtra("departStation");
        desStation = intent.getStringExtra("desStation");

        binding = DataBindingUtil.setContentView(this, R.layout.activity_review);
        binding.departButton.setText(departStation + " ▼");
        binding.departButton.setOnClickListener(listener);
        binding.startButton.setOnClickListener(listener);
        binding.nameStation.setText(desStation);

        binding.backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        setTimeToArrive();
    }

    private void setTimeToArrive() {
        Station depart = StationList.getStations().getStationFormName(departStation);
        Station destination = StationList.getStations().getStationFormName(desStation);
        
        JSONAsyncTask task = new JSONAsyncTask(this);
        task.execute(getString(R.string.url_api));
    }

    public void updateArriveTime(int time) {
        binding.timeToArrive.setText(time + " นาที");
    }

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (binding.departButton == view) {
                Intent intent = new Intent(ReviewActivity.this, DepartSelectActivity.class);
                startActivityForResult(intent, 1);
            } else if (binding.startButton == view) {
                Intent intent = new Intent(ReviewActivity.this, DestinationActivity.class);
                intent.putExtra("departStation", departStation);
                startActivity(intent);
            }
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                binding.departButton.setText(data.getStringExtra("station") + " ▼");
            }
        }
    }
}
