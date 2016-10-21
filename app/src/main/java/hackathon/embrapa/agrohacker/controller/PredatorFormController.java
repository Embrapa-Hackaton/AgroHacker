package hackathon.embrapa.agrohacker.controller;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
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
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;

import hackathon.embrapa.agrohacker.R;
import hackathon.embrapa.agrohacker.dao.NaturalPredatorDAO;
import hackathon.embrapa.agrohacker.helper.PredatorFormHelper;
import hackathon.embrapa.agrohacker.model.NaturalPredator;

public class PredatorFormController extends AppCompatActivity{

    private static final int CAMERA_CODE = 567;
    private static final int SELECTED_PICTURE = 1;
    private PredatorFormHelper helper;
    private String photoPath;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_predator_form);

        toolbar();

        helper = new PredatorFormHelper(this);

        receivePredatorData();

        takePredatorPhoto();
    }

    private void toolbar() {
        Toolbar mToolbar = (Toolbar) findViewById(R.id.include_predator_toolbar_form);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle(R.string.predator_form);
        getSupportActionBar().setIcon(R.drawable.ic_app);
    }

    private void receivePredatorData() {
        Intent intent = getIntent();
        NaturalPredator predator = (NaturalPredator) intent.getSerializableExtra("predatorKey");
        if (predator != null) {
            helper.fillForm(predator);
        }
    }

    public void getImageClick(View view) {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, SELECTED_PICTURE);
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
        switch (requestCode) {
            case CAMERA_CODE:
                if(resultCode == Activity.RESULT_OK) {
                    helper.imageLoading(photoPath);
                }
                break;
            case SELECTED_PICTURE:
                if(resultCode == RESULT_OK) {
                    Uri uri = data.getData();
                    String[] projection = {MediaStore.Images.Media.DATA};
                    Cursor cursor = getContentResolver().query(uri, projection, null, null, null);
                    cursor.moveToFirst();
                    int columnIndex = cursor.getColumnIndex(projection[0]);
                    photoPath = cursor.getString(columnIndex);
                    cursor.close();
                    helper.imageLoading(photoPath);
                }
                break;
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
                Toast.makeText(PredatorFormController.this, "Predador " + predator.getPopularName() + " salvo!", Toast.LENGTH_SHORT).show();
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
