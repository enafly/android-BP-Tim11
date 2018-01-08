package ba.unsa.etf.rma.ena.dms_android.views;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import ba.unsa.etf.rma.ena.dms_android.MainActivity;
import ba.unsa.etf.rma.ena.dms_android.R;
import ba.unsa.etf.rma.ena.dms_android.activities.AddDokumentActivity;
import ba.unsa.etf.rma.ena.dms_android.adapters.DokumentAdapter;
import ba.unsa.etf.rma.ena.dms_android.classes.Dokument;

/**
 * Created by Ena on 25.12.2017..
 *
 */

public class DokumentManager {

    private MainActivity activity;
    ArrayList<Dokument> dokumenti = new ArrayList<>();
    View view;

    public DokumentManager(MainActivity mainActivity) {
        activity = mainActivity;
        setContents();
    }

    private void setContents() {
        view = LayoutInflater.from(activity.getApplicationContext()).inflate(R.layout.layout_documents, null, false);
        TextView tekst = (TextView) view.findViewById(R.id.textView_uloge);
        tekst.setText(R.string.doc_dokumenti);

        //TODO action on IMageView
        ImageButton addUsersButton = (ImageButton) activity.findViewById(R.id.imageButton_add_dokument);
        addUsersButton.setImageResource(R.drawable.add_document);
        addUsersButton.setOnClickListener(v -> {
            Toast.makeText(activity, "test add", Toast.LENGTH_SHORT).show();
            Intent addDokument = new Intent(activity.getApplicationContext(), AddDokumentActivity.class);
            activity.startActivity(addDokument);
        });

        //TODO KORISNIK ADAPTER ON listView
        ListView listaDokumenata = (ListView) activity.findViewById(R.id.listView_dokumenti);

        setDokumenteListu();

        // get data from the table by the ListAdapter
        dokumenti.add(new Dokument (1,"ime1","vlasnik1","fajl1"));
        DokumentAdapter dokumentAdapter = new DokumentAdapter(view.getContext(), R.layout.layout_document_list_item, dokumenti);
        listaDokumenata.setAdapter(dokumentAdapter);
    }

    private void setDokumenteListu() {

    }
}
