package th.ac.kmitl.it.nextstop.Adapter;

import android.databinding.BindingAdapter;
import android.databinding.ViewDataBinding;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;

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

        item.setConnectionLabel("จุดเชื่อมต่อ รถไฟฟ้า " + item.getConnection());
        item.setConnectionIcon("drawable/iconmrt");
        super.onBindBinding(binding, variableId, layoutRes, position, item);

        Log.e("onBindBinding", "variableId: " + variableId + "  layoutRes: " + layoutRes + " at position: " + position);
    }

    @BindingAdapter("android:src")
    public static void setImageUri(ImageView view, String imageUri) {
        if (imageUri == null) {
            view.setImageURI(null);
        } else {
            view.setImageURI(Uri.parse(imageUri));
        }
    }

    @BindingAdapter("android:src")
    public static void setImageUri(ImageView view, Uri imageUri) {
        view.setImageURI(imageUri);
    }

    @BindingAdapter("android:src")
    public static void setImageDrawable(ImageView view, Drawable drawable) {
        view.setImageDrawable(drawable);
    }

    @BindingAdapter("android:src")
    public static void setImageResource(ImageView imageView, int resource) {
        imageView.setImageResource(resource);
    }
}
