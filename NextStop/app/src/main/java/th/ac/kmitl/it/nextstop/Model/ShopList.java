package th.ac.kmitl.it.nextstop.Model;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;

import me.tatarka.bindingcollectionadapter2.BR;
import me.tatarka.bindingcollectionadapter2.ItemBinding;
import th.ac.kmitl.it.nextstop.R;

/**
 * Created by v-trrata on 5/26/2017.
 */

public class ShopList {
    public final ItemBinding<Shop> itemBinding = ItemBinding.of(BR.item, R.layout.stationcurrentrowlayout);
    public final ObservableList<Shop> items = new ObservableArrayList<>();
}
