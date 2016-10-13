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
import hackathon.embrapa.agrohacker.model.Trap;

public class TrapDAO extends SQLiteOpenHelper{

    public TrapDAO(Context context) {
        super(context, "AgroHacker", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE Trap (id INTEGER PRIMARY KEY, lastChange DATE, duration INTEGER," +
                "pheromone TEXT, status TEXT);";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXIST Trap;";
        db.execSQL(sql);
        onCreate(db);
    }

    private ContentValues getTrapData(Trap trap) {
        ContentValues data = new ContentValues();
        data.put("lastChange", trap.getLastChange().toString());
        data.put("duration", trap.getDuration().toString());
        data.put("pheromone", trap.getPheromone());;
        data.put("status", trap.getStatus());
        return data;
    }

    public void insertTrap(Trap trap) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues data = getTrapData(trap);
        db.insert("Trap", null, data);
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
            trap.setLastChange(Date.valueOf(line.getString(line.getColumnIndex("lastChange"))));
            trap.setDuration(Integer.valueOf(line.getString(line.getColumnIndex("duration"))));
            trap.setStatus(line.getString(line.getColumnIndex("status")));
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
