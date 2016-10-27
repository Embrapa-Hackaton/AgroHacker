package hackathon.embrapa.agrohacker.controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import hackathon.embrapa.agrohacker.R;
import hackathon.embrapa.agrohacker.model.Plot;

public class NotificationActivity extends AppCompatActivity {

    PlotController plotcontroller = new PlotController();

    ArrayList<String> listItems = new ArrayList<String>();

    Button updateButton;

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.activity_notification);

        TextView plotTv = (TextView) findViewById(R.id.plot_tv);
        TextView trapTv = (TextView) findViewById(R.id.trap_tv);
        TextView inspecTv = (TextView) findViewById(R.id.inspec_tv);

        int trapNumber = 0;
        int inspecNumber = 0;
        for(int i = 0; i < plotcontroller.plots.size(); i++){
            trapNumber+=plotcontroller.plots.get(i).getTraps().size();
            inspecNumber+=plotcontroller.plots.get(i).getFieldInspections().size();
        }

        Log.i("Quantidade: ",plotcontroller.plots+"");

        plotTv.setText(plotTv.getText()+""+plotcontroller.plots.size()+"");
        trapTv.setText("Armadilhas cadastradas: "+trapNumber+"");
        inspecTv.setText("Inspeções cadastradas: "+inspecNumber+"");

        ArrayList<Plot> plots = new ArrayList<>(plotcontroller.plots);
        ListView plotsLv = (ListView) findViewById(R.id.update_lv);
        ArrayAdapter<Plot> plotArrayAdapter = new ArrayAdapter<Plot>(this, android.R.layout.simple_list_item_1,
                plots);
        plotsLv.setAdapter(plotArrayAdapter);
    }

    public void generateReport(View view) {

        if(plotcontroller.plots.size() == 0){

            listItems.add("Você não possui nenhum talhão cadastrado");
        }
    }
}
