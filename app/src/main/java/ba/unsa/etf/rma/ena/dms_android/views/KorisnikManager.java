package ba.unsa.etf.rma.ena.dms_android.views;

import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import ba.unsa.etf.rma.ena.dms_android.MainActivity;
import ba.unsa.etf.rma.ena.dms_android.R;

/**
 * Created by Ena on 25.12.2017..
 *
 */

public class KorisnikManager {
    private MainActivity activity;

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

        //TODO KORISNIKADAPTER ON listView
        ListView lista = (ListView) activity.findViewById(R.id.listView_korisnici);

    }
}
