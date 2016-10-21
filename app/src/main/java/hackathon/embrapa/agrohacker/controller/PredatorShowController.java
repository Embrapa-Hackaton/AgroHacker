package hackathon.embrapa.agrohacker.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import hackathon.embrapa.agrohacker.R;
import hackathon.embrapa.agrohacker.helper.PragueShowHelper;
import hackathon.embrapa.agrohacker.helper.PredatorShowHelper;
import hackathon.embrapa.agrohacker.model.NaturalPredator;
import hackathon.embrapa.agrohacker.model.Prague;

public class PredatorShowController extends AppCompatActivity{

    private PredatorShowHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_predator_details);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        helper = new PredatorShowHelper(this);

        receivePragueData();
    }

    private void receivePragueData() {
        Intent intent = getIntent();
        NaturalPredator predator = (NaturalPredator) intent.getSerializableExtra("predatorKey");
        if (predator != null) {
            helper.fillForm(predator);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_back, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);
    }

}
