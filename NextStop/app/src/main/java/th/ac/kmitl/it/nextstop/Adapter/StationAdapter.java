package th.ac.kmitl.it.nextstop.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import th.ac.kmitl.it.nextstop.Model.Station;
import th.ac.kmitl.it.nextstop.R;
import th.ac.kmitl.it.nextstop.databinding.StationrowlayoutBinding;

/**
 * Created by The9uide on 25-Mar-17.
 */

public class StationAdapter extends ArrayAdapter<Station> {
    private final Context context;
    private final Station[] values;
    StationrowlayoutBinding binding;

    public StationAdapter(Context context, Station[] values) {
        super(context, R.layout.stationrowlayout, values);
        this.context = context;
        this.values = values;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        binding = StationrowlayoutBinding.inflate(inflater,parent,false);
        binding.setItem(values[position]);
//        View rowView = inflater.inflate(R.layout.stationrowlayout, parent, false);
//        TextView textView = (TextView) rowView.findViewById(R.id.icon);
//        ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
//        textView.setText(values[position]);
        // Change the icon for Windows and iPhone



        return binding.getRoot();
    }

}
