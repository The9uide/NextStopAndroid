package th.ac.kmitl.it.nextstop.Activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import th.ac.kmitl.it.nextstop.Model.StationList;
import th.ac.kmitl.it.nextstop.R;
import th.ac.kmitl.it.nextstop.databinding.ActivityDepartSelectBinding;

public class DepartSelectActivity extends AppCompatActivity {

    ActivityDepartSelectBinding binding;
    boolean firsttime = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initInstances();
    }

    private void initInstances(){
        binding = DataBindingUtil.setContentView(this, R.layout.activity_depart_select);
        binding.setViewModel(StationList.getStations());
        Intent intent = getIntent();
        firsttime = intent.getBooleanExtra("first",false);

        binding.stationList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                if(firsttime){
                    Intent intent = new Intent(DepartSelectActivity.this, MainActivity.class);
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

        binding.backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

}
