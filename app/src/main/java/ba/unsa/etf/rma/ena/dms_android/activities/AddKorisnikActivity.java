package ba.unsa.etf.rma.ena.dms_android.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

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

        addKorisnika = (Button) findViewById(R.id.button_add_user);
        addKorisnika.setOnClickListener(view-> dodajKorisnika());
        //TODO validations

    }

    private void dodajKorisnika() {

        //TODO dodaj korisnika and return to list of users
    }
}
