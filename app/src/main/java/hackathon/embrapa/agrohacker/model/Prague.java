package hackathon.embrapa.agrohacker.model;

import java.util.ArrayList;

public class Prague extends Insect {

    private String bioecology = "";
    private String damage = "";
    private String lifeCycleStage = "";
    private ArrayList<String> naturalPredators;
    private ArrayList<String> pesticides;

    public Prague(String popularName, String scientificName, String description, String damage, String bioecology,
                  String lifeCycleStage, ArrayList<String> naturalPredators, ArrayList<String> pesticides){

        super(popularName, scientificName, description);
        setDescription(description);
        setBioecology(bioecology);
        setDamage(damage);
        setLifeCycleStage(lifeCycleStage);
        setNaturalPredators(naturalPredators);
        setPesticides(pesticides);
    }

    public Prague(){}

    public String getBioecology() { return bioecology; }

    public void setBioecology(String bioecology) { this.bioecology = bioecology; }

    public String getDamage() { return damage; }

    public void setDamage(String damage) { this.damage = damage; }

    public String getLifeCycleStage() { return lifeCycleStage; }

    public void setLifeCycleStage(String lifeCicleStage) { this.lifeCycleStage = lifeCicleStage; }

    public ArrayList<String> getNaturalPredators() {
        return naturalPredators;
    }

    public void setNaturalPredators(ArrayList<String> naturalPredators) { this.naturalPredators = naturalPredators; }

    public ArrayList<String> getPesticides() {
        return pesticides;
    }

    public void setPesticides(ArrayList<String> pesticides) {
        this.pesticides = pesticides;
    }

    @Override
    public String toString() {
        return getId() + " - " + getPopularName();
    }
}
