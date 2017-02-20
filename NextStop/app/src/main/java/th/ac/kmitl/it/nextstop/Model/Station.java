package th.ac.kmitl.it.nextstop.Model;

/**
 * Created by v-trrata on 2/19/2017.
 */

public class Station {
    private String name;
    private String id;
    private String connection;
    private double latitude;
    private double longitude;

    public Station(String name, String id, String connection ,double latitude, double longitude){
        this.name = name;
        this.id = id;
        this.connection = connection;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getConnection() {
        return connection;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
}
