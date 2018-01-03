package ba.unsa.etf.rma.ena.dms_android.views;

import android.content.Intent;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import ba.unsa.etf.rma.ena.dms_android.MainActivity;
import ba.unsa.etf.rma.ena.dms_android.R;
import ba.unsa.etf.rma.ena.dms_android.activities.AddKorisnikActivity;
import ba.unsa.etf.rma.ena.dms_android.activities.LoginActivity;
import ba.unsa.etf.rma.ena.dms_android.adapters.KorisnikAdapter;
import ba.unsa.etf.rma.ena.dms_android.classes.Korisnik;

/**
 * Created by Ena on 25.12.2017..
 *
 */

public class KorisnikManager {

    private MainActivity activity;
    private ArrayList<Korisnik> korisnici = new ArrayList<>();
    private View buttonsLayout;

    public KorisnikManager(MainActivity mainActivity) {
        activity = mainActivity;
        setContent();
    }

    private void setContent() {
        View view = LayoutInflater.from(activity.getApplicationContext()).inflate(R.layout.layout_users, null, false);
        TextView tekst = (TextView) view.findViewById(R.id.textView_korisnici);
        tekst.setText(R.string.user_korisnici);

        //TODO action on IMageView
        ImageButton addUsersButton = (ImageButton) view.findViewById(R.id.imageButton_add_user);
        addUsersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(activity, "test add", Toast.LENGTH_SHORT).show();
                Intent addKorisnik = new Intent(activity.getApplicationContext(), AddKorisnikActivity.class);
                activity.startActivity(addKorisnik);
                activity.finish();
            }
        });

        //TODO KORISNIK ADAPTER ON listView
        ListView listaKorisnika = (ListView) activity.findViewById(R.id.listView_korisnici);
        // get data from the table by the ListAdapter
        korisnici.add(new Korisnik (1,"ime1","prezime1","korisnicko ime1","sifra1"));
        korisnici.add(new Korisnik (2,"ime2","prezime2","korisnicko ime2","sifra2"));
        korisnici.add(new Korisnik (3,"ime3","prezime3","korisnicko ime3","sifra3"));
        korisnici.add(new Korisnik (4,"ime4","prezime4","korisnicko ime4","sifra4"));
        korisnici.add(new Korisnik (5,"ime5","prezime5","korisnicko ime5","sifra5"));

        KorisnikAdapter korisnikAdapter = new KorisnikAdapter(view.getContext(), R.layout.layout_user_list_item, korisnici);
        listaKorisnika.setAdapter(korisnikAdapter);

        buttonsLayout = activity.findViewById(R.id.buttons_layout);
        buttonsLayout.setVisibility(View.INVISIBLE);

        listaKorisnika.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                toggle(position);

            }
        });
    }

    private void toggle(int position) {
        Korisnik korisnik = korisnici.get(position);
        buttonsLayout.setVisibility(View.VISIBLE);
        //Osposobi buttone!
      //  ImageButton changeUser = (ImageButton) activity.findViewById(R.id.imageButton_change_user);
       // ImageButton deleteUser = (ImageButton) activity.findViewById(R.id.imageButton_delete_user);
        //on click listeners and make anoter activities
        activity.findViewById(R.id.imageButton_change_user).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(activity, "test change", Toast.LENGTH_SHORT).show();

            }
        });
        activity.findViewById(R.id.imageButton_delete_user).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(activity, "test delete", Toast.LENGTH_SHORT).show();
            }
        });

    }

}
