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
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

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


    //Poligon Creation

    ArrayList<Polyline> lines = new ArrayList<Polyline>();

    public void setPoligonMarker(LatLng latLng, GoogleMap mGoogleMap) {

        MarkerOptions marker = new MarkerOptions()
                .draggable(true)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW) )
                .position(new LatLng(latLng.latitude,latLng.longitude));

        markers.add(mGoogleMap.addMarker(marker));


        if(markers.size() == POLYGON_MAX_NUMBERS){
            drawPoligon(mGoogleMap);
            markers.clear();
            lines.clear();
        }
    }

    /*
    private void drawLine(GoogleMap mGoogleMap, Marker first, Marker second){

        PolylineOptions polyline = new PolylineOptions()
                .add(first.getPosition())
                .add(second.getPosition())
                .color(Color.GRAY)
                .width(3);

        lines.add(mGoogleMap.addPolyline(polyline));
    }*/


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
    }
/*
    public void initialize3Plots(GoogleMap mGoogleMap){
        Log.i("Entrou no method", "DAAm");
        Polygon polygon1 = mGoogleMap.addPolygon(new PolygonOptions()
                .add(new LatLng(0, 0), new LatLng(0, 5), new LatLng(3, 5), new LatLng(0, 0))
                .clickable(true)
                .strokeColor(Color.RED)
                .fillColor(Color.BLUE));

        Log.i("CRIOU 1", "COROLHO");

        Polygon polygon2 = mGoogleMap.addPolygon(new PolygonOptions()
                .add(new LatLng(0, 0), new LatLng(0, 10), new LatLng(2, 5), new LatLng(44, 12))
                .strokeColor(Color.RED)
                .clickable(true)
                .fillColor(Color.BLUE));

        Log.i("CRIOU 2", "COROLHO");

        Polygon polygon3 = mGoogleMap.addPolygon(new PolygonOptions()
                .add(new LatLng(0, 0), new LatLng(0, 5), new LatLng(3, 5), new LatLng(0, 0))
                .strokeColor(Color.RED)
                .clickable(true)
                .fillColor(Color.BLUE));

        Log.i("CRIOU 3", "COROLHO");

        Log.i("drawedPoligons", drawedPoligons+"");
        Plot plot1 = new Plot(drawedPoligons+1,polygon1,"Milho");
        drawedPoligons+=1;
        Plot plot2 = new Plot(drawedPoligons+1,polygon2,"Milho");
        drawedPoligons+=1;
        Plot plot3 = new Plot(drawedPoligons+1,polygon3,"Milho");
        drawedPoligons+=1;

        Log.i("drawedPoligons", drawedPoligons+"");

        plots.add(plot1);
        plots.add(plot2);
        plots.add(plot3);

        Log.i("Adicionou sá porra", "HUE");

    }*/
}
