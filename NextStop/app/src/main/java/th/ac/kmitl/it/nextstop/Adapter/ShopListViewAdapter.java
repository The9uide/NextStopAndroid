package th.ac.kmitl.it.nextstop.Adapter;

import android.databinding.BindingAdapter;
import android.databinding.ViewDataBinding;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;

import me.tatarka.bindingcollectionadapter2.BindingListViewAdapter;
import th.ac.kmitl.it.nextstop.Model.Shop;
import th.ac.kmitl.it.nextstop.Model.Station;

/**
 * Created by v-trrata on 5/28/2017.
 */

public class ShopListViewAdapter extends BindingListViewAdapter<Shop> {
    /**
     * Constructs a new instance with the given item count.
     *
     * @param itemTypeCount
     */
    public ShopListViewAdapter(int itemTypeCount) {
        super(itemTypeCount);
    }
    @Override
    public ViewDataBinding onCreateBinding(LayoutInflater inflater, int layoutRes, ViewGroup viewGroup) {
        ViewDataBinding binding = super.onCreateBinding(inflater, layoutRes, viewGroup);
        Log.e("onCreateBinding", "created binding: " + binding);
        return binding;
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

    @BindingAdapter("android:src")
    public static void setImageBitmap(ImageView imageView, Bitmap resource) {
        imageView.setImageBitmap(resource);
    }
}
