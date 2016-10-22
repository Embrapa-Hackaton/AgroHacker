package hackathon.embrapa.agrohacker.controller;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.File;

import hackathon.embrapa.agrohacker.R;
import hackathon.embrapa.agrohacker.dao.PragueDAO;
import hackathon.embrapa.agrohacker.helper.PragueFormHelper;
import hackathon.embrapa.agrohacker.model.Prague;

public class PragueFormController extends AppCompatActivity{

    private static final int CAMERA_CODE = 567;
    private static final int SELECTED_PICTURE = 1;
    private PragueFormHelper helper;
    private String photoPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prague_form);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        helper = new PragueFormHelper(this);

        receivePragueData();

        takePraguePhoto();
    }

    private void receivePragueData() {
        Intent intent = getIntent();
        Prague prague = (Prague) intent.getSerializableExtra("pragueKey");
        if (prague != null) {
            helper.fillForm(prague);
        }
    }

    public void getImageClick(View view) {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, SELECTED_PICTURE);
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
                Prague prague = helper.getAllPrague();
                PragueDAO dao = new PragueDAO(this);

                if(prague.getId() != null) {
                    dao.updatePrague(prague);
                } else {
                    dao.insertPrague(prague);
                }

                dao.close();
                Toast.makeText(PragueFormController.this, "Praga " + prague.getPopularName() + " salvo!", Toast.LENGTH_SHORT).show();
                finish();
                break;
            default:
                finish();
        }
        return super.onOptionsItemSelected(item);
    }

}
