package hackathon.embrapa.agrohacker.controller;


import android.Manifest;
import android.app.Dialog;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import hackathon.embrapa.agrohacker.R;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;

import java.util.ArrayList;
import java.util.List;

import hackathon.embrapa.agrohacker.model.Plot;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleApiClient.OnConnectionFailedListener, GoogleApiClient.ConnectionCallbacks, LocationListener {

    GoogleMap mGoogleMap;
    GoogleApiClient googleApiClient;
    private GoogleApiClient client;
    PlotController plotController = new PlotController();
    TrapController trapController = new TrapController();
    Marker userLocationMarker;
    Plot plot = new Plot();
    Button createPlot;
    Button endingAdding;
    Button createTrap;
    Button createFieldInspection;
    LocationRequest locationRequest;
    ArrayList<LatLng> points = new ArrayList<LatLng>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.map_screen);
        if (googleServicesAvailabe()) {
            Toast.makeText(this, "Connecting application", Toast.LENGTH_LONG).show();
            initializeMap();
        } else {
            //Impossible to display maps
        }

        createPlot = (Button) findViewById(R.id.createPlot);
        endingAdding = (Button) findViewById(R.id.concludebutton);
        createTrap = (Button) findViewById(R.id.trapBt);
        createFieldInspection = (Button) findViewById(R.id.Inspectionbt);

        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    //Checking services

    public boolean googleServicesAvailabe() {
        GoogleApiAvailability apiAvailability = GoogleApiAvailability.getInstance();
        int availability = apiAvailability.isGooglePlayServicesAvailable(this);

        if (availability == ConnectionResult.SUCCESS)
            return true;
        else if (apiAvailability.isUserResolvableError(availability)) {
            Dialog dialog = apiAvailability.getErrorDialog(this, availability, 0);
            dialog.show();
        } else
            Toast.makeText(this, "Impossible to connect to play services", Toast.LENGTH_LONG).show();
        return false;
    }

    //Maps Methods


    private void initializeMap() {
        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.mapfragment);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mGoogleMap = googleMap;
        mGoogleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);

        permitClickOnPolygon();

        googleApiClient = new GoogleApiClient.Builder(this)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();

        googleApiClient.connect();

        plotController.initialize3Plots(mGoogleMap);
    }

    public void createPlot(View mapView) {

        createPlot.setVisibility(View.INVISIBLE);
        endingAdding.setVisibility(View.VISIBLE);

        mGoogleMap.setOnPolygonClickListener(new GoogleMap.OnPolygonClickListener() {

            @Override
            public void onPolygonClick(Polygon polygon) {
                //Do nothing
                Toast.makeText(MapActivity.this,
                        "Você não pode adicionar um ponto dentro de outro talhão",
                        Toast.LENGTH_LONG).show();
            }
        });

        mGoogleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {

            @Override
            public void onMapClick(LatLng latLng) {
                if(plotController.drawedPerTime == 0)
                    plotController.setPoligonMarker(latLng, mGoogleMap, MapActivity.this);
            }
        });
    }

    public void endAddingPlots(View mapView){

        createPlot.setVisibility(View.VISIBLE);
        endingAdding.setVisibility(View.INVISIBLE);

        permitClickOnPolygon();

        mGoogleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                //Do nothing
            }
        });

    }

    public void createTrap(View mapView) {

        points = (ArrayList<LatLng>) plot.getShape().getPoints();
        plot.getShape().setClickable(false);

        mGoogleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                if(trapController.checkTrapIsInsidePlot(latLng, points)){
                    Toast.makeText(MapActivity.this, "Iremos add sá merda",
                            Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MapActivity.this, "Ponto fora do poligono",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void createFieldInspection(View mapView){
        Toast.makeText(this, "Não implementado", Toast.LENGTH_LONG).show();
    }

    //UserLocation

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }

    @Override
    public void onConnected(Bundle bundle) {
        locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(20000);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        LocationServices.FusedLocationApi.requestLocationUpdates(googleApiClient, locationRequest, this);
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onLocationChanged(Location location) {
        if (location == null) {
            Toast.makeText(this, "Impossível pegar localização atual", Toast.LENGTH_LONG).show();
        } else {
            LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
            CameraUpdate update =  CameraUpdateFactory.newLatLngZoom(latLng, 15);
            mGoogleMap.animateCamera(update);

            setUserPositionMarker(latLng);
        }
    }

    private void setUserPositionMarker(LatLng latLng) {
        if(userLocationMarker != null){
            userLocationMarker.remove();
        }

        MarkerOptions marker = new MarkerOptions()
                .title("Sua localização")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE))
                .position(new LatLng(latLng.latitude,latLng.longitude));
        userLocationMarker  =  mGoogleMap.addMarker(marker);
    }

    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Main Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }

    public void permitClickOnPolygon(){

        mGoogleMap.setOnPolygonClickListener(new GoogleMap.OnPolygonClickListener() {


            @Override
            public void onPolygonClick(Polygon polygon) {

                createPlot.setVisibility(View.INVISIBLE);
                createFieldInspection.setVisibility(View.VISIBLE);
                createTrap.setVisibility(View.VISIBLE);

                plot.setShape(polygon);
                plot = plotController.findPlotbyShape(polygon);

                Log.i("Found plot", plot.getIndex() + "");

                CameraUpdate update = CameraUpdateFactory.newLatLngZoom(plot.getShape().getPoints().get(0), 15);
                mGoogleMap.animateCamera(update);

                mGoogleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                    @Override
                    public void onMapClick(LatLng latLng) {
                        if (trapController.checkTrapIsInsidePlot(latLng,
                                (ArrayList<LatLng>) plot.getShape().getPoints())) {

                            trapController.addTrap(mGoogleMap, latLng, plot);
                        } else {

                            Toast.makeText(MapActivity.this,
                                    "Esse ponto não está dentro do talhão escolhido",
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });
    }

}
