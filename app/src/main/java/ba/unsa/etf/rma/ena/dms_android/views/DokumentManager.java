package ba.unsa.etf.rma.ena.dms_android.views;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import ba.unsa.etf.rma.ena.dms_android.DMSService;
import ba.unsa.etf.rma.ena.dms_android.MainActivity;
import ba.unsa.etf.rma.ena.dms_android.R;
import ba.unsa.etf.rma.ena.dms_android.Utils;
import ba.unsa.etf.rma.ena.dms_android.activities.AddDokumentActivity;
import ba.unsa.etf.rma.ena.dms_android.adapters.DokumentAdapter;
import ba.unsa.etf.rma.ena.dms_android.classes.Dokument;
import ba.unsa.etf.rma.ena.dms_android.classes.LoggedIn;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Ena on 25.12.2017..
 *
 */

public class DokumentManager {
    private MainActivity activity;
    private ArrayList<Dokument> dokumenti = new ArrayList<>();
    private View view;
    private LoggedIn loggedIn;

    public DokumentManager(MainActivity mainActivity) {
        activity = mainActivity;
        loggedIn = activity.getLoggedIn();
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

        dokumenti.add(new Dokument(1,"naziv",1));
        setDokumenteListu();
        
        //setDokumente();
        // get data from the table by the ListAdapter
    }

    private void setDokumente() {
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(Utils.URL)
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();

        DMSService dmsService = retrofit.create(DMSService.class);
        Call<List<JsonObject>> dokumentiDobijeni = dmsService.sviDokumentiUsera(loggedIn.getId());

        dokumentiDobijeni.enqueue(new Callback<List<JsonObject>>() {
            @Override
            public void onResponse(Call<List<JsonObject>> call, Response<List<JsonObject>> response) {
                List<JsonObject> odg = response.body();
                if(odg != null){
                    for(int i=0; i<odg.size();i++){
                        dokumenti.add(new Dokument(odg.get(i).get("id").getAsInt(),odg.get(i).get("naziv").getAsString(),odg.get(i).get("vlasnik").getAsInt()));
                    }

                }
                Log.i("onResponse", odg.toString());

                //List<Dokument> dokumentiA = response.body();
                //setContent();
//                Log.i("AAAA", "Dokumenti " + dokumentiA);

                //dokumenti.addAll(dokumentiA);
                Log.i("AAAA", "Dokumenti " + dokumenti.get(1).getNaziv());
                setDokumenteListu();
            }

            @Override
            public void onFailure(Call<List<JsonObject>> call, Throwable t) {
                Log.i("AAa", "Nesto nije okej:  " + t.toString());

            }

        });


    }

    private void setDokumenteListu() {
        ListView listaDokumenata = (ListView) view.findViewById(R.id.listView_dokumenti);

        DokumentAdapter dokumentAdapter = new DokumentAdapter(view.getContext(), R.layout.layout_document_list_item, dokumenti);
        listaDokumenata.setAdapter(dokumentAdapter);
    }
}
