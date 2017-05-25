package th.ac.kmitl.it.nextstop.Model;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;

import me.tatarka.bindingcollectionadapter2.BR;
import me.tatarka.bindingcollectionadapter2.ItemBinding;
import me.tatarka.bindingcollectionadapter2.OnItemBind;
import th.ac.kmitl.it.nextstop.R;

/**
 * Created by v-trrata on 5/6/2017.
 */

public class Route {
    public final ObservableList<String> items = new ObservableArrayList<>();
    public final OnItemBind<String> onItemBind = new OnItemBind<String>() {
        @Override
        public void onItemBind(ItemBinding itemBinding, int position, String item) {
            itemBinding.set(BR.item, setLayout(position));

        }
    };

    public final ItemBinding<String> itemBinding = ItemBinding.of(onItemBind);

    public void addStation(String name) {
        items.add(name);
    }

    public void updateRoute(String[] route) {
        items.clear();
        if (route != null) {
            for (String name : route) {
                items.add(name);
            }
        }
    }

    private int setLayout(int position) {
        if (position == items.size()-1) {
            return R.layout.row_route_destination;
        } else {
            return R.layout.row_route;
        }
    }
}
