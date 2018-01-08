package ba.unsa.etf.rma.ena.dms_android.views;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ba.unsa.etf.rma.ena.dms_android.DMSService;
import ba.unsa.etf.rma.ena.dms_android.MainActivity;
import ba.unsa.etf.rma.ena.dms_android.R;
import ba.unsa.etf.rma.ena.dms_android.activities.AddUlogaActivity;
import ba.unsa.etf.rma.ena.dms_android.adapters.UlogaAdapter;
import ba.unsa.etf.rma.ena.dms_android.classes.Uloga;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Ena on 25.12.2017..
 *
 */

public class UlogaManager {

    private String url= "http://192.168.0.11:12224/dms/";
    private View view;
    private MainActivity activity;
    private ArrayList<Uloga> uloge = new ArrayList<>();

    public UlogaManager(MainActivity mainActivity) {
        activity = mainActivity;
        setContents();
    }

    private void setContents() {
        view = LayoutInflater.from(activity.getApplicationContext()).inflate(R.layout.layout_roles, null, false);
        TextView tekst = (TextView) view.findViewById(R.id.textView_uloge);
        tekst.setText(R.string.doc_dokumenti);

        //TODO action on IMageView
        ImageButton addUlogeButton = (ImageButton) activity.findViewById(R.id.imageButton_add_uloge);
        addUlogeButton.setImageResource(R.drawable.add_role);
        addUlogeButton.setOnClickListener(v -> {
            Toast.makeText(activity, "test add", Toast.LENGTH_SHORT).show();
            Intent addUlogu = new Intent(activity.getApplicationContext(), AddUlogaActivity.class);
            activity.startActivity(addUlogu);
        });

        getUloge();

    }

    public void getUloge() {

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();

        DMSService dmsService = retrofit.create(DMSService.class);
        Call<List<Uloga>> ulogeDobijeni = dmsService.listaUloga();

        ulogeDobijeni.enqueue(new Callback<List<Uloga>>() {
            @Override
            public void onResponse(Call<List<Uloga>> call, Response<List<Uloga>> response) {
                List<Uloga> ulogeA = response.body();
                uloge.addAll(ulogeA);
                Log.i("AAAA", "Uloge "+ uloge.get(1).getNaziv() );
                setListView();
            }

            @Override
            public void onFailure(Call<List<Uloga>> call, Throwable t) {
                Log.i("AAa", "Nesto nije okej:  " + t.toString());
            }
        });
    }

    private void setListView() {
        ListView listaUloga = (ListView) activity.findViewById(R.id.listView_uloge);
        UlogaAdapter ulogaAdapter = new UlogaAdapter(view.getContext(), R.layout.layout_uloga_list_item, uloge);
        listaUloga.setAdapter(ulogaAdapter);
    }
}
