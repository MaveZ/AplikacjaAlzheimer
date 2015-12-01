package zpiteam.zpiprojekt;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AutoCompleteTextView;
import android.widget.Button;


public class OpiekunActivity extends ActionBarActivity {

    private AutoCompleteTextView mEmailView;
    public OpiekunActivity () {

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opiekun);
        Button patientsListBtn = (Button)findViewById(R.id.patientsList);
        patientsListBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(OpiekunActivity.this, PatientsListActivity.class));
                //finish();
            }
        });

        //mEmailView = (AutoCompleteTextView) findViewById(R.id.email);
        Button synchronizeButton = (Button)findViewById(R.id.synchronizeBtn);
        synchronizeButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(OpiekunActivity.this, SynchroActivity.class));
                //String email = mEmailView.getText().toString();
                //finish();
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_opiekun, menu);
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
