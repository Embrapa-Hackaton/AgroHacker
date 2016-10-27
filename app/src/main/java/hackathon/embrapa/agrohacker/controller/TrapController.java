package hackathon.embrapa.agrohacker.controller;


import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.View;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.Date;

import hackathon.embrapa.agrohacker.dao.TrapDAO;
import hackathon.embrapa.agrohacker.model.Plot;
import hackathon.embrapa.agrohacker.model.Trap;


public class TrapController {

    PlotController plotController;
    Marker trapMarker;
    Circle trapRange;

    public void createTrap(String pherormone, int duration, String lastChange, Context context){
        Log.i("Chegou aqui", "createTrap. TrapCon.");
        try {
            Log.i("Latlng: ", trapMarker.getPosition().toString());
            Log.i("range: ", trapRange.getRadius()+"");
            Log.i("Last change: ", lastChange.toString());
            Log.i("duration: ", duration+"");
            Log.i("pherormone: ", pherormone);
            Trap newTrap = new Trap(trapMarker, trapRange, lastChange, duration, pherormone);
            newTrap.setLatitude(newTrap.getTrapMarker().getPosition().latitude);
            newTrap.setLongitude(newTrap.getTrapMarker().getPosition().longitude);
            plotController.setTrapOnPlot(newTrap);
        }catch (NullPointerException e){
            e.printStackTrace();
        }
    }

    public void addTrapOnMap(GoogleMap mGoogleMap, LatLng latLng, View mapView){

        trapMarker = addTrapMarker(mGoogleMap, latLng);
        trapRange = drawCircle(mGoogleMap, latLng);

        double doubles[] = {latLng.latitude, latLng.longitude};

        Intent intent = new Intent();
        intent.putExtra("points", doubles);
        intent.setClass(mapView.getContext(), TrapFormController.class);
        mapView.getContext().startActivity(intent);
    }

    public Marker addTrapMarker(GoogleMap mGoogleMap, LatLng latLng){

        MarkerOptions marker = new MarkerOptions()
                .draggable(true)
                .title("Armadilha")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN))
                .position(new LatLng(latLng.latitude,latLng.longitude));

        return mGoogleMap.addMarker(marker);
    }

    public Circle drawCircle(GoogleMap mGoogleMap, LatLng latlng){

        CircleOptions circle = new CircleOptions()
                            .center(latlng)
                            .radius(250)
                            .strokeWidth(4)
                            .strokeColor(Color.BLACK);

        return mGoogleMap.addCircle(circle);
    }

    public boolean checkTrapIsInsidePlot(LatLng tap, ArrayList<LatLng> vertices){
        int intersectCount = 0;
        for (int j = 0; j < vertices.size() - 1; j++) {
            if (checkCastIntersect(tap, vertices.get(j), vertices.get(j + 1))) {
                intersectCount++;
            }
        }

        return ((intersectCount % 2) == 1); // odd = inside, even = outside;
    }

    private boolean checkCastIntersect(LatLng tap, LatLng vertA, LatLng vertB) {

        double aY = vertA.latitude;
        double bY = vertB.latitude;
        double aX = vertA.longitude;
        double bX = vertB.longitude;
        double pY = tap.latitude;
        double pX = tap.longitude;

        if ((aY > pY && bY > pY) || (aY < pY && bY < pY)
                || (aX < pX && bX < pX)) {
            return false; // a and b can't both be above or below pt.y, and a or
            // b must be east of pt.x
        }

        double m = (aY - bY) / (aX - bX);
        double bee = (-aX) * m + aY;
        double x = (pY - bee) / m;

        return x > pX;
    }
}

