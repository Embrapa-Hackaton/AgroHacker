package hackathon.embrapa.agrohacker.controller;


import android.app.DatePickerDialog;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import hackathon.embrapa.agrohacker.R;
import hackathon.embrapa.agrohacker.dao.PlotDAO;

public class PlotFormActivity extends AppCompatActivity {

    private Spinner spinner;
    private ArrayAdapter<CharSequence> adapter;
    private EditText plantationDate;
    private EditText harvestDate;
    PlotController plotController = new PlotController();
    PlotDAO plotDAO = new PlotDAO(this);
    Button confirmButton;
    Button cancelButton;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plot_form);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setUpSpinner();

        plantationDate = (EditText) findViewById(R.id.plot_planting_date);
        harvestDate = (EditText) findViewById(R.id.plot_harvesting_date);

        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    private void setUpSpinner() {
        spinner = (Spinner) findViewById(R.id.plot_culture_spinner);
        adapter = ArrayAdapter.createFromResource(PlotFormActivity.this, R.array.plot_culture_array,
                android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    public void confirmAddingPlot() {

        DateFormat dateFormat = new SimpleDateFormat("dd/mm/yyyy");

        Date plantationD = null;
        Date harvestD = null;

        try {
            plantationD = dateFormat.parse(plantationDate.getText().toString());
        } catch (java.text.ParseException e) {
            e.printStackTrace();
            Toast.makeText(PlotFormActivity.this, "Impossivel converter data Plantio",
                    Toast.LENGTH_LONG).show();
        }
        try {
            harvestD = dateFormat.parse(plantationDate.getText().toString());
        } catch (Exception exception) {
            Toast.makeText(PlotFormActivity.this, "Impossivel converter data Colheita",
                    Toast.LENGTH_LONG).show();
        }
        if (plantationD != null || harvestD != null) {

            // plotController.addPlot("milho", plantationD, harvestD);
//            Plot plot = new Plot();
//            plot.setPlantationStartDate(plantationDate.getText().toString());
//            plot.setHarvestDate(harvestDate.getText().toString());
//            plot.setPlatationCulture(spinner.getSelectedItem().toString());

            getIntent().putExtra("plantationDate", plantationDate.getText().toString());
            getIntent().putExtra("harvestDate", harvestDate.getText().toString());
            getIntent().putExtra("culture", spinner.getSelectedItem().toString());
            finish();
        }


//        plotDAO.insertPlot(plot);
    }

    private void setUpEditTextPlantation() {
        final Calendar myCalendar = Calendar.getInstance();

        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel(myCalendar);
            }

        };

        plantationDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                new DatePickerDialog(PlotFormActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }

    private void updateLabel(Calendar myCalendar) {

        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        plantationDate.setText(sdf.format(myCalendar.getTime()));
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("PlotForm Page") // TODO: Define a title for the content shown.
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

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_formulary, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_formulary_ok:
                confirmAddingPlot();
                break;
            default:
                finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
