package hackathon.embrapa.agrohacker.controller;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;

import hackathon.embrapa.agrohacker.R;
import hackathon.embrapa.agrohacker.dao.PragueDAO;
import hackathon.embrapa.agrohacker.helper.PragueFormHelper;
import hackathon.embrapa.agrohacker.model.Prague;

public class PragueFormController extends AppCompatActivity{

    public static final int CAMERA_CODE = 567;
    private PragueFormHelper helper;
    private String photoPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prague_form);

        toolbar();

        helper = new PragueFormHelper(this);

        receivePragueData();

        takePraguePhoto();
    }

    private void toolbar() {
        Toolbar mToolbar = (Toolbar) findViewById(R.id.include_prague_toolbar_form);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle(R.string.prague_form);
        getSupportActionBar().setIcon(R.drawable.ic_app);
    }

    private void receivePragueData() {
        Intent intent = getIntent();
        Prague prague = (Prague) intent.getSerializableExtra("pragueKey");
        if (prague != null) {
            helper.fillForm(prague);
        }
    }

    private void takePraguePhoto() {
        Button buttonPhoto = (Button) findViewById(R.id.button_prague_form_photo);
        buttonPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                photoPath = getExternalFilesDir(null) + "/" + System.currentTimeMillis() + ".jpg";
                File photoFile = new File(photoPath);

                intentCamera.putExtra(MediaStore.EXTRA_OUTPUT, FileProvider.getUriForFile(PragueFormController.this,
                        PragueFormController.this.getApplicationContext().getPackageName() + ".provider", photoFile));

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
                Prague prague = helper.getAllPrague();
                PragueDAO dao = new PragueDAO(this);

                if(prague.getId() != null) {
                    dao.updatePrague(prague);
                } else {
                    dao.insertPrague(prague);
                }

                dao.close();
                Toast.makeText(PragueFormController.this, "Aluno " + prague.getPopularName() + " salvo!", Toast.LENGTH_SHORT).show();
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
