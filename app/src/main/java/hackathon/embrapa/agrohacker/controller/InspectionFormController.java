package hackathon.embrapa.agrohacker.controller;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import hackathon.embrapa.agrohacker.R;

public class InspectionFormController extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inspection_form);
        toolbar();
    }

    private void toolbar() {
        Toolbar mToolbar = (Toolbar) findViewById(R.id.include_inspection_toolbar_form);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle(R.string.inspection_form);
        getSupportActionBar().setIcon(R.drawable.ic_app);
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
                Toast.makeText(InspectionFormController.this, "Botão clicado!", Toast.LENGTH_SHORT).show();
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
