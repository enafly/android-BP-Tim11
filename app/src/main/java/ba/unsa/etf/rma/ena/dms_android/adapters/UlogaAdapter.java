package ba.unsa.etf.rma.ena.dms_android.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import ba.unsa.etf.rma.ena.dms_android.R;
import ba.unsa.etf.rma.ena.dms_android.classes.Uloga;

/**
 * Created by Ena on 04.01.2018..
 */

public class UlogaAdapter extends ArrayAdapter<Uloga> {

    private Context context;

    public UlogaAdapter(Context context, int resource, List<Uloga> korisnici) {
        super(context, resource, korisnici);
        this.context = context;
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        View view = convertView;

        if (view == null) {
            LayoutInflater layoutInflater;
            layoutInflater = LayoutInflater.from(getContext());
            view = layoutInflater.inflate(R.layout.layout_uloga_list_item, parent,false);
        }

        final Uloga uloga = getItem(position);

        if (uloga != null) {

            ImageButton addUloga = (ImageButton) view.findViewById(R.id.imageView_icon_role_change);
            TextView naziv = (TextView) view.findViewById(R.id.textView_naziv_role_u);

            addUloga.setOnClickListener(vieww -> changeUloga());

            if (naziv != null) {    naziv.setText(uloga.getNaziv());}

        }

        return view;
    }

    private void changeUloga() {
        Toast.makeText(context,"test",Toast.LENGTH_SHORT).show();
    }
}
