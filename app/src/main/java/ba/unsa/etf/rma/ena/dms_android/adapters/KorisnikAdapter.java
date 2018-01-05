package ba.unsa.etf.rma.ena.dms_android.adapters;

import android.content.Context;
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

import ba.unsa.etf.rma.ena.dms_android.R;
import ba.unsa.etf.rma.ena.dms_android.classes.Korisnik;

/**
 * Created by Ena on 25.12.2017..
 *
 */

public class KorisnikAdapter extends ArrayAdapter<Korisnik> {

    private boolean[] toggle;
    private Context context;
    private View view;


    public KorisnikAdapter(Context context, int resource, List<Korisnik> korisnici) {
        super(context, resource, korisnici);
        this.context = context;
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
            changeUser.setOnClickListener(v -> {
                Toast.makeText(context, "test change", Toast.LENGTH_SHORT).show();
                Log.i("AAAA", "imageButton_change_user position: " + position);

            });
            deleteUser.setOnClickListener(v -> {
                Toast.makeText(context, "test delete", Toast.LENGTH_SHORT).show();
                Log.i("AAAA", "imageButton_delete_user position: " + position);
            });


        }
        return view;
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
