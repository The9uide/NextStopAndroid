package th.ac.kmitl.it.nextstop.Model;

/**
 * Created by v-trrata on 2/19/2017.
 */

public class Station {
    private String name;
    private String id;
    private String connection;
    private float latitude;
    private float longitude;

    public Station(String name, String id, String connection ,float latitude, float longitude){
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

    public float getLatitude() {
        return latitude;
    }

    public float getLongitude() {
        return longitude;
    }
}
