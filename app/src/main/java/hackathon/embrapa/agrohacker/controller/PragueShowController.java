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
import hackathon.embrapa.agrohacker.model.Prague;

public class PragueShowController extends AppCompatActivity{

    private PragueShowHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prague_details);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        helper = new PragueShowHelper(this);

        receivePragueData();
    }

    private void receivePragueData() {
        Intent intent = getIntent();
        Prague prague = (Prague) intent.getSerializableExtra("pragueKey");
        if (prague != null) {
            helper.fillForm(prague);
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
