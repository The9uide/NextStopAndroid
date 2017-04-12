package th.ac.kmitl.it.nextstop.Model;

import android.content.Intent;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;
import android.util.Log;
import android.view.View;

import me.tatarka.bindingcollectionadapter2.BR;
import me.tatarka.bindingcollectionadapter2.ItemBinding;
import me.tatarka.bindingcollectionadapter2.OnItemBind;
import th.ac.kmitl.it.nextstop.Activity.DepartSelectActivity;
import th.ac.kmitl.it.nextstop.Activity.LandingActivity;
import th.ac.kmitl.it.nextstop.Activity.MainActivity;
import th.ac.kmitl.it.nextstop.R;

/**
 * Created by v-trrata on 2/19/2017.
 */

public class StationList {
    private static StationList stations = null;

    public final OnItemBind<Station> onItemBind = new OnItemBind<Station>() {
        @Override
        public void onItemBind(ItemBinding itemBinding, int position, Station item) {
            itemBinding.set(BR.item, item.getConnection() == null ? R.layout.stationrowlayout : R.layout.stationconnectrowlayout);

        }
    };
    public final ItemBinding<Station> itemBinding = ItemBinding.of(onItemBind);
    public final ObservableList<Station> items = new ObservableArrayList<>();

    protected StationList() {
        items.add(new Station("สุวรรณภูมิ", "A1", null, 13.698090, 100.752265));
        items.add(new Station("ลาดกระบัง", "A2", null, 13.727669, 100.748717));
        items.add(new Station("บ้านทับช้าง", "A3", null, 13.732827, 100.691467));
        items.add(new Station("หัวหมาก", "A4", null, 13.737958, 100.645347));
        items.add(new Station("รามคำแหง", "A5", null, 13.742959, 100.600257));
        items.add(new Station("มักกะสัน", "A6", "MRT เพชรบุรี", 13.751017, 100.561346));
        items.add(new Station("ราชปรารภ", "A7", null, 13.755133, 100.541826));
        items.add(new Station("พญาไท", "A8", "BTS พญาไท", 13.756711, 100.534972));


        items.add(new Station("สุวรรณภูมิ", "A1", null, 13.698090, 100.752265));
        items.add(new Station("ลาดกระบัง", "A2", null, 13.727669, 100.748717));
        items.add(new Station("บ้านทับช้าง", "A3", null, 13.732827, 100.691467));
        items.add(new Station("หัวหมาก", "A4", null, 13.737958, 100.645347));
        items.add(new Station("รามคำแหง", "A5", null, 13.742959, 100.600257));
        items.add(new Station("มักกะสัน", "A6", "MRT เพชรบุรี", 13.751017, 100.561346));
        items.add(new Station("ราชปรารภ", "A7", null, 13.755133, 100.541826));
        items.add(new Station("พญาไท", "A8", "BTS พญาไท", 13.756711, 100.534972));


    }

    public static StationList getStations() {
        if (stations == null) {
            stations = new StationList();
        }
        return stations;
    }

    public Station getStationFormIndex(int position){
        return items.get(position);
    }

    public Station getStationFormName(String name){
        for(Station station: items){
            if(station.getName().equals(name)){
                return station;
            }
        }
        return null;
    }

}
