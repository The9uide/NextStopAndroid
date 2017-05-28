package th.ac.kmitl.it.nextstop.Fragment;


import android.databinding.DataBindingUtil;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

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
    private List<Shop> shops;

    public ShopFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String url = getString(R.string.foursquare_api);
        FoursquareAsyncTask task = new FoursquareAsyncTask(this);
        task.execute(url);
    }

    public void initInstance() {

        shopList = new ShopList();
        binding.setViewModel(shopList);
        shopList.addShop(new Shop("กำลังหาสถานที่บริเวณโดยรอบ","โปรดรอสักครู่...", BitmapFactory.decodeResource(getContext().getResources(), R.drawable.iconlandingpage) ));

        if (shops != null){
            setShop(shops);
        }

        String url = getString(R.string.url_api) + "&origin=" + 0 + "&destination=" + 0;
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


    public void setShop(List<Shop> shops) {
        this.shops = shops;
        shopList.addList(shops);
    }
}
