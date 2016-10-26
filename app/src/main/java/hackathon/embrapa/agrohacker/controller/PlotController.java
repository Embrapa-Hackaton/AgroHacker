package hackathon.embrapa.agrohacker.controller;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
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
import java.util.List;

import hackathon.embrapa.agrohacker.model.Plot;
import hackathon.embrapa.agrohacker.model.Trap;

import static android.support.v4.app.ActivityCompat.startActivity;

public class PlotController {

    ArrayList<Plot> plots = new ArrayList<Plot>();

    private ArrayList<Marker> markers = new ArrayList<Marker>();
    private final static int POLYGON_MAX_NUMBERS = 4;
    public int drawedPerTime = 0;

    ArrayList<Polygon> plotPoligons = new ArrayList<Polygon>();
    Polygon shape;
    Marker center;

    boolean drawedTheLast = false;

    //Find Plot
    public Plot findPlotbyShape(Polygon shape) {
        int i;
        for (i = 0; i < plots.size(); i++) {
            Log.i("still", "SEARCHING");
            if (plots.get(i).getShape().equals(shape)) {

                Log.i("searching: ", "" + i);
                Log.i("Found the polygon", "contais index" + i);
                return plots.get(i);
            }
        }
        return null;
    }

    //Poligon Creation

    public void setPoligonMarker(LatLng latLng, GoogleMap mGoogleMap, Context context, View view) {

        MarkerOptions marker = new MarkerOptions()
                .draggable(true)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW) )
                .position(new LatLng(latLng.latitude,latLng.longitude));

        markers.add(mGoogleMap.addMarker(marker));


        if(markers.size() == POLYGON_MAX_NUMBERS){
            drawPoligon(mGoogleMap, context, view);
            markers.clear();
        }
    }

    private void drawPoligon(GoogleMap mGoogleMap, Context context, View mapView){

        PolygonOptions options = new PolygonOptions()
                .fillColor(0x660000FF)
                .strokeWidth(5)
                .clickable(true)
                .strokeColor(0x996D1B);


        for(int i = 0; i < POLYGON_MAX_NUMBERS; i++) {
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

            Toast.makeText(context, "Talhão adicionado",
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

        createPlot(shape, "Milho");


        Intent intent = new Intent();
        intent.setClass(mapView.getContext(), PlotFormActivity.class);
        mapView.getContext().startActivity(intent);
    }


      public void createPlot(Polygon polygon, String culture){

        Plot plot = new Plot(plots.size(), polygon, culture);
          center.setTitle("Talhão "+(plots.size()+1));
          center.setSnippet("Cultura: "+ culture+"\nPlantio: 02/34/1234\n"
                            +"Colheita: 02/34/1234\nStatus:"+plot.getStatus());
          Log.i("Quero v: ", center.getSnippet());
          plot.setPlotMarker(center);
        plots.add(plot);

        Log.i("Adicionou sá porra", "HUE");

        Log.i("tamanho", plots.size()+"");

    }

    public MarkerOptions addPlotMarker(LatLng latLng){

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

    public Plot checkIfPositionIsInsideAPlot(LatLng latLng){

        for(int i = 0; i < plotPoligons.size(); i++){
            if(checkPointIsInsideAPlot((ArrayList<LatLng>) plotPoligons.get(i).getPoints(), latLng))
               return findPlotbyShape(plotPoligons.get(i));
        }

        return null;

    }


    public boolean checkPointIsInsideAPlot(ArrayList<LatLng> vertices, LatLng latlng){
        int intersectCount = 0;
        for (int j = 0; j < vertices.size() - 1; j++) {
            if (checkCastIntersect(latlng, vertices.get(j), vertices.get(j + 1))) {
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

    public void setTrapOnPlot(Trap newTrap) {

        for(int i = 0; i < plotPoligons.size(); i++){
            //Find polygon that contais the trap
            if(checkPointIsInsideAPlot((ArrayList<LatLng>) plotPoligons.get(i).getPoints(),
                                newTrap.getTrapMarker().getPosition())){
                //Find plot that has that polygons and add trap
                findPlotbyShape(plotPoligons.get(i)).setTrap(newTrap);
            }
        }
    }

}
