package hackathon.embrapa.agrohacker.model;

import java.util.ArrayList;

public class NaturalPredator extends Insect{

    private ArrayList<String> feedingPragues;


    public NaturalPredator(String culture, String scientificName,
                           String popularName, String groups, String lifePeriod,
                           ArrayList<String> feedingPragues) {
        super(culture, scientificName, popularName, groups, lifePeriod);
        setFeedingPragues(feedingPragues);
    }

    public ArrayList<String> getFeedingPragues() {
        return feedingPragues;
    }

    public void setFeedingPragues(ArrayList<String> feedingPragues) {
        this.feedingPragues = feedingPragues;
    }
}
