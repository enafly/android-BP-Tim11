package ba.unsa.etf.rma.ena.dms_android.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import ba.unsa.etf.rma.ena.dms_android.R;
import ba.unsa.etf.rma.ena.dms_android.classes.Korisnik;

/**
 * Created by Ena on 25.12.2017..
 *
 */

public class KorisnikAdapter extends ArrayAdapter<Korisnik> {

    public KorisnikAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }

    public KorisnikAdapter(Context context, int resource, List<Korisnik> items) {
        super(context, resource, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.layout_user_list_item, null);
        }

        Korisnik korisnik = getItem(position);

        if (korisnik != null) {
            TextView ime = (TextView) v.findViewById(R.id.textView_ime_u);
            TextView prezime = (TextView) v.findViewById(R.id.textView_prezime_u);
            TextView korisnickoIme = (TextView) v.findViewById(R.id.textView_korisnicko_ime_u);
            TextView sifra = (TextView) v.findViewById(R.id.textView_sifra_u);

            if (ime != null) {    ime.setText(korisnik.getIme());   }

            if (prezime != null) {  prezime.setText(korisnik.getPrezime()); }

            if (korisnickoIme != null) {  korisnickoIme.setText(korisnik.getKorisnickoIme());    }

            if (sifra != null) {  sifra.setText(korisnik.getSifra());    }

        }

        return v;
    }

}
