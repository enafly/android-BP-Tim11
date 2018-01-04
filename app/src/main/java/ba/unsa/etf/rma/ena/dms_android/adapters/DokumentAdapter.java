package ba.unsa.etf.rma.ena.dms_android.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import ba.unsa.etf.rma.ena.dms_android.R;
import ba.unsa.etf.rma.ena.dms_android.classes.Dokument;

/**
 * Created by Ena on 04.01.2018..
 *
 */

public class DokumentAdapter  extends ArrayAdapter<Dokument> {

    private boolean[] toggle;
    private Context context;

    public DokumentAdapter(Context context, int resource, List<Dokument> korisnici) {
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
            view = layoutInflater.inflate(R.layout.layout_document_list_item, parent,false);
        }

        final Dokument dokument = getItem(position);

        if (dokument != null) {

            ImageView icon = (ImageView) view.findViewById(R.id.imageView_icon_doc);
            TextView ime = (TextView) view.findViewById(R.id.textView_naziv_doc_u);
            TextView prezime = (TextView) view.findViewById(R.id.textView_vlasnik_doc_u);
            TextView korisnickoIme = (TextView) view.findViewById(R.id.textView_file_doc_u);

            icon.setImageResource(R.drawable.add_document);

            if (ime != null) {    ime.setText(dokument.getNaziv());}
            if (prezime != null) {  prezime.setText(dokument.getVlasnik()); }
            if (korisnickoIme != null) {  korisnickoIme.setText(dokument.getFajl());    }

        }

        return view;
    }



}
