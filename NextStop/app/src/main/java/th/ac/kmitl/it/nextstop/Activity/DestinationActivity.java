package th.ac.kmitl.it.nextstop.Activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import java.lang.reflect.Array;

import th.ac.kmitl.it.nextstop.Model.StationList;
import th.ac.kmitl.it.nextstop.R;
import th.ac.kmitl.it.nextstop.databinding.ActivityDestinationBinding;

public class DestinationActivity extends AppCompatActivity {

    ActivityDestinationBinding binding;
    String departStation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initInstances();
    }

    private void initInstances(){
        binding = DataBindingUtil.setContentView(this, R.layout.activity_destination);
        binding.setViewModel(StationList.getStations());

        Intent intent = getIntent();
        departStation = intent.getStringExtra("departName");

        binding.stationList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                if(true){
                    Intent intent = new Intent(DestinationActivity.this, ReviewActivity.class);
                    intent.putExtra("departName",departStation);
                    intent.putExtra("desName", StationList.getStations().getStationFormIndex(i).getName());
                    startActivity(intent);
                }
            }
        });

        binding.backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
