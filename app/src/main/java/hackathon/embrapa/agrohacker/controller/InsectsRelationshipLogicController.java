package hackathon.embrapa.agrohacker.controller;

import hackathon.embrapa.agrohacker.model.FieldInspection;

public class InsectsRelationshipLogicController {

    public void setInspectionStatus(FieldInspection fieldInspection){
        for(int i = 0; i < fieldInspection.getFoundedPrages().size(); i++){
            if(fieldInspection.getFoundedPrages().get(i).getScientificName().equalsIgnoreCase("diatraea saccharalis")){
                fieldInspection.setStatus("URGENTE");
            }
        }
        if(fieldInspection.getStatus()!="URGENTE"){
            if(fieldInspection.getFoundedPrages().size()!=0){
                fieldInspection.setStatus("ALARMANTE");
            }else
                fieldInspection.setStatus("TRANQUILO");
        }
    }
}
