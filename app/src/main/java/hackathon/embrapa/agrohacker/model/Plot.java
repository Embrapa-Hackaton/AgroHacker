package hackathon.embrapa.agrohacker.model;

import android.graphics.Color;

import com.google.android.gms.maps.model.Polygon;

import java.util.ArrayList;
import java.util.Date;


public class Plot {

    private Integer id;
    private String plantationStage = "";
    private Date plantationStartDate;
    private Date harvestDate;
    private String platationCulture = "";
    private String status = "";
    private Polygon shape;
    private ArrayList<Trap> traps = new ArrayList<Trap>();
    private ArrayList<FieldInspection> fieldInspections = new ArrayList<FieldInspection>();

/*
    public Plot(int index, Polygon shape, String platationCulture, Date plantationStartDate, Date harvestDate){
        setIndex(index);
        setShape(shape);
        setPlatationCulture(platationCulture);
        setPlantationStage("Plantação");
        setPlantationStartDate(plantationStartDate);
        setHarvestDate(harvestDate);
        setStatus("UKNOWN");
    }*/

    public Plot(int id, Polygon shape, String platationCulture){
        setId(id);
        setShape(shape);
        setPlatationCulture(platationCulture);
        setPlantationStage("Plantação");
        setStatus("UKNOWN");
    }

    public Plot(){

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

    public Date getPlantationStartDate() {
        return plantationStartDate;
    }

    public void setPlantationStartDate(Date plantationStartDate) {
        this.plantationStartDate = plantationStartDate;
    }

    public Date getHarvestDate() {
        return harvestDate;
    }

    public void setHarvestDate(Date harvestDate) {
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

            if(traps.get(i).getStatus().equals("URGENTE")){
                newStatus = "ALARMANTE";
                break;
            }

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
                shape.setFillColor(Color.RED);
                shape.setStrokeWidth(6);
                break;
            case "ALARMANTE" :
                shape.setFillColor(Color.YELLOW);
                shape.setStrokeWidth(5);
                break;
            case "TRANQUILO" :
                shape.setFillColor(Color.GREEN);
                shape.setStrokeWidth(4);
                break;
        }
    }

}
