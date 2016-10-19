package hackathon.embrapa.agrohacker.controller;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import hackathon.embrapa.agrohacker.R;
import hackathon.embrapa.agrohacker.model.Plot;

public class PlotFormActivity extends AppCompatActivity {

    private Spinner spinner;
    private ArrayAdapter<CharSequence> adapter;
    private EditText plantationDate;
    private EditText harvestDate;
    PlotController plotController = new PlotController();
    Button confirmButton;
    Button cancelButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plot_form);

        setUpSpinner();

        plantationDate = (EditText) findViewById(R.id.plot_planting_date);
        harvestDate = (EditText) findViewById(R.id.plot_harvesting_date);

        confirmButton = (Button) findViewById(R.id.confirmButton);
        cancelButton = (Button) findViewById(R.id.cancelbutton);
    }

    private void setUpSpinner() {
        spinner = (Spinner) findViewById(R.id.plot_culture_spinner);
        adapter = ArrayAdapter.createFromResource(PlotFormActivity.this, R.array.plot_culture_array,
                android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    public void confirmAddingPlot(View view){
        Plot plot = new Plot();
        plot.setId(plotController.drawedPoligons+1);
        plot.setPlatationCulture("Milho");

        DateFormat dateFormat = new SimpleDateFormat("dd/mm/yyyy");

        Date plantationD;
        try {
            plantationD = dateFormat.parse(plantationDate.getText().toString());
            plot.setPlantationStartDate(plantationD);
        }catch(ParseException parseException){
            Toast.makeText(PlotFormActivity.this, "Impossivel converter data Plantio",
                    Toast.LENGTH_LONG).show();
        }

        Date harvestD;
        try {
            harvestD = dateFormat.parse(plantationDate.getText().toString());
            plot.setHarvestDate(harvestD);
        }catch(ParseException parseException){
            Toast.makeText(PlotFormActivity.this, "Impossivel converter data Colheita",
                    Toast.LENGTH_LONG).show();
        }

        plotController.addPlot(plot);
        Toast.makeText(this, "Talhão adicionado com sucesso!", Toast.LENGTH_SHORT).show();
        finish();
    }

    public void cancelAddingPlot(){
        plotController.center.remove();
        plotController.shape.remove();

        //Remove Last from map polygon
        plotController.mapPoligons.remove(plotController.mapPoligons.size()-1);
        plotController.markers.clear();
        Toast.makeText(this, "adicão cancelada com sucesso!", Toast.LENGTH_SHORT).show();
        finish();

    }

    private void setUpEditTextPlantation(){
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

}
