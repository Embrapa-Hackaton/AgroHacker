package hackathon.embrapa.agrohacker.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;

import hackathon.embrapa.agrohacker.R;
import hackathon.embrapa.agrohacker.adapter.PredatorAdapter;
import hackathon.embrapa.agrohacker.dao.NaturalPredatorDAO;
import hackathon.embrapa.agrohacker.model.NaturalPredator;

public class PredatorListController extends AppCompatActivity {

    private ListView predatorList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_predator);

        toolbar();

        predatorList = (ListView) findViewById(R.id.predator_list);

        clickShowPredator();

        newPredatorButton();

        registerForContextMenu(predatorList);
    }

    private void toolbar() {
        Toolbar mToolbar = (Toolbar) findViewById(R.id.include_predator_toolbar_list);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle(R.string.predator_list);
        getSupportActionBar().setIcon(R.drawable.ic_app);
    }

    private void clickShowPredator() {
        predatorList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> lista, View item, int position, long id) {
                NaturalPredator predator = (NaturalPredator) predatorList.getItemAtPosition(position);
                Intent intentGoToForm = new Intent(PredatorListController.this, PredatorFormController.class); //mudar isso
                intentGoToForm.putExtra("predatorKey", predator);
                startActivity(intentGoToForm);
            }
        });
    }

    private void newPredatorButton() {
        Button newPredator = (Button) findViewById(R.id.new_predator_button);
        newPredator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentGoToForm = new Intent(PredatorListController.this, PredatorFormController.class);
                startActivity(intentGoToForm);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        LoadingList();
    }

    private void LoadingList() {
        NaturalPredatorDAO dao = new NaturalPredatorDAO(this);
        List<NaturalPredator> predators = dao.showPredators();
        dao.close();
        PredatorAdapter adapter = new PredatorAdapter(this, predators);
        predatorList.setAdapter(adapter);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, final ContextMenu.ContextMenuInfo menuInfo) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        final NaturalPredator predator = (NaturalPredator) predatorList.getItemAtPosition(info.position);

        menuDelete(menu, predator);
    }

    private void menuDelete(ContextMenu menu, final NaturalPredator predator) {
        MenuItem delete = menu.add("deletar");
        delete.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                NaturalPredatorDAO dao = new NaturalPredatorDAO(PredatorListController.this);
                dao.removePredator(predator);
                dao.close();
                LoadingList();
                return false;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_list, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);
    }

}
