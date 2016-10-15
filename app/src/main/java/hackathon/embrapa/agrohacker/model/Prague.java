package hackathon.embrapa.agrohacker.model;

import java.util.ArrayList;

public class Prague extends Insect {

    private String atackPeriod = "";
    private ArrayList<String> naturalPredators;
    private String damageType = "";
    private ArrayList<String> pesticides;

    public Prague(String culture, String scientificName, String popularName, String group, String lifePeriod,
                  String atackPeriod,ArrayList<String> naturalPredators,
                  String damageType, ArrayList<String> pesticides){
        super(culture, scientificName, popularName, group,lifePeriod);
        setAtackPeriod(atackPeriod);
        setNaturalPredators(naturalPredators);
        setDamageType(damageType);
        setPesticides(pesticides);
    }

    public Prague(){}


    public String getAtackPeriod() {
        return atackPeriod;
    }

    public void setAtackPeriod(String atackPeriod) {
        this.atackPeriod = atackPeriod;
    }

    public ArrayList<String> getNaturalPredator() {
        return naturalPredators;
    }

    public void setNaturalPredators(ArrayList<String> naturalPredators) {
        this.naturalPredators = naturalPredators;
    }

    public String getDamageType() {
        return damageType;
    }

    public void setDamageType(String damageType) {
        this.damageType = damageType;
    }

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
