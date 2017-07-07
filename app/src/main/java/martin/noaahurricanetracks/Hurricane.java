package martin.noaahurricanetracks;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

/**
 * Created by Martin on 7/6/2017.
 */

public class Hurricane {

    private String serialNumber;

    public String getSerialNumber() {
        return serialNumber;
    }

    public int getSeason() {
        return season;
    }

    public int getNum() {
        return num;
    }

    public String getBasin() {
        return basin;
    }

    public String getSubBasin() {
        return subBasin;
    }

    public String getName() {
        return name;
    }

    private int season;
    private int num;
    private String basin;
    private String subBasin;
    private String name;
    private ArrayList<TrackPoint> trackPoints;

    public Hurricane(String serialNumber, int season, int num, String basin, String subBasin, String name){
        this.serialNumber = serialNumber;
        this.season = season;
        this.num = num;
        this.basin = basin;
        this.subBasin = subBasin;
        this.name = name;
        this.trackPoints = new ArrayList<TrackPoint>();
    }

    public void addTrackPoint(String time, String nature, float latitude, float longitude, float wind, float pressure, String center, String trackType){
        trackPoints.add(new TrackPoint(time,  nature, latitude, longitude, wind, pressure, center, trackType));
    }

    public String toString(){
        return "Name: " + this.name + " Season: " + this.season + " Basin(SubBasin): " + this.basin + "(" + this.subBasin + ")";
    }

    public ArrayList<TrackPoint> getTrackPoints(){
        return this.trackPoints;
    }

    public ArrayList<LatLng> getLatLngs(){
        ArrayList<LatLng> list = new ArrayList<LatLng>();
        for(int i = 0; i<this.trackPoints.size(); i++){
            list.add(new LatLng(this.trackPoints.get(i).getLatitude(),this.trackPoints.get(i).getLongitude()));
        }
        return list;
    }

}
