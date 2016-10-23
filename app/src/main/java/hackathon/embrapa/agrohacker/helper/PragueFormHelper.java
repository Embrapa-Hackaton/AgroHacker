package hackathon.embrapa.agrohacker.helper;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.widget.EditText;
import android.widget.ImageView;

import hackathon.embrapa.agrohacker.R;
import hackathon.embrapa.agrohacker.controller.PragueFormController;
import hackathon.embrapa.agrohacker.model.Prague;

public class PragueFormHelper {
    private final EditText popularNameField;
    private final EditText scientificNameField;
    private final EditText descriptionField;
    private final EditText bioecologyField;
    private final EditText damageField;
    private final EditText lifeCicleField;
    private final ImageView photoField;
    private Prague prague;

    public PragueFormHelper(PragueFormController activity) {
        popularNameField = (EditText) activity.findViewById(R.id.prague_popular_name_form);
        scientificNameField = (EditText) activity.findViewById(R.id.prague_scientific_name_form);
        descriptionField = (EditText) activity.findViewById(R.id.prague_description_form);
        bioecologyField = (EditText) activity.findViewById(R.id.prague_bioecology_form);
        damageField = (EditText) activity.findViewById(R.id.prague_damage_form);
        lifeCicleField = (EditText) activity.findViewById(R.id.prague_life_cycle_form);
        photoField = (ImageView) activity.findViewById(R.id.prague_form_photo);
        prague = new Prague();
    }

    public Prague getAllPrague() {
        prague.setPopularName(popularNameField.getText().toString());
        prague.setScientificName(scientificNameField.getText().toString());
        prague.setDescription(descriptionField.getText().toString());
        prague.setBioecology(bioecologyField.getText().toString());
        prague.setDamage(damageField.getText().toString());
        prague.setLifeCycleStage(lifeCicleField.getText().toString());
        prague.setPhotoPath((String) photoField.getTag());
        return prague;
    }

    public void fillForm(Prague prague) {
        popularNameField.setText(prague.getPopularName());
        scientificNameField.setText(prague.getScientificName());
        descriptionField.setText(prague.getDescription());
        bioecologyField.setText(prague.getBioecology());
        damageField.setText(prague.getDamage());
        lifeCicleField.setText(prague.getLifeCycleStage());
        imageLoading(prague.getPhotoPath());
        this.prague = prague;
    }

    public void imageLoading(String photoPath) {
        if (photoPath != null) {
            Bitmap bitmap = BitmapFactory.decodeFile(photoPath);
            Bitmap bitmapReduzido = Bitmap.createScaledBitmap(bitmap, 300, 300, true);
            bitmapReduzido = getRoundCornerBitmap(bitmapReduzido, 10);
            photoField.setImageBitmap(bitmapReduzido);
            photoField.setScaleType(ImageView.ScaleType.FIT_XY);
            photoField.setTag(photoPath);
        }
    }

    public static Bitmap getRoundCornerBitmap(Bitmap bitmap, int radius) {
        int w = bitmap.getWidth();
        int h = bitmap.getHeight();
        Bitmap output = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        final RectF rectF = new RectF(0, 0, w, h);

        canvas.drawRoundRect(rectF, radius, radius, paint);

        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, null, rectF, paint);

        final Rect clipRect = new Rect(0, 0, w, h - radius);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC));
        canvas.drawRect(clipRect, paint);

        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, null, rectF, paint);

        bitmap.recycle();

        return output;
    }


}
