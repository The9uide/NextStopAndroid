package th.ac.kmitl.it.nextstop.Model;

/**
 * Created by v-trrata on 2/19/2017.
 */

public class StationList {
    private static StationList instance = null;
    Station[] allStation;

    protected StationList() {
        allStation = new Station[8];
        allStation[0] = new Station("สุวรรณภูมิ","A1",null,13.698090,100.752265);
        allStation[1] = new Station("ลาดกระบัง","A2",null,13.727669,100.748717);
        allStation[2] = new Station("บ้านทับช้าง","A3",null,13.732827,100.691467);
        allStation[3] = new Station("หัวหมาก","A4",null,13.737958,100.645347);
        allStation[4] = new Station("รามคำแหง","A5",null,13.742959,100.600257);
        allStation[5] = new Station("มักกะสัน","A6","MRT เพชรบุรี",13.751017,100.561346);
        allStation[6] = new Station("ราชปรารภ","A7",null,13.755133,100.541826);
        allStation[7] = new Station("พญาไท","A8","BTS พญาไท",13.756711,100.534972);


    }
    public static StationList getStationList() {
        if(instance == null) {
            instance = new StationList();
        }
        return instance;
    }

}
