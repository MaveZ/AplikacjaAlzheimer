package zpiteam.zpiprojekt;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;


public class SynchroActivity extends ActionBarActivity {

    private AutoCompleteTextView mEmailView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_synchro);

        mEmailView = (AutoCompleteTextView) findViewById(R.id.email);
    }

    public void Synchronizuj(View v)
    {
        // Reset errors.
        mEmailView.setError(null);

        // Store values at the time of the login attempt.
        String email = mEmailView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            mEmailView.setError(getString(R.string.error_field_required));
            focusView = mEmailView;
            cancel = true;
        } else if (!isEmailValid(email)) {
            mEmailView.setError(getString(R.string.error_invalid_email));
            focusView = mEmailView;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            int id_opiekuna = CurrentUser.getInstance().getID();
            int id_pacjenta = DBAdapter.getInstance().checkPatientID(email);
            if( id_pacjenta != -1) {
                if(DBAdapter.getInstance().synchronize(id_opiekuna, id_pacjenta)) {
                    Toast.makeText(this, "Prosba zostala wyslana", Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(this, "UWAGA! Prosba niewyslana. Sprobuj ponownie", Toast.LENGTH_LONG).show();
                }
            }
            else {
                Toast.makeText(this, "UWAGA! Nie znaleziono pacjenta. Sprobuj ponownie", Toast.LENGTH_LONG).show();
            }
            /*if(DBAdapter.getInstance().synchronize("1", "1", "1"))
            {
                Toast.makeText(this, "Prosba zostala wyslana", Toast.LENGTH_LONG).show();
            }
            else
            {
                Toast.makeText(this, "UWAGA! Prosba niewyslana. Sprobuj ponownie", Toast.LENGTH_LONG).show();
            }*/

        }
    }

    private boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
        return email.contains("@");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_synchro, menu);
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
