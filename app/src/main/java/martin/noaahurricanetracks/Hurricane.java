package martin.noaahurricanetracks;

import android.graphics.Color;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Polyline;

import java.util.ArrayList;
import java.util.List;

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

    public Polyline getPolyline(){
        return polyline;
    }

    public void setPolyline(Polyline polyline){
        this.polyline = polyline;
    }

    private int season;
    private int num;
    private String basin;
    private String subBasin;
    private String name;
    private ArrayList<TrackPoint> trackPoints;
    private Polyline polyline;

    public Hurricane(String serialNumber, int season, int num, String basin, String subBasin, String name){
        this.serialNumber = serialNumber;
        this.season = season;
        this.num = num;
        this.basin = basin;
        this.subBasin = subBasin;
        this.name = name;
        this.trackPoints = new ArrayList<TrackPoint>();
    }

    public void addTrackPoint(Hurricane hurricane, String time, String nature, LatLng latLng, float wind, float pressure, String center, String trackType){
        trackPoints.add(new TrackPoint(hurricane, time,  nature, latLng, wind, pressure, center, trackType));
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
            list.add(this.trackPoints.get(i).getLatLng());
        }
        return list;
    }

    public void displayInfo(MapsActivity instance) {
        TextView tv = (TextView) instance.findViewById(R.id.trackPointTitleTextView);
        tv.setText(this.getName() + " " + this.getSeason());
        TextView tv2 = (TextView) instance.findViewById(R.id.trackPointInfoTextView);
        int minPressure = 2000;
        int maxWind = 0;
        for(TrackPoint trackPoint: trackPoints){
            if(trackPoint.getPressure()<minPressure){
                minPressure = (int) trackPoint.getPressure();
            }
            if(trackPoint.getWind()>maxWind){
                maxWind = (int) trackPoint.getWind();
            }
        }
        tv2.setText(trackPoints.get(0).getISO_time() + " - " + trackPoints.get(trackPoints.size()-1).getISO_time() + "\n" +
        "Min Pressure: " + minPressure + "mb    Max Wind: " + maxWind + "kt");
        //set up chart
        LineChart chart = (LineChart) instance.findViewById(R.id.chart);
        List<Entry> pressureEntries = new ArrayList<Entry>();
        List<Entry> windEntries = new ArrayList<Entry>();
        for(int i = 0; i<trackPoints.size(); i++){
           pressureEntries.add(new Entry(i, trackPoints.get(i).getPressure()));
           windEntries.add(new Entry(i, trackPoints.get(i).getWind()));
        }

        LineDataSet pressureDataSet = new LineDataSet(pressureEntries, "Pressure");
        pressureDataSet.setColor(Color.BLUE);
        pressureDataSet.setDrawValues(false);
        pressureDataSet.setDrawCircles(false);
        pressureDataSet.setAxisDependency(chart.getAxisLeft().getAxisDependency());

        LineDataSet windDataSet = new LineDataSet(windEntries, "Wind");
        windDataSet.setColor(Color.RED);
        windDataSet.setDrawValues(false);
        windDataSet.setDrawCircles(false);
        windDataSet.setAxisDependency(chart.getAxisRight().getAxisDependency());

        LineData lineData = new LineData();
        lineData.addDataSet(pressureDataSet);
        lineData.addDataSet(windDataSet);

        chart.setData(lineData);
        chart.invalidate();
    }
}
