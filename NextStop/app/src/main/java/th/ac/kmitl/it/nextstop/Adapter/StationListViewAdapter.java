package th.ac.kmitl.it.nextstop.Adapter;

import android.databinding.ViewDataBinding;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import me.tatarka.bindingcollectionadapter2.BindingListViewAdapter;
import th.ac.kmitl.it.nextstop.Model.Station;

/**
 * Created by v-trrata on 4/20/2017.
 */

public class StationListViewAdapter extends BindingListViewAdapter<Station> {
    /**
     * Constructs a new instance with the given item count.
     *
     * @param itemTypeCount
     */
    public StationListViewAdapter(int itemTypeCount) {
        super(itemTypeCount);
    }

    @Override
    public ViewDataBinding onCreateBinding(LayoutInflater inflater, int layoutRes, ViewGroup viewGroup) {
        ViewDataBinding binding = super.onCreateBinding(inflater, layoutRes, viewGroup);
        Log.e("onCreateBinding", "created binding: " + binding);
        return binding;
    }

    @Override
    public void onBindBinding(ViewDataBinding binding, int variableId, int layoutRes, int position, Station item) {

//        item.setConnection("จุดเชื่อมต่อ รถไฟฟ้า " + item.getConnection());
        super.onBindBinding(binding, variableId, layoutRes, position, item);

        Log.e("onBindBinding", "variableId: " + variableId + "  layoutRes: " + layoutRes + " at position: " + position);
    }


}
