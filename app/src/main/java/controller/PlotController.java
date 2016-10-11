package controller;

import android.graphics.Color;
import android.util.Log;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;

import java.util.ArrayList;

import model.Plot;

public class PlotController {

    ArrayList<Plot> plots = new ArrayList<Plot>();

    ArrayList<Marker> markers = new ArrayList<Marker>();
    final static int POLYGON_MAX_NUMBERS = 4;
    private int drawedPoligons = 0;

    ArrayList<Polygon> mapPoligons = new ArrayList<Polygon>();
    Polygon shape;


    //Find Plot
    public Plot findPlotbyShape(Polygon shape) {
        int i;
        for (i = 0; i < plots.size(); i++) {
            if (plots.get(i).getShape().equals(shape))
                break;
        }
        Log.i("Found the polygon", "contais index"+ i);
        return plots.get(i);
    }


/*
    //Poligon Creation
    public void setPoligonMarker(LatLng latLng, GoogleMap mGoogleMap) {

        MarkerOptions marker = new MarkerOptions()
                .draggable(true)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW) )
                .position(new LatLng(latLng.latitude,latLng.longitude));
        markers.add(mGoogleMap.addMarker(marker));

        if(markers.size() == POLYGON_MAX_NUMBERS){
            drawPoligon(mGoogleMap);
            markers.clear();
        }
    }


    private void drawPoligon(GoogleMap mGoogleMap){
        PolygonOptions options = new PolygonOptions()
                .fillColor(0x660000FF)
                .strokeWidth(4)
                .strokeColor(Color.BLUE);

        for(int i = 0; i < POLYGON_MAX_NUMBERS; i++){
            options.add(markers.get(i).getPosition());
            markers.get(i).remove();
        }
        shape = mGoogleMap.addPolygon(options);
        mapPoligons.add(shape);
    }*/
}
