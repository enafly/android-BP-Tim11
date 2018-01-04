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
import ba.unsa.etf.rma.ena.dms_android.activities.AddUlogaActivity;
import ba.unsa.etf.rma.ena.dms_android.adapters.UlogaAdapter;
import ba.unsa.etf.rma.ena.dms_android.classes.Uloga;

/**
 * Created by Ena on 25.12.2017..
 *
 */

public class UlogaManager {

    private MainActivity activity;
    private ArrayList<Uloga> uloge = new ArrayList<>();

    public UlogaManager(MainActivity mainActivity) {
        activity = mainActivity;
        setContents();
    }

    private void setContents() {
        View view = LayoutInflater.from(activity.getApplicationContext()).inflate(R.layout.layout_roles, null, false);
        TextView tekst = (TextView) view.findViewById(R.id.textView_uloge);
        tekst.setText(R.string.doc_dokumenti);

        //TODO action on IMageView
        ImageButton addUlogeButton = (ImageButton) activity.findViewById(R.id.imageButton_add_uloge);
        addUlogeButton.setImageResource(R.drawable.roles);
        addUlogeButton.setOnClickListener(v -> {
            Toast.makeText(activity, "test add", Toast.LENGTH_SHORT).show();
            Intent addUlogu = new Intent(activity.getApplicationContext(), AddUlogaActivity.class);
            activity.startActivity(addUlogu);
        });

        //TODO KORISNIK ADAPTER ON listView
        ListView listaUloga = (ListView) activity.findViewById(R.id.listView_uloge);
        // get data from the table by the ListAdapter
        uloge.add(new Uloga (1,"naziv1"));
        uloge.add(new Uloga (2,"naziv2"));
        uloge.add(new Uloga (3,"naziv3"));
        UlogaAdapter ulogaAdapter = new UlogaAdapter(view.getContext(), R.layout.layout_uloga_list_item, uloge);
        listaUloga.setAdapter(ulogaAdapter);
    }
}
