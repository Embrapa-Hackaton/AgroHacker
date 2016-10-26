package hackathon.embrapa.agrohacker.model;

import android.graphics.Color;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.Polygon;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;


public class Plot implements Serializable {

    private Integer id;
    private String plantationStage = "";
    private String plantationStartDate;
    private String harvestDate;
    private String platationCulture = "";
    private String status = "";
    private Polygon shape;
    private Marker plotMarker;
    private ArrayList<Trap> traps = new ArrayList<Trap>();
    private ArrayList<FieldInspection> fieldInspections = new ArrayList<FieldInspection>();

    private Double lat1;
    private Double lon1;
    private Double lat2;
    private Double lon2;
    private Double lat3;
    private Double lon3;
    private Double lat4;
    private Double lon4;

    public Plot(int id, Polygon shape, String platationCulture, String plantationStartDate, String harvestDate){
        setId(id);
        setShape(shape);
        setPlatationCulture(platationCulture);
        setPlantationStage("Plantação");
        setPlantationStartDate(plantationStartDate);
        setHarvestDate(harvestDate);
        setStatus("UKNOWN");
    }


    public Plot(int id, Polygon shape, String platationCulture) {
        setId(id);
        setShape(shape);
        setPlatationCulture(platationCulture);
        setStatus("UKNOWN");
    }

    public Plot(){

    }

    public Marker getPlotMarker() {
        return plotMarker;
    }

    public void setPlotMarker(Marker plotMarker) {
        this.plotMarker = plotMarker;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Polygon getShape() {
        return shape;
    }

    public void setShape(Polygon shape) {
        this.shape = shape;
    }

    public String getPlatationCulture() {
        return platationCulture;
    }

    public void setPlatationCulture(String platationCulture) {
        this.platationCulture = platationCulture;
    }

    public String getPlantationStage() {
        return plantationStage;
    }

    public void setPlantationStage(String plantationStage) {
        this.plantationStage = plantationStage;
    }

    public String getStatus() {
        return status;
    }

    public String getPlantationStartDate() {
        return plantationStartDate;
    }

    public void setPlantationStartDate(String plantationStartDate) {
        this.plantationStartDate = plantationStartDate;
    }

    public String getHarvestDate() {
        return harvestDate;
    }

    public void setHarvestDate(String harvestDate) {
        this.harvestDate = harvestDate;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ArrayList<FieldInspection> getFieldInspections() {
        return fieldInspections;
    }

    public void setFieldInspections(FieldInspection fieldInspection) {
        this.fieldInspections.add(fieldInspection);
        calculateStatus(status);
    }

    public ArrayList<Trap> getTraps() {
        return traps;
    }

    public void setTrap(Trap trap) {
        this.traps.add(trap);
    }

    private void calculateStatus(String status){
        String newStatus = "";
        for (int i = 0; i < fieldInspections.size(); i++){

            if(fieldInspections.get(i).getStatus().equals("URGENTE")){
                newStatus = "URGENTE";
                break;
            }
/*
            if(traps.get(i).getStatus().equals("URGENTE")){
                newStatus = "ALARMANTE";
                break;
            }*/

            else
                newStatus = "TRANQUILO"; // E FAVORAVEL
        }
        checkStatus(status, newStatus);
    }



    private void checkStatus(String oldStatus, String newStatus){
        if(!oldStatus.equals(newStatus)){
            setStatus(newStatus);
            changePlotColor();
        }else{
            //Do Nothing
        }
    }

    private void changePlotColor(){
        switch (status){
            case "URGENTE" :
                plotMarker.setIcon(BitmapDescriptorFactory
                        .defaultMarker(BitmapDescriptorFactory.HUE_RED));
                shape.setFillColor(0x66fd172f);
                shape.setStrokeWidth(4);
                break;
            case "ALARMANTE" :
                shape.setFillColor(Color.YELLOW);
                plotMarker.setIcon(BitmapDescriptorFactory
                        .defaultMarker(BitmapDescriptorFactory.HUE_YELLOW));
                shape.setFillColor(0xfaf41b);
                shape.setStrokeWidth(3);
                break;
            case "TRANQUILO" :
                plotMarker.setIcon(BitmapDescriptorFactory
                        .defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
                shape.setFillColor(0x33ef1d);
                shape.setStrokeWidth(2);
                break;
        }
    }

    public Double getLat1() {
        return lat1;
    }

    public void setLat1(Double lat1) {
        this.lat1 = lat1;
    }

    public Double getLon1() {
        return lon1;
    }

    public void setLon1(Double lon1) {
        this.lon1 = lon1;
    }

    public Double getLat2() {
        return lat2;
    }

    public void setLat2(Double lat2) {
        this.lat2 = lat2;
    }

    public Double getLon2() {
        return lon2;
    }

    public void setLon2(Double lon2) {
        this.lon2 = lon2;
    }

    public Double getLat3() {
        return lat3;
    }

    public void setLat3(Double lat3) {
        this.lat3 = lat3;
    }

    public Double getLon3() {
        return lon3;
    }

    public void setLon3(Double lon3) {
        this.lon3 = lon3;
    }

    public Double getLat4() {
        return lat4;
    }

    public void setLat4(Double lat4) {
        this.lat4 = lat4;
    }

    public Double getLon4() {
        return lon4;
    }

    public void setLon4(Double lon4) {
        this.lon4 = lon4;
    }

}
