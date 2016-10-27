package hackathon.embrapa.agrohacker.model;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.Serializable;
import java.util.Date;


public class Trap implements Serializable {

    private int id;
    private String lastChange;
    private Integer duration;
    private String pheromone;
    private String status;
    private Marker trapMarker;
    private Circle trapRange;
    private Double longitude;
    private Double latitude;


    public Trap(Marker trapMarker, Circle trapRange, String lastChange, int duration, String pheromone){
        setTrapRange(trapRange);
        setLastChange(lastChange);
        setDuration(duration);
        setPheromone(pheromone);
        setTrapMarker(trapMarker);
    }

    public Trap() {}

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public Circle getTrapRange(){
        return trapRange;
    }

    public void setTrapRange(Circle trapRange){
        this.trapRange = trapRange;
    }

    public String getLastChange() {
        return lastChange;
    }

    public void setLastChange(String lastChange) {
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
    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
}
