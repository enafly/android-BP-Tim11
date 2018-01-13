package ba.unsa.etf.rma.ena.dms_android.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import ba.unsa.etf.rma.ena.dms_android.DMSService;
import ba.unsa.etf.rma.ena.dms_android.R;
import ba.unsa.etf.rma.ena.dms_android.Utils;
import ba.unsa.etf.rma.ena.dms_android.classes.Korisnik;
import ba.unsa.etf.rma.ena.dms_android.views.KorisnikManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Ena on 25.12.2017..
 *
 */

public class KorisnikAdapter extends ArrayAdapter<Korisnik> {

    private boolean[] toggle;
    private Context context;
    private View view;
    KorisnikManager korisnikManager;


    public KorisnikAdapter(Context context, int resource, List<Korisnik> korisnici, KorisnikManager korisnikManager) {
        super(context, resource, korisnici);
        this.context = context;
        this.korisnikManager = korisnikManager;
        toggle = new boolean[korisnici.size()];
        fillToggle(korisnici.size());
    }

    private void fillToggle(int size) {
        for (int i= 0; i<size; i++){
            toggle[i] = true;
        }
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        view = convertView;

        if (view == null) {
            LayoutInflater layoutInflater;
            layoutInflater = LayoutInflater.from(getContext());
            view = layoutInflater.inflate(R.layout.layout_user_list_item, parent,false);
        }

        final Korisnik korisnik = getItem(position);

        if (korisnik != null) {

            TextView ime = (TextView) view.findViewById(R.id.textView_ime_u);
            TextView prezime = (TextView) view.findViewById(R.id.textView_vlasnik_u);
            TextView korisnickoIme = (TextView) view.findViewById(R.id.textView_korisnicko_ime_u);

            if (ime != null) {    ime.setText(korisnik.getIme());}
            if (prezime != null) {  prezime.setText(korisnik.getPrezime()); }
            if (korisnickoIme != null) {  korisnickoIme.setText(korisnik.getKorisnickoIme());    }

            View buttonView = view.findViewById(R.id.buttons_layout);
            RelativeLayout listElementKorisnik = (RelativeLayout) view.findViewById(R.id.list_element_korisnik);
            listElementKorisnik.setOnClickListener(v -> showHideOptions(korisnik, position, buttonView));

            ImageButton changeUser = (ImageButton) view.findViewById(R.id.imageButton_edit_document);
            ImageButton deleteUser = (ImageButton) view.findViewById(R.id.imageButton_delete_user);
            changeUser.setOnClickListener(v ->{
                //TODO change user
                Log.i("AAAA", "imageButton_change_user position: " + position);

            });
            deleteUser.setOnClickListener(v -> {
                callDeleteKorisnikApi(korisnik.getId());
                Log.i("AAAA", "imageButton_delete_user position: " + position);
            });
        }
        return view;
    }

    private void callDeleteKorisnikApi(int id) {
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(Utils.URL)
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();

        DMSService dmsService = retrofit.create(DMSService.class);
        Call<Void> brisiKorisnika = dmsService.deleteRole(id);

        brisiKorisnika.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(@NonNull Call<Void> call, @NonNull Response<Void> response) {
                korisnikManager.getKorisnike();
                Log.i("AAAA", "Korisnik obrisan ");
            }

            @Override
            public void onFailure(@NonNull Call<Void> call, @NonNull Throwable t) {
                Log.i("AAa", "Nesto nije okej:  " + t.toString());
            }
        });
    }

    private void showHideOptions(Korisnik korisnik, int toggleIndex, View buttonView) {
       Log.i("AAAAAAAAA","showHideOptions position: " + toggleIndex);
        if (toggle[toggleIndex]) {
            toggle[toggleIndex] = false;
            buttonView.setVisibility(View.VISIBLE);
        } else {
            buttonView.setVisibility(View.GONE);
            toggle[toggleIndex] = true;
       }
    }

}
