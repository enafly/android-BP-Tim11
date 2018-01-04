package ba.unsa.etf.rma.ena.dms_android.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
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
    ImageButton changeUser;
    ImageButton deleteUser;

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

        View view = convertView;

        if (view == null) {
            LayoutInflater layoutInflater;
            layoutInflater = LayoutInflater.from(getContext());
            view = layoutInflater.inflate(R.layout.layout_user_list_item, parent,false);
        }

        final Korisnik korisnik = getItem(position);

        if (korisnik != null) {
            RelativeLayout listElementKorisnik = (RelativeLayout) view.findViewById(R.id.list_element_korisnik);
            listElementKorisnik.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showHideOptions(korisnik, position);
                }
            });
            changeUser = (ImageButton) view.findViewById(R.id.imageButton_edit_doc);
            //changeUser.setVisibility(View.INVISIBLE);

            deleteUser = (ImageButton) view.findViewById(R.id.imageButton_delete_user);
            //deleteUser.setVisibility(View.INVISIBLE);

            changeImageButtonsVisibility(View.GONE);

            ImageView icon = (ImageView) view.findViewById(R.id.imageView_icon);
            TextView ime = (TextView) view.findViewById(R.id.textView_ime_u);
            TextView prezime = (TextView) view.findViewById(R.id.textView_vlasnik_doc_u);
            TextView korisnickoIme = (TextView) view.findViewById(R.id.textView_korisnicko_ime_u);
            TextView sifra = (TextView) view.findViewById(R.id.textView_sifra_u);

            icon.setImageResource(R.drawable.user);

            if (ime != null) {    ime.setText(korisnik.getIme());}
            if (prezime != null) {  prezime.setText(korisnik.getPrezime()); }
            if (korisnickoIme != null) {  korisnickoIme.setText(korisnik.getKorisnickoIme());    }
            if (sifra != null) {  sifra.setText(korisnik.getSifra());    }

        }

        return view;
    }

    private void changeImageButtonsVisibility(int visiblity) {
        changeUser.setVisibility(visiblity);
        changeUser.setImageResource(R.drawable.change_user);
        deleteUser.setVisibility(visiblity);
        deleteUser.setImageResource(R.drawable.delete_user);
    }

    private void showHideOptions(Korisnik korisnik, int toggleIndex) {
       Log.i("AAAAAAAAA","showHideOptions");
        if (toggle[toggleIndex]) {
            toggle[toggleIndex] = false;
            changeImageButtonsVisibility(View.VISIBLE);
            actionsOnButtons();
        } else {
            if(changeUser!= null && deleteUser!= null){
                changeImageButtonsVisibility(View.GONE);
            }
            toggle[toggleIndex] = true;
       }
    }

    private void actionsOnButtons() {
        changeUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "test change", Toast.LENGTH_SHORT).show();
                Log.i("AAAA", "imageButton_change_user");

            }
        });
        deleteUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "test delete", Toast.LENGTH_SHORT).show();
                Log.i("AAAA", "imageButton_delete_user");
            }
        });
    }
}
