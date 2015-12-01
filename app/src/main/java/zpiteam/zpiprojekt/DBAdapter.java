package zpiteam.zpiprojekt;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

/**
 * Created by Maciej on 2015-06-10.
 */
public class DBAdapter {

    private static final DBAdapter adapter = new DBAdapter();

    private static final String DEBUG_TAG = "SqLiteDB";

    //tabele
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "database.db";
    private static final String PATIENTS_TABLE = "pacjent";
    private static final String PATRONS_TABLE = "opiekun";
    private static final String CONNECTIONS_TABLE = "powiazanie";
    private static final String LOCS_TABLE = "lokalizacja";

    //kolumny
    private static final String KEY_ID = "id";
    private static final String ID_OPTIONS = "INTEGER PRIMARY KEY AUTOINCREMENT";
    private static final int ID_COLUMN = 0;

    private static final String KEY_LOGIN = "login";
    private static final String LOGIN_OPTIONS = "TEXT NOT NULL";
    private static final int LOGIN_COLUMN = 1;

    private static final String KEY_PASS = "haslo";
    private static final String PASS_OPTIONS = "TEXT NOT NULL";
    private static final int PASS_COLUMN = 2;

    private static final String KEY_PATR_ID = "Opiekun_id";
    private static final String PATR_ID_OPTIONS = "INTEGER NOT NULL";
    private static final int PATR_ID_COLUMN = 1;

    private static final String KEY_PATIENT_ID = "Pacjent_id";
    private static final String PATIENT_ID_OPTIONS = "INTEGER NOT NULL";
    private static final int PATIENT_ID_COLUMN = 2;

    private static final String KEY_STATUS = "status"; //0 - false, 1 - true, 2 - nieokreslone
    private static final String STATUS_OPTIONS = "INTEGER NOT NULL";
    private static final int STATUS_COLUMN = 3;

    private static final String KEY_W = "szerokosc";
    private static final String W_OPTIONS = "INTEGER NOT NULL";
    private static final int W_COLUMN = 2;

    private static final String KEY_H = "wysokosc";
    private static final String H_OPTIONS = "INTEGER NOT NULL";
    private static final int H_COLUMN = 3;

    private static final String KEY_TIME = "czas";
    private static final String TIME_OPTIONS = "DATE NOT NULL";
    private static final int TIME_COLUMN = 4;

    //tworzenie tabel
    //Pacjenci
    private static final String DB_CREATE_PATIENTS_TABLE =
            "CREATE TABLE " + PATIENTS_TABLE + "( " +
                    KEY_ID + " " + ID_OPTIONS + ", " +
                    KEY_LOGIN + " " + LOGIN_OPTIONS + ", " +
                    KEY_PASS + " " + PASS_OPTIONS +
                    ");";
    private static final String DROP_PATIENTS_TABLE =
            "DROP TABLE IF EXISTS " + PATIENTS_TABLE;

    //Opiekunowie
    private static final String DB_CREATE_PATRONS_TABLE =
            "CREATE TABLE " + PATRONS_TABLE + "( " +
                    KEY_ID + " " + ID_OPTIONS + ", " +
                    KEY_LOGIN + " " + LOGIN_OPTIONS + ", " +
                    KEY_PASS + " " + PASS_OPTIONS +
                    ");";
    private static final String DROP_PATRONS_TABLE =
            "DROP TABLE IF EXISTS " + PATRONS_TABLE;

    //Powiązanie
    private static final String DB_CREATE_CONNECTIONS_TABLE =
            "CREATE TABLE " + CONNECTIONS_TABLE + "("
                + KEY_ID + " " + ID_OPTIONS + ", "
                + KEY_PATR_ID + " " + PATR_ID_OPTIONS + ", "
                + KEY_PATIENT_ID + " " + PATIENT_ID_OPTIONS + ", "
                + KEY_STATUS + " " + STATUS_OPTIONS + ", "
                +"FOREIGN KEY(" + KEY_PATR_ID +  ") REFERENCES " +  PATRONS_TABLE + "(" + KEY_ID + ")" + ", "
                +"FOREIGN KEY(" + KEY_PATIENT_ID + ") REFERENCES " + PATIENTS_TABLE + "(" + KEY_ID + ")" +
                ");";
    private static final String DROP_CONNECTIONS_TABLE =
            "DROP TABLE IF EXISTS " + CONNECTIONS_TABLE;

    //Lokalizacja
    private static final String DB_CREATE_LOCS_TABLE =
            "CREATE TABLE " + LOCS_TABLE + "(" +
                    KEY_ID + " " + ID_OPTIONS + ", " +
                    KEY_W + " " + W_OPTIONS + ", " +
                    KEY_H + " " + H_OPTIONS + ", " +
                    KEY_TIME + " " + TIME_OPTIONS + ", " +
                    KEY_PATIENT_ID + " " + PATIENT_ID_OPTIONS + ", " +
                    "FOREIGN KEY(" + KEY_PATIENT_ID + ") REFERENCES " +  PATIENTS_TABLE +"(" +KEY_ID +")" +
                    ");";
    private static final String DROP_LOCS_TABLE =
            "DROP TABLE IF EXISTS " + LOCS_TABLE;

    private SQLiteDatabase db;
    private Context context;
    private DatabaseHelper dbHelper;

    public DBAdapter() {}

    public static DBAdapter getInstance() {
        return adapter;
    }

    public void setContext(Context context){
        this.context = context;
    }

    //otwieranie połączenia z bazą
    public DBAdapter open(){
        dbHelper = new DatabaseHelper(context, DB_NAME, null, DB_VERSION);
        try {
            db = dbHelper.getWritableDatabase();
        } catch (SQLException e) {
            db = dbHelper.getReadableDatabase();
        }
        return this;
    }
    //zamykanie połączenia z bazą
    public void close() {
        dbHelper.close();
    }

    public boolean registerPatron(String login, String pass, Button patrButton, Button patButton){
        try {
            ContentValues values = new ContentValues();
            values.put(KEY_LOGIN, login);
            values.put(KEY_PASS, pass);
            open();
            db.insert(PATRONS_TABLE, null, values);
            return true;
        } catch (Exception e) {
            Log.d("Register Patron: ", e.getMessage());
            return false;
        }
    }

    public boolean registerPatient(String login, String pass, Button patrButton, Button patButton){
        try {
            ContentValues values = new ContentValues();
            values.put(KEY_LOGIN, login);
            values.put(KEY_PASS, pass);
            open();
            db.insert(PATIENTS_TABLE, null, values);
            return true;
        } catch (Exception e) {
            Log.d("Register Patient: ", e.getMessage());
            return false;
        }
    }

    public boolean synchronize(int idPatron, int idPatient){
        try {
            ContentValues values = new ContentValues();
            values.put(KEY_PATR_ID, idPatron);
            values.put(KEY_PATIENT_ID, idPatient);
            values.put(KEY_STATUS, 2);
            open();
            db.insert(CONNECTIONS_TABLE, null, values);
            return true;
        } catch (Exception e) {
            Log.d("Synchronize: ", e.getMessage());
        return false;
        }
    }

    public int changeConnectionStatus(int id, int status) {
        String where = KEY_ID + "=" + id;
        ContentValues values = new ContentValues();
        values.put(KEY_STATUS, status);
        return db.update(CONNECTIONS_TABLE, values, where, null);
    }

    public String checkConnection(int id){
        try {
            String[] columns = {KEY_PATR_ID};
            String where = KEY_PATIENT_ID + "=" + id +" AND "
                    + KEY_STATUS + "='2';";
            open();
            Cursor cursor = db.query(CONNECTIONS_TABLE, columns, where, null, null, null, null);
            if(cursor != null){
                cursor.moveToFirst();
                int patr = Integer.parseInt(cursor.getString(0));
                Log.d("Check Connection: ", "LICZBA: " + patr);
                String[] columns2 = {KEY_LOGIN};
                where = KEY_ID + "=" + patr +";";
                Cursor cursor2 = db.query(PATRONS_TABLE, columns2, where, null, null, null, null);
                if(cursor2 != null){
                    cursor2.moveToFirst();
                    Log.d("Check Connection: ", "LOGIN: " + cursor2.getString(0));
                    return cursor2.getString(0);
                } else {
                    return "";
                }
            }
            return "";
        } catch (Exception e) {
            Log.d("Check Connection: ", e.getMessage());
            return "";
        }
    }

    public boolean checkPatron(String login, String password){
        try {
            String[] columns = {KEY_ID, KEY_LOGIN, KEY_PASS};
            String where = KEY_LOGIN + "=" + "'" + login + "' AND "
                    + KEY_PASS + "=" + "'" + password + "';";
            open();
            Cursor cursor = db.query(PATRONS_TABLE, columns, where, null, null, null, null);
            if(cursor != null && cursor.moveToFirst()){
                cursor.moveToFirst();
                cursor.getString(ID_COLUMN);
                return true;
            }
            return false;
        } catch (Exception e) {
            Log.d("Check Patient: ", e.getMessage());
            return false;
        }
    }

    public int checkPatronID(String login){
        try {
            String[] columns = {KEY_ID};
            String where = KEY_LOGIN + "=" + "'" + login + "';";
            open();
            Cursor cursor = db.query(PATRONS_TABLE, columns, where, null, null, null, null);
            if(cursor != null && cursor.moveToFirst()){
                cursor.moveToFirst();
                return cursor.getInt(ID_COLUMN);
            }
            return -1;
        } catch (Exception e) {
            Log.d("Check Patient: ", e.getMessage());
            return -1;
        }
    }

    /*
    public String checkPatronMail(int id){
        try {
            String[] columns = {KEY_ID, KEY_LOGIN, KEY_PASS};
            String where = KEY_LOGIN + "=" + "'" + login + "'";
            open();
            Cursor cursor = db.query(PATRONS_TABLE, columns, where, null, null, null, null);
            if (cursor != null && cursor.moveToFirst()) {
                return cursor.getString(ID_COLUMN);
            }
            return "-1";
        } catch (Exception e) {
            Log.d("Check Patrons MAIL: ", e.getMessage());
            return "-1";
        }
    }
    */

    public boolean checkPatient(String login, String password){
        try {
            String[] columns = {KEY_ID, KEY_LOGIN, KEY_PASS};
            String where = KEY_LOGIN + "=" + "'" + login + "' AND "
            + KEY_PASS + "=" + "'" + password + "';";
            open();
            Cursor cursor = db.query(PATIENTS_TABLE, columns, where, null, null, null, null);
            if (cursor != null && cursor.moveToFirst()) {
                return true;
            }
            return false;
        } catch (Exception e) {
            Log.d("Check PATRON ID: ", e.getMessage());
            return false;
        }
    }

    public int checkPatientID(String login){
        try {
            String[] columns = {KEY_ID};
            String where = KEY_LOGIN + "=" + "'" + login + "';";
            open();
            Cursor cursor = db.query(PATIENTS_TABLE, columns, where, null, null, null, null);
            if(cursor != null && cursor.moveToFirst()){
                cursor.moveToFirst();
                return cursor.getInt(ID_COLUMN);
            }
            return -1;
        } catch (Exception e) {
            Log.d("Check Patient ID: ", e.getMessage());
            return -1;
        }
    }


    public String checkPatientMail(int id){
        try {
            String[] columns = {KEY_ID, KEY_LOGIN, KEY_PASS};
            String where = KEY_ID + "=" + "'" + id + "'";
            open();
            Cursor cursor = db.query(PATIENTS_TABLE, columns, where, null, null, null, null);
            if (cursor != null && cursor.moveToFirst()) {
                return cursor.getString(LOGIN_COLUMN);
            }
            return "-1";
        } catch (Exception e) {
            Log.d("Check Patient MAIL: ", e.getMessage());
            return "-1";
        }
    }


    public String checkAccountType(String login, String password){

        if (checkPatron(login, password)) {
            return "Opiekun";
        }
        if (checkPatient(login, password)) {
            return "Pacjent";
        }
        return "Brak";
    }

    public ArrayList<String> getPatientsList(int patrID) {
        try {
            ArrayList<String> patID = new ArrayList<String>();
            String[] columns = {KEY_PATIENT_ID};
            String where = KEY_PATR_ID + "=" + "'" + patrID + "' AND "
                    + KEY_STATUS + "='1';";
            open();
            Cursor cursor = db.query(CONNECTIONS_TABLE, columns, where, null, null, null, null);
            if (cursor != null && cursor.moveToFirst()) {
                patID.add(cursor.getString(0));
                while (cursor.moveToNext()) {
                    //cursor.moveToNext();
                    patID.add(cursor.getString(0));
                }
                ArrayList<String> patLogins = new ArrayList<String>();
                for(int i = 0; i < patID.size(); i++) {
                    patLogins.add(checkPatientMail(Integer.parseInt(patID.get(i))));
                }
                return patLogins;
            }
            return null;
        } catch (Exception e) {
            Log.d("Check Patient: ", e.getMessage());
            return null;
        }
    }

    public void createLocalization(double latitude, double longitude, String time, int id) {
        try {
            ContentValues values = new ContentValues();
            values.put(KEY_W, latitude);
            values.put(KEY_H, longitude);
            values.put(KEY_TIME, time);
            values.put(KEY_PATIENT_ID, id);
            db.insert(LOCS_TABLE, null, values);
        } catch (Exception e) {
            Log.d("Create Localization: ", e.getMessage());
        }
    }

    public ArrayList<String> getLocalization(int id) {
        try {
            ArrayList<String> localization = new ArrayList<String>();
            String[] columns = {KEY_W, KEY_H, KEY_TIME, KEY_ID};
            String where = KEY_PATIENT_ID + "=" + "'" + id + "';";
            String orderBy = KEY_ID + " DESC";
            open();
            Cursor cursor = db.query(LOCS_TABLE, columns, where, null, null, null, orderBy);
            if(cursor != null && cursor.moveToFirst()){
                cursor.moveToFirst();
                localization.add(cursor.getString(0)); //szerokość
                localization.add(cursor.getString(1)); //wysokość
                localization.add(cursor.getString(2)); //data
                Log.d("getLocalization : ", "W: " + localization.get(0) + "S: " + localization.get(1));
                return localization;
            }
            return null;
        } catch (Exception e) {
            Log.d("getLocalization: ", e.getMessage());
            return null;
        }
    }

    private static class DatabaseHelper extends SQLiteOpenHelper {
        public DatabaseHelper(Context context, String name,
                              SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(DB_CREATE_PATIENTS_TABLE);
            db.execSQL(DB_CREATE_PATRONS_TABLE);
            db.execSQL(DB_CREATE_CONNECTIONS_TABLE);
            db.execSQL(DB_CREATE_LOCS_TABLE);

            Log.d(DEBUG_TAG, "Database creating...");
            //Log.d(DEBUG_TAG, "Table " + DB_PAT + " ver." + DB_VERSION + " created");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL(DROP_PATIENTS_TABLE);
            db.execSQL(DROP_PATRONS_TABLE);

            Log.d(DEBUG_TAG, "Database updating...");
            //Log.d(DEBUG_TAG, "Table " + DB_TODO_TABLE + " updated from ver." + oldVersion + " to ver." + newVersion);
            Log.d(DEBUG_TAG, "All data is lost.");

            onCreate(db);
        }
    }
}
