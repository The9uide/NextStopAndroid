package th.ac.kmitl.it.nextstop.Model;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;

import me.tatarka.bindingcollectionadapter2.BR;
import me.tatarka.bindingcollectionadapter2.ItemBinding;
import th.ac.kmitl.it.nextstop.R;

/**
 * Created by v-trrata on 2/19/2017.
 */

public class StationList {
    private static StationList stations = null;
    public final ItemBinding<Station> itemBinding = ItemBinding.of(BR.item, R.layout.stationrowlayout);
    public final ObservableList<Station> items = new ObservableArrayList<>();

    protected StationList() {
        items.add(new Station("สุวรรณภูมิ","A1",null,13.698090,100.752265));
        items.add(new Station("ลาดกระบัง","A2",null,13.727669,100.748717));
        items.add(new Station("บ้านทับช้าง","A3",null,13.732827,100.691467));
        items.add(new Station("หัวหมาก","A4",null,13.737958,100.645347));
        items.add(new Station("รามคำแหง","A5",null,13.742959,100.600257));
        items.add(new Station("มักกะสัน","A6","MRT เพชรบุรี",13.751017,100.561346));
        items.add(new Station("ราชปรารภ","A7",null,13.755133,100.541826));
        items.add(new Station("พญาไท","A8","BTS พญาไท",13.756711,100.534972));

        items.add(new Station("สุวรรณภูมิ","A1",null,13.698090,100.752265));
        items.add(new Station("ลาดกระบัง","A2",null,13.727669,100.748717));
        items.add(new Station("บ้านทับช้าง","A3",null,13.732827,100.691467));
        items.add(new Station("หัวหมาก","A4",null,13.737958,100.645347));
        items.add(new Station("รามคำแหง","A5",null,13.742959,100.600257));
        items.add(new Station("มักกะสัน","A6","MRT เพชรบุรี",13.751017,100.561346));
        items.add(new Station("ราชปรารภ","A7",null,13.755133,100.541826));
        items.add(new Station("พญาไท","A8","BTS พญาไท",13.756711,100.534972));
        items.add(new Station("สุวรรณภูมิ","A1",null,13.698090,100.752265));
        items.add(new Station("ลาดกระบัง","A2",null,13.727669,100.748717));
        items.add(new Station("บ้านทับช้าง","A3",null,13.732827,100.691467));
        items.add(new Station("หัวหมาก","A4",null,13.737958,100.645347));
        items.add(new Station("รามคำแหง","A5",null,13.742959,100.600257));
        items.add(new Station("มักกะสัน","A6","MRT เพชรบุรี",13.751017,100.561346));
        items.add(new Station("ราชปรารภ","A7",null,13.755133,100.541826));
        items.add(new Station("พญาไท","A8","BTS พญาไท",13.756711,100.534972));

//        allStation = new Station[8];
//        allStation[0] = new Station("สุวรรณภูมิ","A1",null,13.698090,100.752265);
//        allStation[1] = new Station("ลาดกระบัง","A2",null,13.727669,100.748717);
//        allStation[2] = new Station("บ้านทับช้าง","A3",null,13.732827,100.691467);
//        allStation[3] = new Station("หัวหมาก","A4",null,13.737958,100.645347);
//        allStation[4] = new Station("รามคำแหง","A5",null,13.742959,100.600257);
//        allStation[5] = new Station("มักกะสัน","A6","MRT เพชรบุรี",13.751017,100.561346);
//        allStation[6] = new Station("ราชปรารภ","A7",null,13.755133,100.541826);
//        allStation[7] = new Station("พญาไท","A8","BTS พญาไท",13.756711,100.534972);


    }

    public static StationList getStations() {
        if(stations == null) {
            stations = new StationList();
        }
        return stations;
    }

}
