package ba.unsa.etf.rma.ena.dms_android.views;

import android.widget.TextView;

import ba.unsa.etf.rma.ena.dms_android.MainActivity;
import ba.unsa.etf.rma.ena.dms_android.R;

/**
 * Created by Ena on 25.12.2017..
 *
 */

public class UlogaManager {
    private MainActivity activity;

    public UlogaManager(MainActivity mainActivity) {
        activity = mainActivity;
        TextView tekst = (TextView) activity.findViewById(R.id.textViewU);
        tekst.setText(R.string.roles_uloge);
    }
}
