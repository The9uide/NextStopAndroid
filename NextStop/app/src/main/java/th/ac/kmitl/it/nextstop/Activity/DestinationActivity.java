package th.ac.kmitl.it.nextstop.Activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import th.ac.kmitl.it.nextstop.Model.StationList;
import th.ac.kmitl.it.nextstop.R;
import th.ac.kmitl.it.nextstop.databinding.ActivityDestinationBinding;

public class DestinationActivity extends AppCompatActivity {

    ActivityDestinationBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_destination);
        binding.setViewModel(StationList.getStations());

        binding.stationList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                if(true){
                    Intent intent = new Intent(DestinationActivity.this, MainActivity.class);
                    intent.putExtra("station", StationList.getStations().getStationFormIndex(i).getName());
                    startActivity(intent);
                }else {
                    Intent data = new Intent();
                    data.putExtra("station", StationList.getStations().getStationFormIndex(i).getName());
                    setResult(RESULT_OK, data);
                    finish();
                }
            }
        });
    }


}
