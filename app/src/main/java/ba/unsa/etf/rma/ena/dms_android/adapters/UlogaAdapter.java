package ba.unsa.etf.rma.ena.dms_android.adapters;

import android.content.Context;
import android.content.Intent;
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

import com.google.gson.JsonObject;

import java.util.List;

import ba.unsa.etf.rma.ena.dms_android.DMSService;
import ba.unsa.etf.rma.ena.dms_android.R;
import ba.unsa.etf.rma.ena.dms_android.Utils;
import ba.unsa.etf.rma.ena.dms_android.activities.AddUlogaActivity;
import ba.unsa.etf.rma.ena.dms_android.classes.Uloga;
import ba.unsa.etf.rma.ena.dms_android.views.UlogaManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Ena on 04.01.2018..
 *
 */

public class UlogaAdapter extends ArrayAdapter<Uloga> {

    private Context context;
    private boolean[] toggle;
    private UlogaManager um;
    public UlogaAdapter(Context context, int resource, List<Uloga> uloge, UlogaManager ulogaManager) {
        super(context, resource, uloge);
        this.context = context;
        toggle = new boolean[uloge.size()];
        fillToggle(uloge.size());
        um=ulogaManager;
    }

    private void fillToggle(int size) {
        for (int i= 0; i<size; i++){
            toggle[i] = true;
        }
    }

    @NonNull
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
            ImageButton deleteRole = (ImageButton) view.findViewById(R.id.imageButton_delete_role);
            changeRole.setOnClickListener(v -> {
                Intent addUlogu = new Intent(context.getApplicationContext(), AddUlogaActivity.class);
                addUlogu.putExtra("ulogaId",uloga.getId());
                addUlogu.putExtra("ulogaNaziv",uloga.getNaziv());
                addUlogu.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(addUlogu);
                Log.i("AAAA", "imageButton_change_user position: " + position);

            });
            deleteRole.setOnClickListener(v -> {
                callDeleteRoleApi(uloga.getId());
                Log.i("AAAA", "imageButton_delete_user position: " + position);
            });
        }

        return view;
    }

    private void callDeleteRoleApi(int id) {
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(Utils.URL)
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();

        DMSService dmsService = retrofit.create(DMSService.class);
        Call<Void> brisiUlogu = dmsService.deleteRole(id);

        brisiUlogu.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(@NonNull Call<Void> call, @NonNull Response<Void> response) {

                Toast.makeText(context, "Uloga obrisana", Toast.LENGTH_SHORT).show();
                um.getUloge();
                Log.i("AAAA", "Uloga brisanje ");
            }

            @Override
            public void onFailure(@NonNull Call<Void> call, @NonNull Throwable t) {
                Log.i("AAa", "Nesto nije okej:  " + t.toString());
            }
        });
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
