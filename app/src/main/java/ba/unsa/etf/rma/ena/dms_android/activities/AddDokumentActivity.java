package ba.unsa.etf.rma.ena.dms_android.activities;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.commons.io.FileSystemUtils;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URISyntaxException;

import ba.unsa.etf.rma.ena.dms_android.DMSService;
import ba.unsa.etf.rma.ena.dms_android.R;
import ba.unsa.etf.rma.ena.dms_android.Utils;
import ba.unsa.etf.rma.ena.dms_android.classes.Dokument;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class AddDokumentActivity extends Activity {

    private TextView nazivFilea;
    private ImageButton addFile;
    private Button addDokument;
    private Spinner vlasnici;
    InputStream inputStream;
    String fileExtension;
    String mimeType;
    int idVlasnika;
    private Uri uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_dokument);

        Intent addDocument = getIntent();
        idVlasnika = addDocument.getIntExtra("id",0);

        nazivFilea = (TextView) findViewById(R.id.textView_ime_doc_add);

        addFile = (ImageButton) findViewById(R.id.button_add_file);
        addFile.setOnClickListener(view-> dodajFile());

        addDokument = (Button) findViewById(R.id.button_add_dokument);
        addDokument.setOnClickListener(view-> dodajDokument());

        //TODO validations

    }

    private void dodajDokument() {
        Log.i("AAA", "Api call dokument");

        Dokument dokument = new Dokument(0,nazivFilea.getText().toString(),idVlasnika,inputStream,0, mimeType,fileExtension);
        callDodajDokumentApi(dokument);
        //finish();
    }

    private void dodajFile() {
        performFileSearch();
        Log.i("AAA", "Uploadovan file");
    }
    private static final int READ_REQUEST_CODE = 42;

    /**
     * Fires an intent to spin up the "file chooser" UI and select an image.
     */
    public void performFileSearch() {
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);

        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("*/*");

        startActivityForResult(intent, READ_REQUEST_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent resultData) {
        if (requestCode == READ_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            uri = null;
            if (resultData != null) {
                uri = resultData.getData();
                addFile.setImageResource(R.drawable.upload_document_ok);
                ContentResolver contentResolver = getApplicationContext().getContentResolver();
                MimeTypeMap mime = MimeTypeMap.getSingleton();

                fileExtension = mime.getExtensionFromMimeType(contentResolver.getType(uri));
                mimeType = mime.getMimeTypeFromExtension(fileExtension);
                Log.i("AAA", "fileExtension " + fileExtension + " mimeType " + mimeType);
                try {
                    inputStream = getContentResolver().openInputStream(uri);

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

                Log.i("AAAA", "Uri: " + uri.getPathSegments().get(0));
                //showImage(uri);
            }
        }
    }

    private void callDodajDokumentApi(Dokument dokument) {
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(Utils.URL)
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();

        String filePath="";
        try {
            filePath=PathUtil.getPath(this,uri);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        File file = new File(filePath);


        // create RequestBody instance from file
        RequestBody requestFile =
                RequestBody.create(
                        MediaType.parse(getContentResolver().getType(uri)),
                        file
                );

        // MultipartBody.Part is used to send also the actual file name
        MultipartBody.Part body =
                MultipartBody.Part.createFormData("fajl", file.getName(), requestFile);

        RequestBody id = RequestBody.create(okhttp3.MultipartBody.FORM, String.valueOf(dokument.getId()));
        RequestBody naziv = RequestBody.create(okhttp3.MultipartBody.FORM, dokument.getNaziv());
        RequestBody vlasnik = RequestBody.create(okhttp3.MultipartBody.FORM, String.valueOf(dokument.getVlasnik()));
        RequestBody vidljivost = RequestBody.create(okhttp3.MultipartBody.FORM, String.valueOf(dokument.getVidljivost()));
        RequestBody contentType = RequestBody.create(okhttp3.MultipartBody.FORM, dokument.getContentType());
        RequestBody extenzija = RequestBody.create(okhttp3.MultipartBody.FORM, dokument.getExtenzija());

        DMSService dmsService = retrofit.create(DMSService.class);
        Call<ResponseBody> dodajDokumentCall = dmsService.dodajDokument(id,naziv,vlasnik,vidljivost,contentType,extenzija,body);

        dodajDokumentCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                if(response.isSuccessful()) {
                    Log.i("AAA", "Uspješno");
                    Toast.makeText(getApplicationContext(), "Uspješno dodan dokument", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(getApplicationContext(), "Greška u dodavanju dokumenta", Toast.LENGTH_SHORT).show();
                    Log.i("AAA","Greška");
                }

            }

            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
                Toast.makeText(getApplicationContext(), "Greška u dodavanju dokumenta", Toast.LENGTH_SHORT).show();
                t.printStackTrace();
                Log.i("AAa", "Nesto nije okej:  " + t.toString());
            }
        });
        finish();
    }
}
