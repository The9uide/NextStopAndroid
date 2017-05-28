package th.ac.kmitl.it.nextstop.Model;

import android.util.Log;

import java.util.Arrays;


/**
 * Created by The9uide on 11-Apr-17.
 */

public class StationManager {
    private Station currentStation;
    private double latitude;
    private double longitude;
    private StationList stationList = StationList.getStations();
    private double totalDistance;
    private int baseTime;
    private Station destinationStation;


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

    public String[] updateNexttation(String[] route) {
        if (route.length > 1) {
            Station nextStation = stationList.getStationFormName(route[1]);
            Station nearestStation = getNearestStation(this.latitude, this.longitude);
            if (nearestStation.equals(nextStation)) {
                return Arrays.copyOfRange(route, 1, route.length);
            }
        }

        return route;
    }

    public void updateLocation(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    private void connectDestinationAPI(Station station) {
        String origin = latitude + "," + longitude;
        String destination = station.getLatitude() + "," + station.getLongitude();
        String url = "https://maps.googleapis.com/maps/api/directions/json?mode=walking&amp;transit_mode=subway&amp;key=AIzaSyDJeJe29vIwfDDZ75g1MCtPWVZklhukzQY" + "&origin=" + origin + "&destination=" + destination;
        Log.e("URL", url);

        JSONAsyncTask task = new JSONAsyncTask(this);
        task.execute(url);
    }

    public void setupBaseTime(int time, Station departStation, Station destinationStation) {
        this.baseTime = time;
        this.totalDistance = getDistance(departStation, destinationStation.getLatitude(), destinationStation.getLongitude());
        this.destinationStation = destinationStation;
    }

    public int updateTimeToArrive() {
        double currentDistance = getDistance(destinationStation, this.latitude, this.longitude);
        Log.e("Time", baseTime + " " + currentDistance + " " + totalDistance);
        int time = (int) (baseTime * currentDistance / totalDistance);
        return time;
    }


}
