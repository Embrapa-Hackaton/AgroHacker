package hackathon.embrapa.agrohacker.controller;


import android.graphics.Color;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

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
}

