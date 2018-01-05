package ba.unsa.etf.rma.ena.dms_android.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import ba.unsa.etf.rma.ena.dms_android.R;
import ba.unsa.etf.rma.ena.dms_android.classes.Uloga;

/**
 * Created by Ena on 04.01.2018..
 *
 */

public class UlogaAdapter extends ArrayAdapter<Uloga> {

    private Context context;
    private boolean[] toggle;

    public UlogaAdapter(Context context, int resource, List<Uloga> uloge) {
        super(context, resource, uloge);
        this.context = context;
        toggle = new boolean[uloge.size()];
        fillToggle(uloge.size());
    }

    private void fillToggle(int size) {
        for (int i= 0; i<size; i++){
            toggle[i] = true;
        }
    }

    @Override
    public View getView(final int position, View convertView, @NonNull ViewGroup parent) {

        View view = convertView;

        if (view == null) {
            LayoutInflater layoutInflater;
            layoutInflater = LayoutInflater.from(getContext());
            view = layoutInflater.inflate(R.layout.layout_uloga_list_item, parent,false);
        }

        final Uloga uloga = getItem(position);

        if (uloga != null) {

            TextView naziv = (TextView) view.findViewById(R.id.textView_naziv_role_u);

            if (naziv != null) {    naziv.setText(uloga.getNaziv());}

            View buttonView = view.findViewById(R.id.buttons_layout_role);
            RelativeLayout listElementUloga = (RelativeLayout) view.findViewById(R.id.list_element_uloga);
            listElementUloga.setOnClickListener(v -> showHideOptions(uloga, position, buttonView));

            ImageButton changeRole = (ImageButton) view.findViewById(R.id.imageButton_edit_role);
            ImageButton deleteUser = (ImageButton) view.findViewById(R.id.imageButton_delete_role);
            changeRole.setOnClickListener(v -> {
                Toast.makeText(context, "test change", Toast.LENGTH_SHORT).show();
                Log.i("AAAA", "imageButton_change_user position: " + position);

            });
            deleteUser.setOnClickListener(v -> {
                Toast.makeText(context, "test delete", Toast.LENGTH_SHORT).show();
                Log.i("AAAA", "imageButton_delete_user position: " + position);
            });
        }

        return view;
    }

    private void showHideOptions(Uloga uloga, int toggleIndex, View buttonView) {
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
