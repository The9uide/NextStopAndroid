package th.ac.kmitl.it.nextstop.Activity;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import th.ac.kmitl.it.nextstop.R;
import th.ac.kmitl.it.nextstop.databinding.ActivityDepartSelectBinding;
import th.ac.kmitl.it.nextstop.databinding.ActivityMainBinding;

public class DepartSelectActivity extends AppCompatActivity {

    ActivityDepartSelectBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_landing);
    }
}