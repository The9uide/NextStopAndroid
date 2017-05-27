package th.ac.kmitl.it.nextstop.Fragment;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import th.ac.kmitl.it.nextstop.Model.FoursquareAsyncTask;
import th.ac.kmitl.it.nextstop.Model.JSONAsyncTask;
import th.ac.kmitl.it.nextstop.Model.Shop;
import th.ac.kmitl.it.nextstop.Model.ShopList;
import th.ac.kmitl.it.nextstop.R;
import th.ac.kmitl.it.nextstop.databinding.FragmentDetailBinding;

/**
 * Created by The9uide on 13-May-17.
 */

public class ShopFragment extends Fragment {

    private FragmentDetailBinding binding;
    private ShopList shopList;

    public ShopFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void initInstance() {

        shopList = new ShopList();
        binding.setViewModel(shopList);

        String url = getString(R.string.url_api) + "&origin=" + 0 + "&destination=" + 0;

        FoursquareAsyncTask task = new FoursquareAsyncTask(this);
        task.execute(url);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_detail, container, false);
        View view = binding.getRoot();
        initInstance();
        // Inflate the layout for this fragment
        return view;
    }


    public void setShop(Shop shop) {
        shopList.addShop(shop);

    }
}
