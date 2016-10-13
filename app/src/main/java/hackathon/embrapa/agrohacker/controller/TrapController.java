package hackathon.embrapa.agrohacker.controller;


import android.graphics.Color;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

import hackathon.embrapa.agrohacker.model.Plot;


public class TrapController {

    public void addTrap(GoogleMap mGoogleMap, LatLng latLng, Plot plot){

        MarkerOptions marker = new MarkerOptions()
                .draggable(true)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW) )
                .position(new LatLng(latLng.latitude,latLng.longitude));

        mGoogleMap.addMarker(marker);

       Circle trapRange = drawCircle(mGoogleMap, latLng);

     //   Trap newTrap = new Trap(marker, trapRange, duration, pherormone);

     //   plot.setTrap(newTrap);
    }

    private Circle drawCircle(GoogleMap mGoogleMap, LatLng latlng){

        CircleOptions circle = new CircleOptions()
                            .center(latlng)
                            .radius(250)
                            .fillColor(Color.GRAY)
                            .strokeWidth(4)
                            .strokeColor(Color.BLUE);

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

