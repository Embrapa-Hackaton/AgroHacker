package hackathon.embrapa.agrohacker.model;

public class Insect {

    private Integer id;
    private String culture = "";
    private String scientificName = "";
    private String lifePeriod;
    private String popularName = "";
    private String group = "";

    public Insect(String culture, String scientificName, String popularName, String group, String lifePeriod){
        setCulture(culture);
        setScientificName(scientificName);
        setPopularName(popularName);
        setLifePeriod(lifePeriod);
        setGroup(group);
    }

    public Insect() {}

    public String getCulture() {
        return culture;
    }

    public void setCulture(String culture) {
        this.culture = culture;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getPopularName() {
        return popularName;
    }

    public void setPopularName(String popularName) {
        this.popularName = popularName;
    }

    public String getScientificName() {
        return scientificName;
    }

    public void setScientificName(String scientificName) {
        this.scientificName = scientificName;
    }

    public String getLifePeriod() {
        return lifePeriod;
    }

    public void setLifePeriod(String lifePeriod) {
        this.lifePeriod = lifePeriod;
    }

    public Integer getId() { return id; }

    public void setId(Integer id) { this.id = id; }
}
