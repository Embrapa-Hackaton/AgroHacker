package hackathon.embrapa.agrohacker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class MapActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_map, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_map_inspect:
                Intent intentGoToInspectForm = new Intent(MapActivity.this, FormularyActivity.class);
                startActivity(intentGoToInspectForm);
                break;
            case R.id.menu_map_talhao:
                break;
            case R.id.menu_map_trap:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
