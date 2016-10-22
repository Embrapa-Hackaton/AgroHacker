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
import android.widget.Spinner;
import android.widget.TextView;

import hackathon.embrapa.agrohacker.R;
import hackathon.embrapa.agrohacker.controller.PragueShowController;
import hackathon.embrapa.agrohacker.model.Prague;

public class PragueShowHelper {
    private final TextView popularNameField;
    private final TextView scientificNameField;
    private final TextView descriptionField;
    private final TextView bioecologyField;
    private final TextView damageField;
    private final TextView lifeCicleField;
    private final ImageView photoField;
    private Prague prague;

    public PragueShowHelper(PragueShowController activity) {
        popularNameField = (TextView) activity.findViewById(R.id.item_show_prague_name);
        scientificNameField = (TextView) activity.findViewById(R.id.item_show_prague_scientific_name);
        descriptionField = (TextView) activity.findViewById(R.id.item_show_prague_description);
        bioecologyField = (TextView) activity.findViewById(R.id.item_show_prague_bioecology);
        damageField = (TextView) activity.findViewById(R.id.item_show_prague_damage);
        lifeCicleField = (TextView) activity.findViewById(R.id.item_show_prague_life_cycle);
        photoField = (ImageView) activity.findViewById(R.id.item_show_prague_foto);
        prague = new Prague();
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
            bitmapReduzido = roundCorner(bitmapReduzido, 10);
            photoField.setImageBitmap(bitmapReduzido);
            photoField.setScaleType(ImageView.ScaleType.FIT_XY);
            photoField.setTag(photoPath);
        }
    }

    public static Bitmap roundCorner(Bitmap src, float round) {
        // image size
        int width = src.getWidth();
        int height = src.getHeight();

        // create bitmap output
        Bitmap result = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);

        // set canvas for painting
        Canvas canvas = new Canvas(result);
        canvas.drawARGB(0, 0, 0, 0);

        // config paint
        final Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.BLACK);

        // config rectangle for embedding
        final Rect rect = new Rect(0, 0, width, height);
        final RectF rectF = new RectF(rect);

        // draw rect to canvas
        canvas.drawRoundRect(rectF, round, round, paint);

        // create Xfer mode
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        // draw source image to canvas
        canvas.drawBitmap(src, rect, rect, paint);

        // return final image
        return result;
    }


}
