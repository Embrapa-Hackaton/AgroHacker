package hackathon.embrapa.agrohacker.controller;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import java.security.spec.ECField;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import hackathon.embrapa.agrohacker.R;
import hackathon.embrapa.agrohacker.dao.TrapDAO;
import hackathon.embrapa.agrohacker.model.Trap;

public class TrapFormController extends AppCompatActivity {


    private Spinner spinner;
    private ArrayAdapter<CharSequence> adapter;
    EditText lastChange;
    EditText duration;
    TrapController trapController = new TrapController();
    TrapDAO trapDAO = new TrapDAO(this);

    private GoogleApiClient client;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trap_form);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setUpSpinner();

        lastChange = (EditText) findViewById(R.id.trap_last_change);
        duration = (EditText) findViewById(R.id.duration_edit_Text);

        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    private void setUpSpinner() {
        spinner = (Spinner) findViewById(R.id.plot_culture_spinner);
        adapter = ArrayAdapter.createFromResource(TrapFormController.this, R.array.plot_culture_array,
                android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    public void confirmAddingTrap(){

        Log.i("Entering here", "I hope");

        DateFormat dateFormat = new SimpleDateFormat("dd/mm/yyyy");

        String lastChangeDate = null;
        int durationI = 0;

        try{
            durationI = Integer.parseInt(duration.getText().toString());
        }catch(Exception exception){
            exception.printStackTrace();
            Toast.makeText(TrapFormController.this, "Numero de dias informado inváilido",
                    Toast.LENGTH_LONG).show();
        }

        try {
            lastChangeDate = lastChange.getText().toString();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(TrapFormController.this, "Impossivel converter data de mudança",
                    Toast.LENGTH_LONG).show();
        }

        if (lastChangeDate!= null || (durationI != 0)) {
            Log.i("Obtaining correct info","trap form controller");
            Log.i("Date: "+lastChangeDate.toString(),"");
            Log.i("Duration: ", durationI+"");
            trapController.createTrap("hahsh", durationI, lastChangeDate, this);

            Trap trap = new Trap();
            trap.setDuration(Integer.valueOf(duration.getText().toString()));
            trap.setLastChange(lastChange.getText().toString());
            trap.setPheromone(spinner.getSelectedItem().toString());

            Intent intent = getIntent();
            double doubles[] = intent.getDoubleArrayExtra("points");

            trap.setLatitude(doubles[0]);
            trap.setLongitude(doubles[1]);

            trapDAO.insertTrap(trap);

            finish();
        }
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("TrapFormController Page") // TODO: Define a title for the content shown.
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
                confirmAddingTrap();
                break;
            default:
                finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
