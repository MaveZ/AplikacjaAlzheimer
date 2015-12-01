package zpiteam.zpiprojekt;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;


public class PacjentActivity extends ActionBarActivity {

    String synchroName = "";
    boolean synchro;
    private AutoCompleteTextView mEmailView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pacjent);

        final TextView synchroText = (TextView) findViewById(R.id.synchroText);

        /*Button checkSynchroButton = (Button) findViewById(R.id.checkForSynch);
        checkSynchroButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                synchroName = DBAdapter.getInstance().checkConnection(CurrentUser.getInstance().getID());

                if (!synchroName.equals("")) {
                    synchroText.setText(synchroName);
                    synchro = true;
                } else {
                    synchroText.setText("Brak prosb o synch");
                    synchro = false;
                }
                //finish();
            }
        });*/

        //Przycisk do prosby o synchro
        //mEmailView = (AutoCompleteTextView) findViewById(R.id.email);
        /*Button acceptSynchroButton = (Button) findViewById(R.id.acceptSynchro);
        acceptSynchroButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBAdapter.getInstance().changeConnectionStatus(CurrentUser.getInstance().getID(), 1);
                //finish();
            }
        });

        Button declineSynchroButton = (Button) findViewById(R.id.declineSynchro);
        declineSynchroButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBAdapter.getInstance().changeConnectionStatus(CurrentUser.getInstance().getID(), 0);
                //finish();
            }
        });*/


        Button localizeButton = (Button) findViewById(R.id.localizeBtn);
        localizeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PacjentActivity.this, LocalizeActivity.class));
                //finish();
            }
        });
    }

    public void showAlert(View view) {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        synchroName = DBAdapter.getInstance().checkConnection(CurrentUser.getInstance().getID());

        if (synchroName.equals("")) { //jezeli nie znaleziono polaczenia
            alert.setMessage("Brak prosb o synchronizacje")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    })
                    .create();
        }
        else {
            alert.setMessage("Prosba od " + synchroName)
                    .setPositiveButton("Przyjmij", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            DBAdapter.getInstance().changeConnectionStatus(CurrentUser.getInstance().getID(), 1);
                            dialogInterface.dismiss();
                        }
                    })
                    .setNegativeButton("Odrzuc", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            DBAdapter.getInstance().changeConnectionStatus(CurrentUser.getInstance().getID(), 0);
                            dialogInterface.dismiss();
                        }
                    })
                    .setNeutralButton("Anuluj", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    })
                    .create();
        }
        alert.show();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_pacjent, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
