package hackathon.embrapa.agrohacker.model;

import java.util.ArrayList;

public class NaturalPredator extends Insect{

    private ArrayList<String> feedingPragues;


    public NaturalPredator(String culture, String scientificName,
                           String popularName, String group, String lifePeriod,
                           ArrayList<String> feedingPragues) {
        super(culture, scientificName, popularName, group, lifePeriod);
        setFeedingPragues(feedingPragues);
    }

    public ArrayList<String> getFeedingPragues() {
        return feedingPragues;
    }

    public void setFeedingPragues(ArrayList<String> feedingPragues) {
        this.feedingPragues = feedingPragues;
    }
}
