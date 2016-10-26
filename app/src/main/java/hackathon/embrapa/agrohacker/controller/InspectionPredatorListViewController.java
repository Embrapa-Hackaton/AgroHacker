package hackathon.embrapa.agrohacker.controller;

import java.util.ArrayList;

import hackathon.embrapa.agrohacker.model.NaturalPredator;

public class InspectionPredatorListViewController {

    ArrayList<NaturalPredator> predators = new ArrayList<NaturalPredator>();

    public void setNaturalPredators(NaturalPredator naturalPredator) {
        predators.add(naturalPredator);
    }
}
