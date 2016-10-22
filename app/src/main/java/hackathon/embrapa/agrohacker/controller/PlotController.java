package hackathon.embrapa.agrohacker.controller;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;

import java.util.ArrayList;
import java.util.Date;

import hackathon.embrapa.agrohacker.model.Plot;

public class PlotController {

    ArrayList<Plot> plots = new ArrayList<Plot>();

    private ArrayList<Marker> markers = new ArrayList<Marker>();
    private final static int POLYGON_MAX_NUMBERS = 4;
    public int drawedPerTime = 0;

    ArrayList<Polygon> plotPoligons = new ArrayList<Polygon>();
    Polygon shape;
    Marker center;

    boolean drawedTheLast = false;


    public void addPlot(String culture, Date plantationDate, Date harvestDate){

        Log.i("plots : ", plots.size()+"");

        Plot plot = new Plot(plotPoligons.size(),shape, culture, plantationDate, harvestDate);
        plot.setPlotMarker(center);

        try {

            plots.add(plot);
            Log.i("plots : ", plots.size()+"");
        }catch (Exception exception){

            Log.i("Nao cadastrou good : ", "Daam");
        }
    }

    //Find Plot
    public Plot findPlotbyShape(Polygon shape) {
        int i;
        for (i = 0; i < plotPoligons.size(); i++) {
            Log.i("still", "SEARCHING");
            if (plots.get(i).getShape().equals(shape))
                break;
        }
        Log.i("Found the polygon", "contais index"+ i);
        return plots.get(i);
    }

    //Poligon Creation

    public void setPoligonMarker(LatLng latLng, GoogleMap mGoogleMap, Context context) {

        MarkerOptions marker = new MarkerOptions()
                .draggable(true)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW) )
                .position(new LatLng(latLng.latitude,latLng.longitude));

        markers.add(mGoogleMap.addMarker(marker));


        if(markers.size() == POLYGON_MAX_NUMBERS){
            drawPoligon(mGoogleMap, context);
            markers.clear();
        }
    }

    private void drawPoligon(GoogleMap mGoogleMap, Context context){

        PolygonOptions options = new PolygonOptions()
                .fillColor(0x660000FF)
                .strokeWidth(4)
                .clickable(true)
                .strokeColor(Color.BLUE);


        for(int i = 0; i < POLYGON_MAX_NUMBERS; i++){
            options.add(markers.get(i).getPosition());
            markers.get(i).remove();
        }

        drawedPerTime++;

        shape = mGoogleMap.addPolygon(options);
        plotPoligons.add(shape);

        Toast.makeText(context, "Quantidade de poligonos: "+ plotPoligons.size()+"", Toast.LENGTH_SHORT).show();

        center = mGoogleMap.addMarker(addPlotMarker(
                findPolygonCenter((ArrayList<LatLng>) shape.getPoints())));


        drawedTheLast = true;

        if (drawedTheLast) {

            Toast.makeText(context, "Talhão adicionado, clique em OK para salvar",
                    Toast.LENGTH_LONG).show();


            mGoogleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                @Override
                public void onMapClick(LatLng latLng) {
                    //Do nothing
                }
            });
        }
        drawedTheLast = false;
        drawedPerTime = 0;
    }

    private MarkerOptions addPlotMarker(LatLng latLng){

        MarkerOptions marker = new MarkerOptions()
                .draggable(true)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE))
                .position(new LatLng(latLng.latitude,latLng.longitude));

        return marker;
    }

    private LatLng findPolygonCenter(ArrayList<LatLng> points) {

        double latitude = 0.0;
        double longitude = 0.0;

        for (int i = 0; i < points.size(); i++) {
            latitude += points.get(i).latitude;
            longitude += points.get(i).longitude;
        }

        latitude = latitude/ points.size();
        longitude = longitude/ points.size();

        return new LatLng(latitude, longitude);
    }

}
