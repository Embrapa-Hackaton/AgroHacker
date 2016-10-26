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

public class PlotDAO extends DAO {

    public PlotDAO(Context context) {
        super(context);
    }

    private ContentValues getPlotData(Plot plot) {
        ContentValues data = new ContentValues();
        data.put("plantationStage", plot.getPlantationStage());
        data.put("plantationStartDate", plot.getPlantationStartDate());
        data.put("harvestDate", plot.getHarvestDate());
        data.put("platationCulture", plot.getPlatationCulture());
        data.put("status", plot.getStatus());
        data.put("latitude1", plot.getLat1());
        data.put("longitude1", plot.getLon1());
        data.put("latitude2", plot.getLat2());
        data.put("longitude2", plot.getLon2());
        data.put("latitude3", plot.getLat3());
        data.put("longitude3", plot.getLon3());
        data.put("latitude4", plot.getLat4());
        data.put("longitude4", plot.getLon4());
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
            plot.setPlantationStartDate(line.getString(line.getColumnIndex("plantationStartDate")));
            plot.setHarvestDate(line.getString(line.getColumnIndex("harvestDate")));
            plot.setPlatationCulture(line.getString(line.getColumnIndex("platationCulture")));
            plot.setStatus(line.getString(line.getColumnIndex("status")));
            plot.setLat1(line.getDouble(line.getColumnIndex("latitude1")));
            plot.setLon1(line.getDouble(line.getColumnIndex("longitude1")));
            plot.setLat2(line.getDouble(line.getColumnIndex("latitude2")));
            plot.setLon2(line.getDouble(line.getColumnIndex("longitude2")));
            plot.setLat3(line.getDouble(line.getColumnIndex("latitude3")));
            plot.setLon3(line.getDouble(line.getColumnIndex("longitude3")));
            plot.setLat4(line.getDouble(line.getColumnIndex("latitude4")));
            plot.setLon4(line.getDouble(line.getColumnIndex("longitude4")));
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
