package ba.unsa.etf.rma.ena.dms_android.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import ba.unsa.etf.rma.ena.dms_android.DMSService;
import ba.unsa.etf.rma.ena.dms_android.R;
import ba.unsa.etf.rma.ena.dms_android.Utils;
import ba.unsa.etf.rma.ena.dms_android.classes.Dokument;
import ba.unsa.etf.rma.ena.dms_android.classes.Korisnik;
import ba.unsa.etf.rma.ena.dms_android.views.DokumentManager;
import ba.unsa.etf.rma.ena.dms_android.views.KorisnikManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Ena on 04.01.2018..
 *
 */

public class DokumentAdapter  extends ArrayAdapter<Dokument> {

    private boolean[] toggle;
    private Context context;
    private DokumentManager dokumentManager;
    private String vlasnik = "";

    public DokumentAdapter(Context context, int resource, List<Dokument> dokumenti, DokumentManager dokumentManager) {
        super(context, resource, dokumenti);
        this.context = context;
        this.dokumentManager = dokumentManager;
        Log.i("DokumentAdapter", "Dokumenti " + dokumenti);
        toggle = new boolean[dokumenti.size()];
        fillToggle(dokumenti.size());
    }

    private void fillToggle(int size) {
        for (int i= 0; i<size; i++){
            toggle[i] = true;
        }
    }

    @NonNull
    @Override
    public View getView(final int position, View convertView, @NonNull ViewGroup parent) {

        Log.i("getView", "imageButton_view_document position: ");
        View view = convertView;

        if (view == null) {
            LayoutInflater layoutInflater;
            layoutInflater = LayoutInflater.from(getContext());
            view = layoutInflater.inflate(R.layout.layout_document_list_item, parent,false);
        }

        final Dokument dokument = getItem(position);

        if (dokument != null) {

            ImageView icon = (ImageView) view.findViewById(R.id.imageView_icon_doc);
            TextView naziv = (TextView) view.findViewById(R.id.textView_naziv_doc_u);
            TextView vlasnikDokumenta = (TextView) view.findViewById(R.id.textView_vlasnik_doc_u);

            switch (dokument.getExtenzija()){
                case "docx":
                    icon.setImageResource(R.drawable.ic_doc);
                    break;
                case "jpg":
                    icon.setImageResource(R.drawable.ic_jpg);
                    break;
                case "pdf":
                    icon.setImageResource(R.drawable.ic_pdf);
                    break;
                case "png":
                    icon.setImageResource(R.drawable.ic_png);
                    break;
                case "ppt":
                    icon.setImageResource(R.drawable.ic_ppt);
                    break;
                case "txt":
                    icon.setImageResource(R.drawable.ic_txt);
                    break;
                case "xlsx":
                    icon.setImageResource(R.drawable.ic_xls);
                    break;

            }

            View buttonView = view.findViewById(R.id.buttons_layout_doc);
            RelativeLayout listElementDokument = (RelativeLayout) view.findViewById(R.id.list_element_dokument);
            listElementDokument.setOnClickListener(v -> showHideOptions(dokument, position, buttonView));

            ImageButton viewDoc = (ImageButton) view.findViewById(R.id.imageButton_view_document);
            ImageButton editDoc = (ImageButton) view.findViewById(R.id.imageButton_edit_document);
            ImageButton downloadDoc = (ImageButton) view.findViewById(R.id.imageButton_download_document);
            ImageButton deleteDoc = (ImageButton) view.findViewById(R.id.imageButton_delete_document);

            viewDoc.setOnClickListener(v -> {
                Log.i("AAAA", "imageButton_view_document position: " + position);

            });
            editDoc.setOnClickListener(v -> {
                Log.i("AAAA", "imageButton_edit_document position: " + position);

            });
            downloadDoc.setOnClickListener(v -> {
                Log.i("AAAA", "imageButton_download_document position: " + position);

            });
            deleteDoc.setOnClickListener(v -> {
                Log.i("AAAA", "imageButton_delete_document position: " + position);
                callDeleteDokumentApi(dokument.getId());
            });

            if (naziv != null) {    naziv.setText(dokument.getNaziv());}
            if (vlasnikDokumenta != null) {
                vlasnikDokumenta.setText(vlasnik);
            }
        }
        return view;
    }

    private void setVlasnikIme(String ime) {
        vlasnik = ime;
    }

    private void callDeleteDokumentApi(Integer id) {

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(Utils.URL)
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();

        DMSService dmsService = retrofit.create(DMSService.class);
        Call<Void> brisiDokument = dmsService.deleteDokument(id);

        brisiDokument.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(@NonNull Call<Void> call, @NonNull Response<Void> response) {
                dokumentManager.setDokumente();
                Log.i("AAAA", "Dokument obrisan ");
            }

            @Override
            public void onFailure(@NonNull Call<Void> call, @NonNull Throwable t) {
                Log.i("AAa", "Nesto nije okej:  " + t.toString());
            }
        });
    }

    private void showHideOptions(Dokument dokument, int toggleIndex, View buttonView) {
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
