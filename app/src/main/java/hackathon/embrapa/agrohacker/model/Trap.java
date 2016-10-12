package hackathon.embrapa.agrohacker.model;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Date;


public class Trap {
    LatLng latLng;
    Date lastChange;
    Integer duration;
    String pheromone;
    Marker trapMarker;
    String status;

    public Trap(GoogleMap googleMap, LatLng latLng, Date lastChange, int duration, String pheromone){
        setLatLng(latLng);
        setLastChange(lastChange);
        setDuration(duration);
        setPheromone(pheromone);
        createMarker(googleMap, latLng);
    }

    public LatLng getLatLng(){
        return latLng;
    }

    public void setLatLng(LatLng latLng) {
        this.latLng = latLng;
    }

    public Date getLastChange() {
        return lastChange;
    }

    public void setLastChange(Date lastChange) {
        this.lastChange = lastChange;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getPheromone() {
        return pheromone;
    }

    public void setPheromone(String pheromone) {
        this.pheromone = pheromone;
    }

    public Marker getTrapMarker() {
        return trapMarker;
    }

    public void setTrapMarker(Marker trapMarker) {
        this.trapMarker = trapMarker;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    private void createMarker(GoogleMap mGoogleMap, LatLng latLng){
        MarkerOptions marker = new MarkerOptions()
                .position(new LatLng(latLng.latitude,latLng.longitude));
       trapMarker =  mGoogleMap.addMarker(marker);
    };
}
