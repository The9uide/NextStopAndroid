package th.ac.kmitl.it.nextstop.Model;

import android.content.res.Resources;
import android.util.Log;

import java.util.Arrays;

import th.ac.kmitl.it.nextstop.R;

/**
 * Created by The9uide on 11-Apr-17.
 */

public class StationManager {
    private Station currentStation;
    private Station nextStation;
    private double latitude;
    private double longitude;
    private StationList stationList = StationList.getStations();


    public StationManager(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Station getCurrentStation() {
        currentStation = getNearestStation(latitude, longitude);
        return currentStation;
    }

    public Station getNearestStation(double latitude, double longitude) {
        Station minStation = null;
        double minDistance = 0;
        double distance;
        for (Station s : stationList.items) {
            distance = getDistance(s, latitude, longitude);
            Log.e(s.getName(), distance + "");
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

    public double getDistance(Station station, double latitude, double longitude) {
        double xPoint = Math.pow(station.getLatitude() - latitude, 2);
        double yPoint = Math.pow(station.getLongitude() - longitude, 2);
        return Math.sqrt(xPoint + yPoint);
    }

    public String[] updateNexttation(String[] route){
        Station currentStation = stationList.getStationFormName(route[0]);
        if(route.length > 1){
            Station nextStation = stationList.getStationFormName(route[1]);
            Station nearestStation = getNearestStation(this.latitude,this.longitude);
            if (nearestStation.equals(nextStation)){
                return Arrays.copyOfRange(route, 1, route.length);
            }
        }

        return route;
    }

    public void updateLocation(double latitude, double longitude){
        this.latitude = latitude;
        this.longitude = longitude;
    }

    private void connectDestinationAPI(Station station){
        String origin = latitude + "," + longitude;
        String destination = station.getLatitude() + "," + station.getLongitude();
        String url = "https://maps.googleapis.com/maps/api/directions/json?mode=walking&amp;transit_mode=subway&amp;key=AIzaSyDJeJe29vIwfDDZ75g1MCtPWVZklhukzQY" + "&origin=" + origin + "&destination=" + destination;
        Log.e("URL", url);

        JSONAsyncTask task = new JSONAsyncTask(this);
        task.execute(url);
    }
}
