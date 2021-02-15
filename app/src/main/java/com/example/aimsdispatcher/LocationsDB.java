package com.example.aimsdispatcher;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class LocationsDB {

    public static final String KEY_ROWID="_id";
    public static final String KEY_LATITUDE="_latitute";
    public static final String KEY_LONGITUDE="_longitude";
    public static  final String KEY_CITY = "_city";
    public static final String KEY_STATE = "_state";

    private final String DATABASE_NAME ="LocationsDB";
    private final String DATABASE_TABLE="LocationsTable";
    private final int DATABASE_VERSION=1;

    private DBHelper ourHelper;
    private final Context ourContext;
    private SQLiteDatabase ourDatabase;

    public LocationsDB(Context ourContext) {
        this.ourContext = ourContext;
    }

    private class DBHelper extends SQLiteOpenHelper{
        public DBHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            String sqlCode="CREATE TABLE "+DATABASE_TABLE+"("+
                    KEY_ROWID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                    KEY_LATITUDE+" TEXT NOT NULL, "+
                    KEY_LONGITUDE+" TEXT NOT NULL, "+
                    KEY_CITY+" TEXT NOT NULL, "+
                    KEY_STATE+" TEXT NOT NULL);";
            db.execSQL(sqlCode);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

            db.execSQL("DROP TABLE IF EXISTS "+ DATABASE_TABLE);
            onCreate(db);
        }
    }

    public LocationsDB open() throws SQLException{
        ourHelper=new DBHelper(ourContext);
        ourDatabase=ourHelper.getWritableDatabase();
        return this;
    }

    public void close()
    {
        ourHelper.close();
    }

    public long createEntry(String latitude, String longitude, String city, String state){
        ContentValues cv = new ContentValues();
        cv.put(KEY_LATITUDE, latitude);
        cv.put(KEY_LONGITUDE,longitude);
        cv.put(KEY_CITY,city);
        cv.put(KEY_STATE,state);
        return ourDatabase.insert(DATABASE_TABLE,null,cv);
    }

    public String getData(){
        String [] columns = new String[] {KEY_ROWID,KEY_LATITUDE,KEY_LONGITUDE,KEY_CITY,KEY_STATE};
        Cursor c= ourDatabase.query(DATABASE_TABLE,columns,null,null,null,null,null);
        String result="";
        int iRowID = c.getColumnIndex(KEY_ROWID);
        int ilatitude = c.getColumnIndex(KEY_LATITUDE);
        int ilongitude =c.getColumnIndex(KEY_LONGITUDE);
        int icity = c.getColumnIndex(KEY_CITY);
        int istate = c.getColumnIndex(KEY_STATE);
        for(c.moveToFirst();!c.isAfterLast();c.moveToNext()){

            result= result+c.getString(iRowID).trim()+": "+"Latitude: "+c.getString(ilatitude).trim()+" Longitude: "+
                    c.getString(ilongitude).trim()+" City: "+c.getString(icity).trim()+" State: "+c.getString(istate).trim()+"\n";
        }
        c.close();

        return result;
    }

}
