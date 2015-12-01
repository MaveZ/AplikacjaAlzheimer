package zpiteam.zpiprojekt;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;


public class PatientsListActivity extends ActionBarActivity {

    private ListView list;
    private ArrayAdapter<String> adapter ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patients_list);

        final ArrayList<String> Patients = DBAdapter.getInstance().getPatientsList(CurrentUser.getInstance().getID()); //tutaj trzeba zmienic pa1@test na funkcje sprawdzajaca login lub id opiekuna
        if(Patients != null)
        {
            adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, Patients);
            list = (ListView)findViewById(R.id.listView1);
            list.setAdapter(adapter);

            list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    // When clicked
                    String login_pacjenta = Patients.get((int)id);
                    Intent intent = new Intent(PatientsListActivity.this, PatientOptionsActivity.class);
                    intent.putExtra("login_pacjenta", login_pacjenta);
                    startActivity(intent);
                }
            });
        }
        else
        {
            ArrayList<String> Patients1 = new ArrayList<String>();
            Patients1.add("Brak przypisanych pacjentow.");
            adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, Patients1);
            list = (ListView)findViewById(R.id.listView1);
            list.setAdapter(adapter);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_patients_list, menu);
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
