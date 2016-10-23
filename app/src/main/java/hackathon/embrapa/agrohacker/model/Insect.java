package hackathon.embrapa.agrohacker.model;

import java.io.Serializable;

public class Insect implements Serializable {

    private Integer id;
    private String popularName = "";
    private String scientificName = "";
    private String description = "";
    private String photoPath = "";


    public Insect(String popularName, String scientificName, String description){
        setPopularName(popularName);
        setScientificName(scientificName);
        setDescription(description);
    }

    public Insect() {}

    public Integer getId() { return id; }

    public void setId(Integer id) { this.id = id; }

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

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    public String getPhotoPath() { return photoPath; }

    public void setPhotoPath(String photoPath) { this.photoPath = photoPath; }
}
