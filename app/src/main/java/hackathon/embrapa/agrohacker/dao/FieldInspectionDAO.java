package hackathon.embrapa.agrohacker.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import hackathon.embrapa.agrohacker.model.FieldInspection;
import hackathon.embrapa.agrohacker.model.Plot;

public class FieldInspectionDAO extends DAO {

    public FieldInspectionDAO(Context context) {
        super(context);
    }

    private ContentValues getInspectionData(FieldInspection inspection) {
        ContentValues data = new ContentValues();
        data.put("realization", inspection.getRealization().toString());
        data.put("inspectorName", inspection.getInspectorName());
        data.put("status", inspection.getStatus());
        return data;
    }

    public void insertInspection(FieldInspection inspection) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues data = getInspectionData(inspection);
        db.insert("Inspection", null, data);
    }

    public List<FieldInspection> showInspections() {
        String sql = "SELECT * FROM Inspection;";
        SQLiteDatabase db = getReadableDatabase();
        Cursor line = db.rawQuery(sql, null);
        List<FieldInspection> inspections = new ArrayList<FieldInspection>();
        while(line.moveToNext()) {
            FieldInspection inspection = new FieldInspection();
            inspection.setId(line.getInt(line.getColumnIndex("id")));
            inspection.setRealization(Date.valueOf(line.getString(line.getColumnIndex("realization"))));
            inspection.setInspectorName(line.getString(line.getColumnIndex("inspectorName")));
            inspection.setStatus(line.getString(line.getColumnIndex("status")));
            inspections.add(inspection);
        }
        line.close();
        return inspections;
    }

    public void removeInspection(FieldInspection inspection) {
        SQLiteDatabase db = getWritableDatabase();
        String[] params = {String.valueOf(inspection.getId())};
        db.delete("Inspection", "id = ?", params);
    }

    public void updateInspection(FieldInspection inspection) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues data = getInspectionData(inspection);
        String[] params = {String.valueOf(inspection.getId())};
        db.update("Inspection", data, "id = ?", params);
    }
}
