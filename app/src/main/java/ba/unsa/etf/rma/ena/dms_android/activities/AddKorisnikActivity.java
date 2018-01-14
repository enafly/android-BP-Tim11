package ba.unsa.etf.rma.ena.dms_android.activities;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ba.unsa.etf.rma.ena.dms_android.DMSService;
import ba.unsa.etf.rma.ena.dms_android.R;
import ba.unsa.etf.rma.ena.dms_android.Utils;
import ba.unsa.etf.rma.ena.dms_android.classes.Korisnik;
import ba.unsa.etf.rma.ena.dms_android.classes.Uloga;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AddKorisnikActivity extends AppCompatActivity {

    private TextView ime;
    private TextView prezime;
    private TextView korisnickoIme;
    private TextView sifra;
    private TextView sifraPonovo;
    private Spinner listaUloga;
    private Button addKorisnika;
    private ArrayList<Uloga> uloge = new ArrayList<>();
    private int listaIntegera[];
    private Integer ulogaOdabrana;

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

       //setSpinner();
        addUlogeList();

        addKorisnika = (Button) findViewById(R.id.button_add_user);
        addKorisnika.setOnClickListener(view-> dodajKorisnika());
        //TODO validations

    }

    private void addUlogeList() {
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(Utils.URL)
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();

        DMSService dmsService = retrofit.create(DMSService.class);
        Call<List<Uloga>> ulogeDobijeni = dmsService.listaUloga();

        ulogeDobijeni.enqueue(new Callback<List<Uloga>>() {
            @Override
            public void onResponse(Call<List<Uloga>> call, Response<List<Uloga>> response) {
                List<Uloga> ulogeA = response.body();
                uloge.addAll(ulogeA);
                Log.i("AAAA", "Uloge "+ uloge.get(1).getNaziv() );
                setSpinner();
            }

            @Override
            public void onFailure(Call<List<Uloga>> call, Throwable t) {
                Log.i("AAa", "Nesto nije okej:  " + t.toString());
            }
        });
    }

    private void setSpinner() {
        List<String> list = new ArrayList<>();
        listaIntegera = new int[uloge.size()];
        for(int i=0; i<uloge.size(); i++){
            list.add(uloge.get(i).getNaziv());
            listaIntegera[i] = uloge.get(i).getId();
        }
        Log.i("AAA","Uloge: " + list.size()+ " Sraaje " + list.get(1));

        listaUloga = (Spinner) findViewById(R.id.dropdown_uloge);
        ArrayAdapter<String> adp1 = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,list);
        adp1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        listaUloga.setAdapter(adp1);

        listaUloga.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
                Log.i("AAA ", list.get(position));
                ulogaOdabrana = listaIntegera[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                ulogaOdabrana = listaIntegera[0];
            }
        });
    }

    private void dodajKorisnika() {
        String imeKorisnika = ime.getText().toString();
        String prezimeKorisnika =  prezime.getText().toString();
        String korisnickoImeKorisnika = korisnickoIme.getText().toString();
        String sifraKorisnika = sifra.getText().toString();
        String sifraPonovoKorisnika = sifraPonovo.getText().toString();

        ime.setError(null);

        //TODO Validacije
        Korisnik korisnik=new Korisnik(0,imeKorisnika,prezimeKorisnika,korisnickoImeKorisnika,sifraKorisnika,ulogaOdabrana);
        callDodajKorisnikaApi(korisnik);
        finish();
        Log.i("AAA","Korisnik dodan");

    }

    private void callDodajKorisnikaApi(Korisnik korisnik) {
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(Utils.URL)
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();

        DMSService dmsService = retrofit.create(DMSService.class);
        Call<Void> dodajKorisnikaCall = dmsService.dodajKorisnika(korisnik);

        dodajKorisnikaCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(@NonNull Call<Void> call, @NonNull Response<Void> response) {
                if(response.isSuccessful())
                    Log.i("AAA", "Uspješno dodan korisnik");
                else
                    Log.i("AAA", "Greška");
            }

            @Override
            public void onFailure(@NonNull Call<Void> call, @NonNull Throwable t) {
                 Log.i("AAa", "Nesto nije okej:  " + t.toString());
            }
        });
    }

}
