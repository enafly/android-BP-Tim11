package ba.unsa.etf.rma.ena.dms_android.views;

import android.text.Layout;
import android.view.View;
import android.widget.TextView;

import ba.unsa.etf.rma.ena.dms_android.MainActivity;
import ba.unsa.etf.rma.ena.dms_android.R;

/**
 * Created by Ena on 25.12.2017..
 *
 */

public class DokumentManager {

    private MainActivity activity;

    public DokumentManager(MainActivity mainActivity) {
        activity = mainActivity;
        TextView tekst = (TextView) activity.findViewById(R.id.textView2);
        tekst.setText(R.string.doc_dokumenti);
    }
}
