package hackathon.embrapa.agrohacker.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import hackathon.embrapa.agrohacker.model.Plot;

public class PlotDAO extends SQLiteOpenHelper{

    public PlotDAO(Context context) {
        super(context, "AgroHacker", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE Plot (id INTEGER PRIMARY KEY, plantationStage TEXT, plantationStartDate DATE," +
                "harvestDate DATE, platationCulture TEXT, status TEXT);";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXIST Plot;";
        db.execSQL(sql);
        onCreate(db);
    }

    private ContentValues getPlotData(Plot plot) {
        ContentValues data = new ContentValues();
        data.put("plantationStage", plot.getPlantationStage());
        data.put("plantationStartDate", plot.getPlantationStartDate().toString());
        data.put("harvestDate", plot.getHarvestDate().toString());
        data.put("platationCulture", plot.getPlatationCulture());
        data.put("status", plot.getStatus());
        return data;
    }

    public void insertPlot(Plot plot) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues data = getPlotData(plot);
        db.insert("Plot", null, data);
    }

    public List<Plot> showPlots() {
        String sql = "SELECT * FROM Plot;";
        SQLiteDatabase db = getReadableDatabase();
        Cursor line = db.rawQuery(sql, null);
        List<Plot> plots = new ArrayList<Plot>();
        while(line.moveToNext()) {
            Plot plot = new Plot();
            plot.setId((int) line.getLong(line.getColumnIndex("id")));
            plot.setPlantationStage(line.getString(line.getColumnIndex("plantationStage")));
            plot.setPlantationStartDate(Date.valueOf(line.getString(line.getColumnIndex("plantationStartDate"))));
            plot.setHarvestDate(Date.valueOf(line.getString(line.getColumnIndex("harvestDate"))));
            plot.setPlatationCulture(line.getString(line.getColumnIndex("platationCulture")));
            plot.setStatus(line.getString(line.getColumnIndex("status")));
            plots.add(plot);
        }
        line.close();
        return plots;
    }

    public void removePlot(Plot plot) {
        SQLiteDatabase db = getWritableDatabase();
        String[] params = {String.valueOf(plot.getId())};
        db.delete("Plot", "id = ?", params);
    }

    public void updatePlot(Plot plot) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues data = getPlotData(plot);
        String[] params = {String.valueOf(plot.getId())};
        db.update("Plot", data, "id = ?", params);
    }
}
