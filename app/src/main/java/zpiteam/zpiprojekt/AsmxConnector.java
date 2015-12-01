package zpiteam.zpiprojekt;

import android.content.ContentValues;
import android.database.Cursor;
import android.util.Log;
import android.widget.Button;

import java.util.ArrayList;

import pl.hostingasp.karpiq.asmx_connector.ASMX_Actions;

/**
 * Created by Robert on 2015-12-01.
 */
public class AsmxConnector {
    private static final AsmxConnector ASMX = new AsmxConnector();

    public AsmxConnector() {}

    public static AsmxConnector getInstance() {
        return ASMX;
    }

    public String register(String login, String pass, boolean isGuardian) {
        try {
            return ASMX_Actions.getInstance().RegisterAccount(login, pass, isGuardian);

        } catch (Exception e) {
            Log.d("Register: ", e.getMessage());
            return "Rejestracja nie powiodła się";
        }
    }


    public boolean synchronize(int idPatron, int idPatient) {
        try {
            ASMX_Actions.getInstance().InitiateConnection(idPatient, idPatron);
            return true;
        } catch (Exception e) {
            Log.d("Synchronize: ", e.getMessage());
            return false;
        }
    }

    /*public int changeConnectionStatus(int id, int status) {
        String where = KEY_ID + "=" + id;
        ContentValues values = new ContentValues();
        values.put(KEY_STATUS, status);
        return db.update(CONNECTIONS_TABLE, values, where, null);
    }

    public String checkConnection(int id) {
        try {
            String[] columns = {KEY_PATR_ID};
            String where = KEY_PATIENT_ID + "=" + id + " AND "
                    + KEY_STATUS + "='2';";
            open();
            Cursor cursor = db.query(CONNECTIONS_TABLE, columns, where, null, null, null, null);
            if (cursor != null) {
                cursor.moveToFirst();
                int patr = Integer.parseInt(cursor.getString(0));
                Log.d("Check Connection: ", "LICZBA: " + patr);
                String[] columns2 = {KEY_LOGIN};
                where = KEY_ID + "=" + patr + ";";
                Cursor cursor2 = db.query(PATRONS_TABLE, columns2, where, null, null, null, null);
                if (cursor2 != null) {
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

    public boolean checkPatron(String login, String password) {
        try {
            String[] columns = {KEY_ID, KEY_LOGIN, KEY_PASS};
            String where = KEY_LOGIN + "=" + "'" + login + "' AND "
                    + KEY_PASS + "=" + "'" + password + "';";
            open();
            Cursor cursor = db.query(PATRONS_TABLE, columns, where, null, null, null, null);
            if (cursor != null && cursor.moveToFirst()) {
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

    public int checkPatronID(String login) {
        try {
            String[] columns = {KEY_ID};
            String where = KEY_LOGIN + "=" + "'" + login + "';";
            open();
            Cursor cursor = db.query(PATRONS_TABLE, columns, where, null, null, null, null);
            if (cursor != null && cursor.moveToFirst()) {
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
/*
    public boolean checkPatient(String login, String password) {
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

    public int checkPatientID(String login) {
        try {
            String[] columns = {KEY_ID};
            String where = KEY_LOGIN + "=" + "'" + login + "';";
            open();
            Cursor cursor = db.query(PATIENTS_TABLE, columns, where, null, null, null, null);
            if (cursor != null && cursor.moveToFirst()) {
                cursor.moveToFirst();
                return cursor.getInt(ID_COLUMN);
            }
            return -1;
        } catch (Exception e) {
            Log.d("Check Patient ID: ", e.getMessage());
            return -1;
        }
    }


    public String checkPatientMail(int id) {
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


    public String checkAccountType(String login, String password) {

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
                for (int i = 0; i < patID.size(); i++) {
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
            if (cursor != null && cursor.moveToFirst()) {
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
    }*/
}