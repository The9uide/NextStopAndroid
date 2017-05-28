package th.ac.kmitl.it.nextstop.Model;

import android.content.Intent;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;
import android.util.Log;
import android.view.View;

import me.tatarka.bindingcollectionadapter2.BR;
import me.tatarka.bindingcollectionadapter2.BindingListViewAdapter;
import me.tatarka.bindingcollectionadapter2.ItemBinding;
import me.tatarka.bindingcollectionadapter2.OnItemBind;
import th.ac.kmitl.it.nextstop.Activity.DepartSelectActivity;
import th.ac.kmitl.it.nextstop.Activity.DestinationActivity;
import th.ac.kmitl.it.nextstop.Activity.LandingActivity;
import th.ac.kmitl.it.nextstop.Activity.MainActivity;
import th.ac.kmitl.it.nextstop.Adapter.StationListViewAdapter;
import th.ac.kmitl.it.nextstop.R;
import th.ac.kmitl.it.nextstop.Service.LocationReceiver;

/**
 * Created by v-trrata on 2/19/2017.
 */

public class StationList {
    private static StationList stations = null;

    public final OnItemBind<Station> onItemBind = new OnItemBind<Station>() {
        @Override
        public void onItemBind(ItemBinding itemBinding, int position, Station item) {
            itemBinding.set(BR.item, setLayout(item));

        }
    };
    public final ItemBinding<Station> itemBinding = ItemBinding.of(onItemBind);
    public final ObservableList<Station> items = new ObservableArrayList<>();
    public BindingListViewAdapter<Station> adapter;
    public Station destinationStation;
    public Station departStation;
    public int timeToArrive;
    public LocationReceiver locationReceiver;
    public  int countNoti;

    protected StationList() {
        items.add(new Station("สุวรรณภูมิ", "A1", "สนามบิน สุวรรณภูมิ", 13.698090, 100.752265));
        items.add(new Station("ลาดกระบัง", "A2", null, 13.727669, 100.748717));
        items.add(new Station("บ้านทับช้าง", "A3", null, 13.732827, 100.691467));
        items.add(new Station("หัวหมาก", "A4", null, 13.737958, 100.645347));
        items.add(new Station("รามคำแหง", "A5", null, 13.742959, 100.600257));
        items.add(new Station("มักกะสัน", "A6", "MRT เพชรบุรี", 13.751017, 100.561346));
        items.add(new Station("ราชปรารภ", "A7", null, 13.755133, 100.541826));
        items.add(new Station("พญาไท", "A8", "BTS พญาไท", 13.756711, 100.534972));

        adapter = new StationListViewAdapter(items.size());
    }

    public static StationList getStations() {
        if (stations == null) {
            stations = new StationList();
        }
        return stations;
    }

    public Station getStationFormIndex(int position) {
        return items.get(position);
    }

    public Station getStationFormName(String name) {
        for (Station station : items) {
            if (station.getName().equals(name)) {
                return station;
            }
        }
        return null;
    }

    public String getStationStair(Station departStation, Station desStation) {

        int depart = getIndexWithStation(departStation);
        int destination = getIndexWithStation(desStation);
        Log.e("Stair Value", departStation.getName() + depart + " - " + desStation.getName() + destination + " = " + (depart - destination) + "");
        if (depart - destination < 0) {
            return "พญาไท";
        } else {
            return "สุวรรณภูมิ";
        }
    }

    private int getIndexWithStation(Station station) {
        for (int i = 0; i < items.size(); i++) {
            if (station.equals(items.get(i))) {
                return i;
            }
        }
        return 0;
    }

    public String[] getRouteTravel(Station departStation, Station desStation) {
        int depart = getIndexWithStation(departStation);
        int destination = getIndexWithStation(desStation);
        String[] route = new String[Math.abs(depart - destination) + 1];

        int index = 0;
        if (depart < destination) {
            for (int i = depart; i <= destination; i++) {
                route[index] = items.get(i).getName();
                index++;
            }
        } else if (depart > destination) {
            for (int i = depart; i >= destination; i--) {
                route[index] = items.get(i).getName();
                index++;
            }
        }
        return route;
    }

    private int setLayout(Station item) {
        if (item.isCurrent()) {
            return R.layout.stationcurrentrowlayout;
        } else if (item.getConnection() != null) {
            return R.layout.stationconnectrowlayout;

        } else {
            return R.layout.stationrowlayout;
        }
    }

    public void resetCurrentStation() {
        for (Station x : items) {
            x.setCurrent(false);
        }
    }
    public void setTravelDetail(Station departStation, Station destinationStation,int timeToArrive,LocationReceiver locationReceiver){
        this.departStation = departStation;
        this.destinationStation = destinationStation;
        this.timeToArrive = timeToArrive;
        this.locationReceiver = locationReceiver;
        this.countNoti = 0;
    }

    public int getImageResourceFormName(String name){
        if (name.equals("สุวรรณภูมิ")) {
            return R.drawable.a1;
        } else if (name.equals("ลาดกระบัง")) {
            return R.drawable.a2;
        } else if (name.equals("บ้านทับช้าง")) {
            return R.drawable.a3;
        } else if (name.equals("หัวหมาก")) {
            return R.drawable.a4;
        } else if (name.equals("รามคำแหง")) {
            return R.drawable.a5;
        } else if (name.equals("มักกะสัน")) {
            return R.drawable.a6;
        } else if (name.equals("ราชปรารภ")) {
            return R.drawable.a7;
        } else if (name.equals("พญาไท")) {
            return R.drawable.a8;
        }
        return R.drawable.a1;
    }
}
