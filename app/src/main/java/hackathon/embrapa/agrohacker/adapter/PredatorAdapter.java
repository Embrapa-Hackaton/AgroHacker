package hackathon.embrapa.agrohacker.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import hackathon.embrapa.agrohacker.R;
import hackathon.embrapa.agrohacker.model.NaturalPredator;

public class PredatorAdapter extends BaseAdapter {

    private final List<NaturalPredator> predators;
    private final Context context;

    public PredatorAdapter(Context context, List<NaturalPredator> predators) {
        this.context = context;
        this.predators = predators;
    }

    @Override
    public int getCount() {
        return predators.size();
    }

    @Override
    public Object getItem(int position) {
        return predators.get(position);
    }

    @Override
    public long getItemId(int position) {
        return predators.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        NaturalPredator predator = predators.get(position);
        View view = convertView;

        LayoutInflater inflater = LayoutInflater.from(context);
        if(view == null){view = inflater.inflate(R.layout.activity_list_item, parent, false);}

        TextView popularNameField = (TextView) view.findViewById(R.id.item_name);
        popularNameField.setText(predator.getPopularName());

        TextView scientificNameField = (TextView) view.findViewById(R.id.item_scientific_name);
        scientificNameField.setText(predator.getScientificName());

        ImageView photoField = (ImageView) view.findViewById(R.id.item_photo);
        ImageLoading(predator, photoField);

        return view;
    }

    private void ImageLoading(NaturalPredator predator, ImageView photoField) {
        String photoPath = predator.getPhotoPath();
        if (photoPath != null) {
            Bitmap bitmap = BitmapFactory.decodeFile(photoPath);
            Bitmap bitmapReduzido = Bitmap.createScaledBitmap(bitmap, 300, 300, true);
            bitmapReduzido = roundCorner(bitmapReduzido, 10);
            photoField.setImageBitmap(bitmapReduzido);
            photoField.setScaleType(ImageView.ScaleType.FIT_XY);
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
