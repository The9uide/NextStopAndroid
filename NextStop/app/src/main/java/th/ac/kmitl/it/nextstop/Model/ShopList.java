package th.ac.kmitl.it.nextstop.Model;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;

import java.util.List;

import me.tatarka.bindingcollectionadapter2.BR;
import me.tatarka.bindingcollectionadapter2.BindingListViewAdapter;
import me.tatarka.bindingcollectionadapter2.ItemBinding;
import th.ac.kmitl.it.nextstop.Adapter.ShopListViewAdapter;
import th.ac.kmitl.it.nextstop.Adapter.StationListViewAdapter;
import th.ac.kmitl.it.nextstop.R;

/**
 * Created by v-trrata on 5/26/2017.
 */

public class ShopList {
    public final ItemBinding<Shop> itemBinding = ItemBinding.of(BR.item, R.layout.row_shop);
    public final ObservableList<Shop> items = new ObservableArrayList<>();
    public BindingListViewAdapter<Shop> adapter;

    public void addShop(Shop shop){
        items.add(shop);
    }

    public void addList(List<Shop> shops){
        if(shops.size() > 0){
            items.clear();
            items.addAll(shops);
            adapter = new ShopListViewAdapter(items.size());
        }

    }
}
