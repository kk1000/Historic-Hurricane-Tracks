package martin.noaahurricanetracks;

import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;

/**
 * Created by Martin on 7/6/2017.
 */

public class TrackPoint{
    private Hurricane hurricane;
    private String ISO_time;
    private String nature;
    private LatLng latLng;
    private float wind;
    private float pressure;
    private String center;
    private String trackType;
    private Marker marker;

    public Hurricane getHurricane(){ return hurricane;}

    public String getISO_time() {
        return ISO_time;
    }

    public String getNature() {
        return nature;
    }

    public LatLng getLatLng(){
        return latLng;
    }

    public float getWind() {
        return wind;
    }

    public float getPressure() {
        return pressure;
    }

    public String getCenter() {
        return center;
    }

    public String getTrackType() {
        return trackType;
    }

    public Marker getMarker(){
        return marker;
    }

    public void displayInfo(MapsActivity instance){
        TextView tv = (TextView) instance.findViewById(R.id.trackPointTitleTextView);
        tv.setText(this.getHurricane().getName() + " " + this.getHurricane().getSeason());
        TextView tv2 = (TextView) instance.findViewById(R.id.trackPointInfoTextView);
        tv2.setText("Date: " + this.getISO_time() + "\n" +
                "Pressure(mb): " + this.getPressure() + " Wind(kt): " + this.getWind());
    }

    public void setMarker(Marker marker){
        this.marker = marker;
    }

    TrackPoint(Hurricane hurricane,String time, String nature, LatLng latLng, float wind, float pressure, String center, String trackType){
        this.hurricane = hurricane;
        this.ISO_time = time;
        this.nature = nature;
        this.latLng = latLng;
        this.wind = wind;
        this.pressure = pressure;
        this.center = center;
        this.trackType = trackType;
    }


}