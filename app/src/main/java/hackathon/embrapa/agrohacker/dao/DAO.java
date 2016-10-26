package hackathon.embrapa.agrohacker.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DAO extends SQLiteOpenHelper{

    public DAO(Context context) {
        super(context, "AgroHackerNewTableAgain", null, 1);
    }

    private String sql = "";

    @Override
    public void onCreate(SQLiteDatabase db) {
        sql = "CREATE TABLE Prague (id INTEGER PRIMARY KEY, " +
                "popularName TEXT, " +
                "scientificName TEXT, " +
                "description TEXT, " +
                "bioecology TEXT, " +
                "damage TEXT, " +
                "lifeCicleStage TEXT, " +
                "photoPath TEXT);";
        db.execSQL(sql);
        sql = "CREATE TABLE Predator (id INTEGER PRIMARY KEY, " +
                "popularName TEXT, " +
                "scientificName TEXT, " +
                "description TEXT, " +
                "importance TEXT, " +
                "photoPath TEXT);";
        db.execSQL(sql);
        sql = "CREATE TABLE Plot (id INTEGER PRIMARY KEY, "  +
                "plantationStage TEXT, " +
                "plantationStartDate TEXT, " +
                "harvestDate TEXT, " +
                "platationCulture TEXT, " +
                "status TEXT, " +
                "latitude1 REAL, " +
                "longitude1 REAL, " +
                "latitude2 REAL, " +
                "longitude2 REAL, " +
                "latitude3 REAL, " +
                "longitude3 REAL, " +
                "latitude4 REAL, " +
                "longitude4 REAL);";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        switch (oldVersion) {
            case 1:
                sql = "CREATE TABLE Inspection (id INTEGER PRIMARY KEY, realization DATE, inspectorName TEXT," +
                        "status TEXT);";
                db.execSQL(sql);
                sql = "CREATE TABLE Trap (id INTEGER PRIMARY KEY, lastChange DATE, duration INTEGER," +
                        "pheromone TEXT, status TEXT);";
                db.execSQL(sql);
        }
    }
}
