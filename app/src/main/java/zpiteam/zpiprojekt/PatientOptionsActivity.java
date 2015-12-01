package zpiteam.zpiprojekt;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.net.Uri;
import android.widget.TextView;

import java.util.ArrayList;

public class PatientOptionsActivity extends ActionBarActivity {

    private String login_pacjenta;
    private int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_options);
        Intent i = getIntent();
        login_pacjenta = i.getStringExtra("login_pacjenta"); //pobiera ID pacjenta z poprzedniej activity
        id = DBAdapter.getInstance().checkPatientID(login_pacjenta); //id pacjenta

        TextView synchroText = (TextView) findViewById(R.id.textView);
        synchroText.setText(login_pacjenta);
        Button localize = (Button)findViewById(R.id.map_button);
        localize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<String> localization = new ArrayList<String>();
                localization = DBAdapter.getInstance().getLocalization(id);
                if (localization != null) {
                    String latitude = localization.get(0);
                    String longitude = localization.get(1);
                    String data = localization.get(2);
                    Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                            Uri.parse("geo:0,0?q=" + latitude + "," + longitude + " (" + data + ")"));
                    startActivity(intent);
                }
            }
        });

        Button history = (Button)findViewById(R.id.history_button);
        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        Button delete = (Button)findViewById(R.id.delete_button);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_patient_options, menu);
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
