package ba.unsa.etf.rma.ena.dms_android.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ba.unsa.etf.rma.ena.dms_android.R;

public class AddKorisnikActivity extends AppCompatActivity {

    TextView ime;
    TextView prezime;
    TextView korisnickoIme ;
    TextView sifra;
    TextView sifraPonovo;
    Spinner listaUloga;
    Button addKorisnika;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_korisnik);

        //TODO change or add

        ime = (TextView) findViewById(R.id.textView_ime_add);
        prezime = (TextView) findViewById(R.id.textView_prezime_add);
        korisnickoIme = (TextView) findViewById(R.id.textView_korisnicko_ime_add);
        sifra = (TextView) findViewById(R.id.textView_sifra_add);
        sifraPonovo = (TextView) findViewById(R.id.textView_sifra_add_ponovo);

        //TODO spinner
        final List<String> list = new ArrayList<String>();
        list.add("Uloga 1");
        list.add("Uloga 2");
        list.add("Uloga 3");
        list.add("Uloga 4");
        list.add("Uloga 5");
        listaUloga = (Spinner) findViewById(R.id.dropdown_uloge);
        ArrayAdapter<String> adp1 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);
        adp1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        listaUloga.setAdapter(adp1);

        listaUloga.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
                // TODO Auto-generated method stub
                Toast.makeText(getBaseContext(), list.get(position), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });

        addKorisnika = (Button) findViewById(R.id.button_add_user);
        addKorisnika.setOnClickListener(view-> dodajKorisnika());
        //TODO validations

    }

    private void dodajKorisnika() {

        //TODO dodaj korisnika and return to list of users
    }
}
