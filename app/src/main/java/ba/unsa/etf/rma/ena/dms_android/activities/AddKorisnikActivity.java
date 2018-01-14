package ba.unsa.etf.rma.ena.dms_android.activities;

import android.content.Intent;
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
import ba.unsa.etf.rma.ena.dms_android.classes.LoggedIn;
import ba.unsa.etf.rma.ena.dms_android.classes.Uloga;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AddKorisnikActivity extends AppCompatActivity {

    TextView naslov;
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
    LoggedIn loggedIn;
    boolean mojProfil;
    int kliknuti;
    Korisnik korisnik = new Korisnik();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_korisnik);

        Intent changeOrAdd = getIntent();
        loggedIn = changeOrAdd.getExtras().getParcelable("loggedIn");
        mojProfil = changeOrAdd.getBooleanExtra("mojProfil",false);
        kliknuti = changeOrAdd.getIntExtra("idKliknutog",0);

        naslov = (TextView) findViewById(R.id.textView_add_user);
        ime = (TextView) findViewById(R.id.textView_ime_add);
        prezime = (TextView) findViewById(R.id.textView_prezime_add);
        korisnickoIme = (TextView) findViewById(R.id.textView_korisnicko_ime_add);
        sifra = (TextView) findViewById(R.id.textView_sifra_add);
        sifraPonovo = (TextView) findViewById(R.id.textView_sifra_add_ponovo);
        listaUloga = (Spinner) findViewById(R.id.dropdown_uloge);
        addKorisnika = (Button) findViewById(R.id.button_add_user);
        addKorisnika.setOnClickListener(view-> dodajKorisnika());

        if(kliknuti!=0){
            naslov.setText(getResources().getText(R.string.promijeni_user));
            addUlogeList();
            korisnik = getKorisnikApiCall(kliknuti);
            addKorisnika.setText(getResources().getText(R.string.promijeni_user));
        }
        else if(mojProfil){
            naslov.setText(getResources().getText(R.string.promijeni_user));
            if(loggedIn.getUloga()!=1){
                listaUloga.setVisibility(View.GONE);
            }
            else {
                addUlogeList();
            }
            korisnik = getKorisnikApiCall(loggedIn.getId());
            addKorisnika.setText(getResources().getText(R.string.promijeni_user));
        }
        else {
            addUlogeList();
        }
        //TODO validations

    }

    private void change() {
        ime.setText(korisnik.getIme());
        prezime.setText(korisnik.getPrezime());
        korisnickoIme.setText(korisnik.getKorisnickoIme());
        sifra.setText(korisnik.getSifra());
        sifraPonovo.setText(korisnik.getSifra());
    }


    private Korisnik getKorisnikApiCall(int kliknuti) {
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(Utils.URL)
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();

        DMSService dmsService = retrofit.create(DMSService.class);
        Call<Korisnik> korisnikDobijeni = dmsService.findVlasnikById(kliknuti);

        korisnikDobijeni.enqueue(new Callback<Korisnik>() {
            @Override
            public void onResponse(Call<Korisnik> call, Response<Korisnik> response) {
                korisnik = response.body();
                Log.i("AAA", response.body().toString());
                if(kliknuti!=0 || mojProfil){
                    change();
                }
            }

            @Override
            public void onFailure(Call<Korisnik> call, Throwable t) {
                Log.i("AAa", "Nesto nije okej:  " + t.toString());
            }
        });

        return null;
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

        ArrayAdapter<String> adp1 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);
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
        if (!mojProfil &&  kliknuti==0){
            korisnik=new Korisnik(0,imeKorisnika,prezimeKorisnika,korisnickoImeKorisnika,sifraKorisnika,ulogaOdabrana);
        }

        korisnik.setIme(imeKorisnika);
        korisnik.setPrezime(prezimeKorisnika);
        korisnik.setKorisnickoIme(korisnickoImeKorisnika);
        korisnik.setSifra(sifraKorisnika);


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
