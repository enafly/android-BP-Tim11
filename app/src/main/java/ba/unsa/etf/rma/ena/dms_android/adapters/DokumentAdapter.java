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
import ba.unsa.etf.rma.ena.dms_android.classes.Dokument;

/**
 * Created by Ena on 04.01.2018..
 *
 */

public class DokumentAdapter  extends ArrayAdapter<Dokument> {

    private boolean[] toggle;
    private Context context;

    public DokumentAdapter(Context context, int resource, List<Dokument> dokumenti) {
        super(context, resource, dokumenti);
        this.context = context;
        toggle = new boolean[dokumenti.size()];
        fillToggle(dokumenti.size());
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
            TextView naziv = (TextView) view.findViewById(R.id.textView_naziv_doc_u);
            TextView vlasnik = (TextView) view.findViewById(R.id.textView_vlasnik_u);
           // TextView file = (TextView) view.findViewById(R.id.textView_file_doc_u);

            //TODO change by type
            icon.setImageResource(R.drawable.ic_doc);

            View buttonView = view.findViewById(R.id.buttons_layout_doc);
            RelativeLayout listElementDokument = (RelativeLayout) view.findViewById(R.id.list_element_dokument);
            listElementDokument.setOnClickListener(v -> showHideOptions(dokument, position, buttonView));

            ImageButton viewDoc = (ImageButton) view.findViewById(R.id.imageButton_view_document);
            ImageButton editDoc = (ImageButton) view.findViewById(R.id.imageButton_edit_document);
            ImageButton downloadDoc = (ImageButton) view.findViewById(R.id.imageButton_download_document);
            ImageButton deleteDoc = (ImageButton) view.findViewById(R.id.imageButton_delete_document);

            viewDoc.setOnClickListener(v -> {
                Toast.makeText(context, "test change", Toast.LENGTH_SHORT).show();
                Log.i("AAAA", "imageButton_view_document position: " + position);

            });
            editDoc.setOnClickListener(v -> {
                Toast.makeText(context, "test change", Toast.LENGTH_SHORT).show();
                Log.i("AAAA", "imageButton_edit_document position: " + position);

            });
            downloadDoc.setOnClickListener(v -> {
                Toast.makeText(context, "test change", Toast.LENGTH_SHORT).show();
                Log.i("AAAA", "imageButton_download_document position: " + position);

            });
            deleteDoc.setOnClickListener(v -> {
                Toast.makeText(context, "test delete", Toast.LENGTH_SHORT).show();
                Log.i("AAAA", "imageButton_delete_document position: " + position);
            });

            if (naziv != null) {    naziv.setText(dokument.getNaziv());}
            if (vlasnik != null) {  vlasnik.setText(dokument.getVlasnik()); }
            //if (file != null) {  file.setText(dokument.getFajl());    }

        }
        return view;
    }

    private void showHideOptions(Dokument dokument, int toggleIndex, View buttonView) {
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
