package hackathon.embrapa.agrohacker.model;


import com.google.android.gms.maps.model.Marker;

import java.util.ArrayList;
import java.util.Date;

public class FieldInspection {
    private Date realization;
    private String inspectorName;
    private ArrayList<Prague> foundedPrages;
    private ArrayList<NaturalPredator> foundedPredators;
    private String status;
    private Marker marker;

    public FieldInspection(Date date, String inspectorName, Marker marker){
        setRealization(date);
        setInspectorName(inspectorName);
        setMarker(marker);
    }

    public Date getRealization() {
        return realization;
    }

    public void setRealization(Date realization) {
        this.realization = realization;
    }

    public String getInspectorName() {
        return inspectorName;
    }

    public void setInspectorName(String inspectorName) {
        this.inspectorName = inspectorName;
    }

    public ArrayList<Prague> getFoundedPrages() {
        return foundedPrages;
    }

    public void setFoundedPrages(ArrayList<Prague> foundedPrages) {
        this.foundedPrages = foundedPrages;
    }

    public ArrayList<NaturalPredator> getFoundedPredators() {
        return foundedPredators;
    }

    public void setFoundedPredators(ArrayList<NaturalPredator> foundedPredators) {
        this.foundedPredators = foundedPredators;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Marker getMarker() {
        return marker;
    }

    public void setMarker(Marker marker) {
        this.marker = marker;
    }

}
