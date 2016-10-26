package hackathon.embrapa.agrohacker.controller;


import java.util.ArrayList;

import hackathon.embrapa.agrohacker.model.NaturalPredator;
import hackathon.embrapa.agrohacker.model.Prague;

public class InspectionPragueListViewController {

    ArrayList<Prague> pragues = new ArrayList<Prague>();

    public void setPragues(Prague prague) {
        pragues.add(prague);
    }
}
