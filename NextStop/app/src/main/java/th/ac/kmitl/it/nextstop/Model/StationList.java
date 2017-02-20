package th.ac.kmitl.it.nextstop.Model;

/**
 * Created by v-trrata on 2/19/2017.
 */

public class StationList {
    private static StationList instance = null;
    Station[] allStation;
    protected StationList() {
        allStation = new Station[8];

    }
    public static StationList getStationList() {
        if(instance == null) {
            instance = new StationList();

        }
        return instance;
    }

}
