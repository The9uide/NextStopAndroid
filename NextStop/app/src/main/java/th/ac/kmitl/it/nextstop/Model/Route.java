package th.ac.kmitl.it.nextstop.Model;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;

import me.tatarka.bindingcollectionadapter2.BR;
import me.tatarka.bindingcollectionadapter2.ItemBinding;
import th.ac.kmitl.it.nextstop.R;

/**
 * Created by v-trrata on 5/6/2017.
 */

public class Route {
    public final ObservableList<String> items = new ObservableArrayList<>();
    public final ItemBinding<String> itemBinding = ItemBinding.of(BR.item, R.layout.row_route);

    public void mockStation(){
        items.add("Station1");
        items.add("Station2");
        items.add("Station3");
        items.add("Station4");
        items.add("Station5");
        items.add("Station6");
        items.add("Station7");
        items.add("Station8");
    }

    public void addStation(String name){
        items.add(name);

    }
    public void updateRoute(String[] route){
        items.clear();
        for(String name : route){
            items.add(name);
        }

    }
}
