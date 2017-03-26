package th.ac.kmitl.it.nextstop.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import th.ac.kmitl.it.nextstop.R;

/**
 * Created by The9uide on 25-Mar-17.
 */

public class StationAdapter extends ArrayAdapter<String> {
    private final Context context;
    private final String[] values;

    public StationAdapter(Context context, String[] values) {
        super(context, R.layout.stationrowlayout, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.stationrowlayout, parent, false);
        TextView textView = (TextView) rowView.findViewById(R.id.icon);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
        textView.setText(values[position]);
        // Change the icon for Windows and iPhone
        String s = values[position];


        return rowView;
    }

}
