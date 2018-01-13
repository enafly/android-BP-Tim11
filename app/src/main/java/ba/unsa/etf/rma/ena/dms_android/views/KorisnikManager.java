package ba.unsa.etf.rma.ena.dms_android.views;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ba.unsa.etf.rma.ena.dms_android.DMSService;
import ba.unsa.etf.rma.ena.dms_android.MainActivity;
import ba.unsa.etf.rma.ena.dms_android.R;
import ba.unsa.etf.rma.ena.dms_android.Utils;
import ba.unsa.etf.rma.ena.dms_android.activities.AddKorisnikActivity;
import ba.unsa.etf.rma.ena.dms_android.adapters.KorisnikAdapter;
import ba.unsa.etf.rma.ena.dms_android.classes.Korisnik;
import ba.unsa.etf.rma.ena.dms_android.classes.LoggedIn;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Ena on 25.12.2017..
 *
 */

public class KorisnikManager {

    private MainActivity activity;
    private ArrayList<Korisnik> korisnici = new ArrayList<>();
    private View view;
    private LoggedIn loggedIn;


    public KorisnikManager(MainActivity mainActivity) {
        activity = mainActivity;
        loggedIn = activity.getLoggedIn();
        setContent();
    }

    private void setContent() {
        view = LayoutInflater.from(activity.getApplicationContext()).inflate(R.layout.layout_users, null, false);
        TextView tekst = (TextView) view.findViewById(R.id.textView_korisnici);
        tekst.setText(R.string.user_korisnici);

        //TODO action on IMageView
        ImageButton addUsersButton = (ImageButton) activity.findViewById(R.id.imageButton_add_user);
        addUsersButton.setImageResource(R.drawable.add_user);
        addUsersButton.setOnClickListener(v -> {
            Toast.makeText(activity, "test add", Toast.LENGTH_SHORT).show();
            Intent addKorisnik = new Intent(activity.getApplicationContext(), AddKorisnikActivity.class);
            activity.startActivity(addKorisnik);
        });
        getKorisnike();

    }

    public void getKorisnike() {

        Retrofit.Builder builder = new Retrofit.Builder()
                    .baseUrl(Utils.URL)
                    .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();

        DMSService dmsService = retrofit.create(DMSService.class);
        Call<List<Korisnik>> korisniciDobijeni = dmsService.listaKorisnika();

        korisniciDobijeni.enqueue(new Callback<List<Korisnik>>() {
                @Override
                public void onResponse(Call<List<Korisnik>> call, Response<List<Korisnik>> response) {
                    List<Korisnik> korisniciA = response.body();
                    //setContent();
                    addToKorisnici(korisniciA);
                    Log.i("AAAA", "Korisnici "+ korisnici.get(1).getIme() );
                    setListView();
                }
                @Override
                public void onFailure(Call<List<Korisnik>> call, Throwable t) {
                    Log.i("AAa", "Nesto nije okej:  " + t.toString());

                }
            });
    }

    private void setListView() {
        //TODO KORISNIK ADAPTER ON listView
        ListView listaKorisnika = (ListView) activity.findViewById(R.id.listView_korisnici);

        final KorisnikAdapter korisnikAdapter = new KorisnikAdapter(view.getContext(), R.layout.layout_user_list_item, korisnici,this);
        listaKorisnika.setAdapter(korisnikAdapter);
    }

    private void addToKorisnici(List<Korisnik> korisniciNovi) {
        if (loggedIn.getUloga() == 1) {
            korisnici.addAll(korisniciNovi);
        } else if (loggedIn.getUloga() == 2) {
            for (int i = 0; i < korisniciNovi.size(); i++) {
                if (korisniciNovi.get(i).getUloga() != 1 || korisniciNovi.get(i).getUloga() != 2) {
                    korisnici.add(korisniciNovi.get(i));

                }
            }
        }
    }

}
