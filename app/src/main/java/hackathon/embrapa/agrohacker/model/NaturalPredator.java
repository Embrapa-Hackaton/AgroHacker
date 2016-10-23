package hackathon.embrapa.agrohacker.model;

import java.util.ArrayList;

public class NaturalPredator extends Insect{

    private ArrayList<String> feedingPragues;
    private String importance = "";

    public NaturalPredator(String popularName, String scientificName, String description, String importance,
                           ArrayList<String> feedingPragues) {

        super(popularName, scientificName, description);
        setImportance(importance);
        setFeedingPragues(feedingPragues);
    }

    public NaturalPredator(){}

    public String getImportance() { return importance; }

    public void setImportance(String importance) { this.importance = importance; }

    public ArrayList<String> getFeedingPragues() {
        return feedingPragues;
    }

    public void setFeedingPragues(ArrayList<String> feedingPragues) { this.feedingPragues = feedingPragues; }
}
