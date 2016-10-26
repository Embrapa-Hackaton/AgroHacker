package hackathon.embrapa.agrohacker.controller;


import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.location.Location;
import android.net.Uri;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
//import android.support.v4.view.GravityCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.view.MenuItem;
import android.widget.TextView;
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
import com.google.android.gms.maps.model.PolygonOptions;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import hackathon.embrapa.agrohacker.dao.PlotDAO;
import hackathon.embrapa.agrohacker.model.Plot;

import static android.R.attr.shape;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleApiClient.OnConnectionFailedListener, GoogleApiClient.ConnectionCallbacks, LocationListener, NavigationView.OnNavigationItemSelectedListener {

    GoogleMap mGoogleMap;
    GoogleApiClient googleApiClient;
    private GoogleApiClient client;
    PlotController plotController = new PlotController();
    TrapController trapController = new TrapController();
    FieldInspectionController fieldInspectionController = new FieldInspectionController();
    ActionBarDrawerToggle toggle;
    Marker userLocationMarker;
    Plot plot = new Plot();
    Button endingAdding;
    LocationRequest locationRequest;
    ArrayList<LatLng> points = new ArrayList<LatLng>();
    PlotDAO plotDAO = new PlotDAO(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_screen);

        toolbar();
        bottomToolbar();

        menuDrawer();

        if (googleServicesAvailabe()) {
            Toast.makeText(this, "Connecting application", Toast.LENGTH_LONG).show();
            initializeMap();
        } else {
            //Impossible to display maps
        }

        endingAdding = (Button) findViewById(R.id.concludebutton);

        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    private void menuDrawer() {
        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void toolbar() {
        Toolbar mToolbar = (Toolbar) findViewById(R.id.include_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle(R.string.app_name);
        getSupportActionBar().setSubtitle(R.string.app_subtitle);
        getSupportActionBar().setIcon(R.drawable.ic_app);
    }

    private void bottomToolbar() {
        Toolbar mToolbarBottom = (Toolbar) findViewById(R.id.include_toolbar_bottom);
        mToolbarBottom.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                Intent intent = null;

                switch (menuItem.getItemId()) {
                    case R.id.menu_map_fieldInspec:
                            createFieldInspection();
                        break;
                    case R.id.menu_map_talhao:
                        Toast.makeText(MapActivity.this, "Selecione 4 pontos no mapa",
                                Toast.LENGTH_LONG).show();
                        createPlot();
                        break;
                    case R.id.menu_map_trap:
                        createTrap();
                        break;
                }

                //startActivity(intent);
                return true;
            }
        });
        mToolbarBottom.inflateMenu(R.menu.menu_bottom);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(toggle.onOptionsItemSelected(item)) return true;
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_prague:
                Intent intentGoToPragueList = new Intent(MapActivity.this, PragueListController.class);
                startActivity(intentGoToPragueList);
                break;
            case R.id.menu_predator:
                Intent intentGoToPredatorList = new Intent(MapActivity.this, PredatorListController.class);
                startActivity(intentGoToPredatorList);
                break;
            case R.id.menu_report:
                Intent intentReportActivity = new Intent(MapActivity.this, NotificationsActivity.class);
                startActivity(intentReportActivity);
                Toast.makeText(MapActivity.this, "Em construção", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_info:
                Toast.makeText(MapActivity.this, "Em construção", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_about:
                Intent intentAboutActivity = new Intent(MapActivity.this, AboutActivity.class);
                startActivity(intentAboutActivity);
                break;
            case R.id.menu_exit:
                finish();
                break;
        }

        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        if(drawerLayout.isDrawerOpen(GravityCompat.START)) drawerLayout.closeDrawer(GravityCompat.START);

        return false;
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
        mGoogleMap.clear();

        plotController.plots.clear();

        drawPlots();

        setInfoWindows();

        permitClickOnPolygon();

        googleApiClient = new GoogleApiClient.Builder(this)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();

        googleApiClient.connect();

    }

    private void setInfoWindows() {

            mGoogleMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {
            @Override
            public View getInfoWindow(Marker marker) {
                return null;
            }

            @Override
            public View getInfoContents(Marker marker) {

                if(marker.getTitle().contains("Tal") ||
                        marker.getTitle().contains("Armadilha") ||
                                marker.getTitle().contains("Inspeção")){

                    View view = getLayoutInflater().inflate(R.layout.plot_info_window, null);

                    TextView tvTalhao = (TextView) view.findViewById(R.id.tv_index);
                    TextView tvInfo = (TextView) view.findViewById(R.id.tv_culture);

                    LatLng latLng = marker.getPosition();

                    tvTalhao.setText(marker.getTitle());

                    tvInfo.setText(marker.getSnippet());

                    return view;
                }

                return null;
            }
        });
    }

    public void createPlot() {

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
                if(plotController.drawedPerTime == 0){
                    View mapView = findViewById(R.id.activity_map);

                    plotController.setPoligonMarker(latLng, mGoogleMap, MapActivity.this, mapView);
                }
            }
        });
    }

    public void endAddingPlots(View mapView){

        findViewById(R.id.menu_map_talhao).setVisibility(View.VISIBLE);

        endingAdding.setVisibility(View.INVISIBLE);

        permitClickOnPolygon();


    //    Intent intent = new Intent(MapActivity.this, PlotFormActivity.class);
    //    startActivity(intent);

        mGoogleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                //Do nothing
            }
        });

        Intent intent = getIntent();
        ArrayList<Plot> plots = plotController.returnPlots();
        plot = plots.get(plots.size() - 1);
        plot.setPlantationStartDate(intent.getStringExtra("platationDate"));
        plot.setPlantationStartDate(intent.getStringExtra("harvestDate"));
        plot.setPlantationStartDate(intent.getStringExtra("culture"));

        plotDAO.insertPlot(plot);
    }

    public void createTrap() {

        endingAdding.setVisibility(View.VISIBLE);

        try {
            points = (ArrayList<LatLng>) plot.getShape().getPoints();
            plot.getShape().setClickable(false);

            mGoogleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                @Override
                public void onMapClick(LatLng latLng) {
                    if (trapController.checkTrapIsInsidePlot(latLng, points)) {
                        trapController.addTrapOnMap(mGoogleMap, latLng, findViewById(R.id.activity_map));
                        permitClickOnPolygon();
                    } else {
                        Toast.makeText(MapActivity.this, "Ponto fora do talhão",
                                Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }catch(NullPointerException exception){
            exception.printStackTrace();
            Toast.makeText(MapActivity.this, "Selecione um talhão para adicinar sua armadilha",
                                Toast.LENGTH_SHORT).show();
        }
    }

    public void createFieldInspection(){

        plot = plotController.checkIfPositionIsInsideAPlot(userLocationMarker.getPosition());

        if(plot!=null){
            Toast.makeText(MapActivity.this, "Uma nova inspeção será adicionada na sua localização",
                    Toast.LENGTH_LONG).show();

            View mapView = findViewById(R.id.activity_map);

            fieldInspectionController.addFieldInspection(mGoogleMap, plot, userLocationMarker.getPosition(),mapView);
        }else
            Toast.makeText(MapActivity.this, "Você não está dentro de nenhum talhão",
                    Toast.LENGTH_LONG).show();
    }

    //UserLocation

    @Override
    public void onConnected(Bundle bundle) {
        locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(50000);

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


    public void permitClickOnPolygon(){

        mGoogleMap.setOnPolygonClickListener(new GoogleMap.OnPolygonClickListener() {

            @Override
            public void onPolygonClick(Polygon polygon) {

                Plot localPlot = new Plot();

                endingAdding.setVisibility(View.VISIBLE);

                findViewById(R.id.menu_map_talhao).setVisibility(View.INVISIBLE);

                localPlot = plotController.findPlotbyShape(polygon);

                Log.i("Found plot", localPlot.getId() + "");

                CameraUpdate update = CameraUpdateFactory.newLatLngZoom(localPlot.getPlotMarker().getPosition(), 17);
                mGoogleMap.animateCamera(update);

                plot = localPlot;
            }
        });
    }


    // SELF GENERATED METHODS


    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }

    @Override
    public void onConnectionSuspended(int i) {

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
    public void onResume() {
        super.onResume();

//        try{
//            List<Plot> plots= plotDAO.showPlots();
//            if(!plots.isEmpty()){
//                for (int i = 0; i < plots.size(); i++) {
//                    Plot plot = plots.get(i);
//                    Polygon polygon = mGoogleMap.addPolygon(new PolygonOptions()
//                            .add(new LatLng(plot.getLat1(), plot.getLon1()),
//                                    new LatLng(plot.getLat2(), plot.getLon2()),
//                                    new LatLng(plot.getLat3(), plot.getLon3()),
//                                    new LatLng(plot.getLat4(), plot.getLon4()))
//                            .strokeColor(0x996D1B)
//                            .fillColor(0x660000FF));
//
//                    plotController.createPlot(polygon, plot.getPlatationCulture());
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

//        drawPlots();
    }

    private void drawPlots() {
        List<Plot> plots = plotDAO.showPlots();
        Log.i("numero de plots", "tem " + plots.size() + " plots");
        try {
            if (!plots.isEmpty()) {
                for (int i = 0; i < plots.size(); i++) {
                    Plot plot;
                    plot = plots.get(i);

                    PolygonOptions polygon = new PolygonOptions();
                    polygon.fillColor(0x660000FF)
                            .strokeWidth(5)
                            .clickable(true)
                            .strokeColor(0x996D1B);
                    polygon.add(new LatLng(plot.getLat1(), plot.getLon1()));
                    polygon.add(new LatLng(plot.getLat2(), plot.getLon2()));
                    polygon.add(new LatLng(plot.getLat3(), plot.getLon3()));
                    polygon.add(new LatLng(plot.getLat4(), plot.getLon4()));

                    plot.setShape(mGoogleMap.addPolygon(polygon));
                    plot.setPlotMarker(mGoogleMap.addMarker(plotController.addPlotMarker(
                        plotController.findPolygonCenter((ArrayList<LatLng>) plot.getShape().getPoints()))));

                    plot.getPlotMarker().setTitle("Talhao");

                    plotController.plotPoligons.add(plot.getShape());
                    plotController.addPlot(plot);
                }
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onStop() {
        super.onStop();

        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }

}
