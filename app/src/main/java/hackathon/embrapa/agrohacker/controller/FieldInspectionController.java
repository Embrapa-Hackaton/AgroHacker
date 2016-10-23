package hackathon.embrapa.agrohacker.controller;

import android.util.Log;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import hackathon.embrapa.agrohacker.model.FieldInspection;
import hackathon.embrapa.agrohacker.model.NaturalPredator;
import hackathon.embrapa.agrohacker.model.Plot;
import hackathon.embrapa.agrohacker.model.Prague;

public class FieldInspectionController {
    public ArrayList<Prague> foundedpragues;
    public ArrayList<NaturalPredator> foundedPredators;

    public void addFieldInspection(GoogleMap mGoogleMap, Plot plot, LatLng latLng){
        Marker fieldInspectionMarker = addFieldInspectionMarker(mGoogleMap, latLng);

        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();

        FieldInspection fieldInspection = new FieldInspection(date,"Seu Chico",fieldInspectionMarker);
      //  setInspectionPragues(fieldInspection);
      //  setInspectionNaturalPredat(fieldInspection);
        plot.setFieldInspections(fieldInspection);
        Log.i("Inspeção adicionada", plot.getFieldInspections().size()+"");
    }


    private Marker addFieldInspectionMarker(GoogleMap mGoogleMap, LatLng latLng){

        MarkerOptions marker = new MarkerOptions()
                .draggable(true)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ROSE) )
                .position(new LatLng(latLng.latitude,latLng.longitude));

        return mGoogleMap.addMarker(marker);
    }

    private void setInspectionPragues(FieldInspection fieldInspection){
        fieldInspection.setFoundedPrages(foundedpragues);
    }

    private void setInspectionNaturalPredat(FieldInspection fieldInspection){
        fieldInspection.setFoundedPredators(foundedPredators);
    }


}