package hackathon.embrapa.agrohacker.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import hackathon.embrapa.agrohacker.model.Plot;
import hackathon.embrapa.agrohacker.model.Trap;

public class TrapDAO extends DAO {

    public TrapDAO(Context context) {
        super(context);
    }

    private ContentValues getTrapData(Trap trap) {
        ContentValues data = new ContentValues();
        data.put("lastChange", trap.getLastChange().toString());
        data.put("duration", trap.getDuration().toString());
        data.put("pheromone", trap.getPheromone());;
        data.put("status", trap.getStatus());
        data.put("latitude", trap.getLatitude());
        data.put("longitude", trap.getLongitude());
        return data;
    }

    public void insertTrap(Trap trap) {
        try{
            SQLiteDatabase db = getWritableDatabase();
            ContentValues data = getTrapData(trap);
            Log.i("trap no banco", trap.getLatitude() +" "+ trap.getLongitude());
            db.insert("Trap", null, data);
        } catch (SQLiteException e) {
            e.printStackTrace();
        }
    }

    public List<Trap> showTraps() {
        String sql = "SELECT * FROM Trap;";
        SQLiteDatabase db = getReadableDatabase();
        Cursor line = db.rawQuery(sql, null);
        List<Trap> traps = new ArrayList<Trap>();
        while(line.moveToNext()) {
            Trap trap = new Trap();
            trap.setId((int) line.getLong(line.getColumnIndex("id")));
            trap.setPheromone(line.getString(line.getColumnIndex("pheromone")));
            trap.setLastChange(line.getString(line.getColumnIndex("lastChange")));
            trap.setDuration(Integer.valueOf(line.getString(line.getColumnIndex("duration"))));
            trap.setStatus(line.getString(line.getColumnIndex("status")));
            trap.setLatitude(line.getDouble(line.getColumnIndex("latitude")));
            trap.setLongitude(line.getDouble(line.getColumnIndex("longitude")));
            traps.add(trap);
        }
        line.close();
        return traps;
    }

    public void removeTrap(Trap trap) {
        SQLiteDatabase db = getWritableDatabase();
        String[] params = {String.valueOf(trap.getId())};
        db.delete("Trap", "id = ?", params);
    }

    public void updateTrap(Trap trap) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues data = getTrapData(trap);
        String[] params = {String.valueOf(trap.getId())};
        db.update("Trap", data, "id = ?", params);
    }
}
