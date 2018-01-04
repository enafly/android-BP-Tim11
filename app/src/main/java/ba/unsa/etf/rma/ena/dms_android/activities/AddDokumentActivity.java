package ba.unsa.etf.rma.ena.dms_android.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import ba.unsa.etf.rma.ena.dms_android.R;

public class AddDokumentActivity extends AppCompatActivity {

    TextView nazivFilea;
    Spinner listaVlasnika;
    Button addFile;
    Button addDokument;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_dokument);

        //TODO change or add
        nazivFilea = (TextView) findViewById(R.id.textView_ime_doc_add);

        //TODO spinner

        addFile = (Button) findViewById(R.id.button_add_file);
        addFile.setOnClickListener(view-> dodajFile());

        addDokument = (Button) findViewById(R.id.button_add_dokument);
        addDokument.setOnClickListener(view-> dodajDokument());

        //TODO validations

    }

    private void dodajDokument() {

    }

    private void dodajFile() {

    }
}
