package com.example.transport;

import android.app.Application;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper  extends Application {

    private FunctionsCall functionsCall = new FunctionsCall();
    private MyHelper mh;
    private SQLiteDatabase myDataBase;
    private String DATABASE_PATH;

    //Constructor
    public DatabaseHelper(Context context) {
        DATABASE_PATH = String.format("%s%s", functionsCall.filepath("Database"), File.separator);
        String DATABASE_NAME = String.format("%s%s", DATABASE_PATH, "transport.db");
        mh = new MyHelper(context, DATABASE_NAME, null, 2);
    }


    public boolean insertdata(Transport_Model transport_model, DatabaseHelper databaseHelper) {
        open();
        ContentValues contentValues = new ContentValues();
        contentValues.put("NAME", transport_model.getNAME());
        contentValues.put("PHONENO", transport_model.getPHONENO());
        contentValues.put("TYPE_OF_TANK", transport_model.getTYPE_OF_TANK());
        contentValues.put("RO_NAME", transport_model.getRO_NAME());
        contentValues.put("DISTRICT_OFFICE_NAME", transport_model.getDISTRICT_OFFICE_NAME());
        contentValues.put("PROJECT_OFFICE_NAME", transport_model.getPROJECT_OFFICE_NAME());
        contentValues.put("VILLAGE_NAME", transport_model.getVILLAGE_NAME());
        contentValues.put("TANK_NAME", transport_model.getTANK_NAME());
        contentValues.put("WORK_START_TIME", transport_model.getWORK_START_TIME());
        contentValues.put("NO_MACHINE_DEPLOYED", transport_model.getNO_MACHINE_DEPLOYED());
        contentValues.put("HITACHI_WORK_START_TIME", transport_model.getHITACHI_WORK_START_TIME());
        contentValues.put("HITACHI_WORK_END_TIME", transport_model.getHITACHI_WORK_END_TIME());
        contentValues.put("JCB_WORK_START_TIME", transport_model.getJCB_WORK_START_TIME());
        contentValues.put("JCB_WORK_END_TIME", transport_model.getJCB_WORK_END_TIME());
        contentValues.put("SILT_TRANSPORTATION", transport_model.getSILT_TRANSPORTATION());
        contentValues.put("NO_SILT_TRANSPORTATION", transport_model.getNO_SILT_TRANSPORTATION());
        contentValues.put("ROLE", transport_model.getRole());

        long result = myDataBase.insert("TRANSPORT", null, contentValues);
        close();
       return result != -1;
    }

  /*  public boolean insertInMastOut(ContentValues cv) {
        long result = myDataBase.insertOrThrow("TRANSPORT", null, cv);
        return result != -1;
    }*/

    public long Regdata_insert(RegModel regModel) {
        open();
        ContentValues contentValues = new ContentValues();
        contentValues.put("NAME", regModel.getNAME());
        contentValues.put("EMAIL", regModel.getEMAIL());
        contentValues.put("PHONE", regModel.getPHONE());
        contentValues.put("PASSWORD", regModel.getPASSWORD());
        long reg = myDataBase.insert("REGISTARTION", null, contentValues);
        close();
        return reg;
    }


    public ArrayList<RegModel> regModels() {
        ArrayList<RegModel> regModelslist = new ArrayList<>();
        String selectall = "SELECT * FROM REGISTARTION";
        open();
        Cursor cursor = myDataBase.rawQuery(selectall, null);
        if (cursor.moveToFirst()) {
            do {
                RegModel reg = new RegModel();
                reg.setNAME(cursor.getString(1));
                reg.setEMAIL(cursor.getString(2));
                reg.setPHONE(cursor.getString(3));
                reg.setPASSWORD(cursor.getString(4));
                regModelslist.add(reg);
            } while (cursor.moveToNext());
        }
        close();
        // return contact list
        return regModelslist;
    }

    public ArrayList<Transport_Model> getAllTransport() {
        ArrayList<Transport_Model> transportModelArrayList = new ArrayList<Transport_Model>();
        // Select All Query
        String selectQuery = "SELECT  * FROM TRANSPORT";

        open();
        Cursor cursor = myDataBase.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Transport_Model transport_model = new Transport_Model();
                transport_model.setNAME(cursor.getString(1));
                transport_model.setPHONENO(cursor.getString(2));
                transport_model.setTYPE_OF_TANK(cursor.getString(3));
                transport_model.setRO_NAME(cursor.getString(4));
                transport_model.setDISTRICT_OFFICE_NAME(cursor.getString(5));
                transport_model.setPROJECT_OFFICE_NAME(cursor.getString(6));
                transport_model.setVILLAGE_NAME(cursor.getString(7));
                transport_model.setTANK_NAME(cursor.getString(8));
                transport_model.setWORK_START_TIME(cursor.getString(9));
                transport_model.setNO_MACHINE_DEPLOYED(cursor.getString(10));
                transport_model.setHITACHI_WORK_START_TIME(cursor.getString(11));
                transport_model.setHITACHI_WORK_END_TIME(cursor.getString(12));
                transport_model.setJCB_WORK_START_TIME(cursor.getString(13));
                transport_model.setJCB_WORK_END_TIME(cursor.getString(14));
                transport_model.setSILT_TRANSPORTATION(cursor.getString(15));
                transport_model.setNO_SILT_TRANSPORTATION(cursor.getString(16));
                transport_model.setRole(cursor.getString(17));
                // Adding contact to list
                transportModelArrayList.add(transport_model);
            } while (cursor.moveToNext());
        }
        close();
        // return contact list
        return transportModelArrayList;
    }

    //Open database
    public void open() {
        myDataBase = mh.getWritableDatabase();
    }

    //Close database
    public void close() {
        myDataBase.close();
    }

    private class MyHelper extends SQLiteOpenHelper {
        MyHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE TRANSPORT (_id INTEGER PRIMARY KEY , NAME TEXT unique, PHONENO TEXT  not null, TYPE_OF_TANK TEXT, " +
                    "RO_NAME TEXT, DISTRICT_OFFICE_NAME TEXT, PROJECT_OFFICE_NAME TEXT, VILLAGE_NAME TEXT, TANK_NAME TEXT, WORK_START_TIME TEXT, " +
                    "NO_MACHINE_DEPLOYED TEXT, HITACHI_WORK_START_TIME TEXT, HITACHI_WORK_END_TIME TEXT, JCB_WORK_START_TIME TEXT, JCB_WORK_END_TIME TEXT, SILT_TRANSPORTATION TEXT, NO_SILT_TRANSPORTATION TEXT,ROLE TEXT);");
            db.execSQL("CREATE TABLE REGISTARTION(_id INTEGER  PRIMARY KEY ,NAME TEXT unique,EMAIl TEXT,PHONE TEXT,PASSWORD TEXT)");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            if (newVersion > oldVersion)
                db.execSQL("ALTER TABLE TRANSPORT ADD NO_SILT_TRANSPORTATION TEXT");
        }
    }
}
