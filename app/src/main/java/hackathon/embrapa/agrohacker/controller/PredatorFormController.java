package hackathon.embrapa.agrohacker.controller;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;

import hackathon.embrapa.agrohacker.R;
import hackathon.embrapa.agrohacker.dao.NaturalPredatorDAO;
import hackathon.embrapa.agrohacker.helper.PredatorFormHelper;
import hackathon.embrapa.agrohacker.model.NaturalPredator;

public class PredatorFormController extends AppCompatActivity{

    public static final int CAMERA_CODE = 567;
    private PredatorFormHelper helper;
    private String photoPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_predator_form);

        helper = new PredatorFormHelper(this);

        receivePredatorData();

        takePredatorPhoto();
    }

    private void receivePredatorData() {
        Intent intent = getIntent();
        NaturalPredator predator = (NaturalPredator) intent.getSerializableExtra("predatorKey");
        if (predator != null) {
            helper.fillForm(predator);
        }
    }

    private void takePredatorPhoto() {
        Button buttonPhoto = (Button) findViewById(R.id.button_predator_form_photo);
        buttonPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                photoPath = getExternalFilesDir(null) + "/" + System.currentTimeMillis() + ".jpg";
                File photoFile = new File(photoPath);

                intentCamera.putExtra(MediaStore.EXTRA_OUTPUT, FileProvider.getUriForFile(PredatorFormController.this,
                        PredatorFormController.this.getApplicationContext().getPackageName() + ".provider", photoFile));

                startActivityForResult(intentCamera, CAMERA_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == Activity.RESULT_OK) {
            if (requestCode == CAMERA_CODE) {
                helper.imageLoading(photoPath);
            }
        }
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
                NaturalPredator predator = helper.getAllPredator();
                NaturalPredatorDAO dao = new NaturalPredatorDAO(this);

                if(predator.getId() != null) {
                    dao.updatePredator(predator);
                } else {
                    dao.insertPredator(predator);
                }

                dao.close();
                Toast.makeText(PredatorFormController.this, "Aluno " + predator.getPopularName() + " salvo!", Toast.LENGTH_SHORT).show();
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
