package th.ac.kmitl.it.nextstop.Model;

import android.util.Log;

/**
 * Created by The9uide on 11-Apr-17.
 */

public class StationManager {
    private Station currentStation;
    private Station nextStation;
    double latitude;
    double longitude;

    public StationManager(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Station getCurrentStation() {
        currentStation = getNearestStation(latitude, longitude);
        return currentStation;
    }

    Station getNearestStation(double latitude, double longitude) {
        Station minStation = null;
        double minDistance = 0;
        double distance;
        for (Station s : StationList.getStations().items) {
            distance = getDistance(s, latitude, longitude);
            Log.v(s.getName(), distance + "");
            if (minStation == null & minDistance == 0) {
                minStation = s;
                minDistance = distance;
            } else if (distance < minDistance) {
                minStation = s;
                minDistance = distance;
            }
        }
        return minStation;
    }

    double getDistance(Station station, double latitude, double longitude) {
        double xPoint = Math.pow(station.getLatitude() - latitude, 2);
        double yPoint = Math.pow(station.getLongitude() - longitude, 2);
        return Math.sqrt(xPoint + yPoint);

    }
}
