package hackathon.embrapa.agrohacker.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;

import hackathon.embrapa.agrohacker.R;
import hackathon.embrapa.agrohacker.adapter.PragueAdapter;
import hackathon.embrapa.agrohacker.dao.DAO;
import hackathon.embrapa.agrohacker.dao.PragueDAO;
import hackathon.embrapa.agrohacker.model.Prague;

public class PragueListController extends AppCompatActivity {

    private ListView pragueList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_prague);

        pragueList = (ListView) findViewById(R.id.prague_list);

        clickShowPrague();

        newPragueButton();

        registerForContextMenu(pragueList);
    }

    private void clickShowPrague() {
        pragueList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> lista, View item, int position, long id) {
                Prague prague = (Prague) pragueList.getItemAtPosition(position);
                Intent intentGoToForm = new Intent(PragueListController.this, PragueFormController.class); //mudar isso
                intentGoToForm.putExtra("pragueKey", prague);
                startActivity(intentGoToForm);
            }
        });
    }

    private void newPragueButton() {
        Button newPrague = (Button) findViewById(R.id.new_prague_button);
        newPrague.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentGoToForm = new Intent(PragueListController.this, PragueFormController.class);
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
        PragueDAO dao = new PragueDAO(this);
        List<Prague> pragues = dao.showPragues();
        dao.close();
        PragueAdapter adapter = new PragueAdapter(this, pragues);
        pragueList.setAdapter(adapter);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, final ContextMenu.ContextMenuInfo menuInfo) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        final Prague prague = (Prague) pragueList.getItemAtPosition(info.position);

        menuDelete(menu, prague);
    }

    private void menuDelete(ContextMenu menu, final Prague prague) {
        MenuItem delete = menu.add("deletar");
        delete.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                PragueDAO dao = new PragueDAO(PragueListController.this);
                dao.removePrague(prague);
                dao.close();
                LoadingList();
                return false;
            }
        });
    }

}
