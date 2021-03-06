package ba.unsa.etf.rma.ena.dms_android.views;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.io.InputStream;
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
    String vlasnikIme;
    int id = 0;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public DokumentManager(MainActivity mainActivity) {
        activity = mainActivity;
        loggedIn = activity.getLoggedIn();
        id= loggedIn.getId();
        setContents();
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public DokumentManager(MainActivity activity, int korisnikId) {
        this.activity = activity;
        loggedIn = activity.getLoggedIn();
        id = korisnikId;
        setContents();
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void setContents() {
        view = LayoutInflater.from(activity.getApplicationContext()).inflate(R.layout.layout_documents, null, false);
        TextView tekst = (TextView) view.findViewById(R.id.textView_uloge);
        tekst.setText(R.string.doc_dokumenti);


        //TODO action on IMageView
        ImageButton addDocumentButton = (ImageButton) activity.findViewById(R.id.imageButton_add_dokument);
        addDocumentButton.setImageResource(R.drawable.add_document);
        addDocumentButton.setOnClickListener(v -> {
            Intent addDokument = new Intent(activity.getApplicationContext(), AddDokumentActivity.class);
            addDokument.putExtra("id",id);
            activity.startActivity(addDokument);
        });

        setDokumente();
    }

    public void setDokumente() {
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(Utils.URL)
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();

        DMSService dmsService = retrofit.create(DMSService.class);
        Call<JsonArray> dokumentiDobijeni = dmsService.sviDokumentiUsera(id);

        dokumentiDobijeni.enqueue(new Callback<JsonArray>() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onResponse(@NonNull Call<JsonArray> call, @NonNull Response<JsonArray> response) {
                JsonArray odg = response.body();
                dokumenti.clear();
                Integer id;
                String naziv;
                String fajl;
                Integer vlasnik;
                Integer vidljivost;
                String contentType;
                String extenzija;

                if(odg != null){
                    for(int i=0;i<odg.size();i++){
                       id= odg.get(i).getAsJsonObject().get("id").getAsInt();
                       naziv=odg.get(i).getAsJsonObject().get("naziv").getAsString();
                       fajl=odg.get(i).getAsJsonObject().get("fajl").getAsString();
                       vlasnik=odg.get(i).getAsJsonObject().get("vlasnik").getAsInt();
                       vidljivost=odg.get(i).getAsJsonObject().get("vidljivost").getAsInt();
                       contentType=odg.get(i).getAsJsonObject().get("contentType").getAsString();
                       extenzija=odg.get(i).getAsJsonObject().get("extenzija").getAsString();
                       Dokument dokument=new Dokument(id,naziv,vlasnik,fajl,vidljivost,contentType,extenzija);
                       dokumenti.add(dokument);
                    }
                    Log.i("Odg to string", odg.toString());
                }

                Log.i("AAAA", "Dokumenti " + dokumenti);
                setDokumenteListu(dokumenti);
            }

            @Override
            public void onFailure(Call<JsonArray> call, Throwable t) {
                t.printStackTrace();
                Log.i("AAa", "Nesto nije okej:  " + t.toString());
            }
        });
    }

    private void setDokumenteListu(List<Dokument> dokumenti) {
        ListView listaDokumenata = (ListView) activity.findViewById(R.id.listView_dokumenti);

        Log.i("setDokumenteListu", "Dokumenti " + dokumenti);

        DokumentAdapter dokumentAdapter = new DokumentAdapter(view.getContext(), R.layout.layout_document_list_item, dokumenti, this);
        listaDokumenata.setAdapter(dokumentAdapter);
    }

    private String getVlasnikIme() {
        return vlasnikIme;
    }

}
