package ba.unsa.etf.rma.ena.dms_android.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ba.unsa.etf.rma.ena.dms_android.R;

public class AddDokumentActivity extends AppCompatActivity {

    TextView nazivFilea;
    Spinner listaVlasnika;
    ImageButton addFile;
    Button addDokument;
    Spinner vlasnici;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_dokument);

        //TODO change or add
        nazivFilea = (TextView) findViewById(R.id.textView_ime_doc_add);

        //TODO spinner
        final List<String> list = new ArrayList<String>();
        list.add("Vlasnik 1");
        list.add("Vlasnik 2");
        list.add("Vlasnik 3");
        list.add("Vlasnik 4");
        list.add("Vlasnik 5");
        vlasnici = (Spinner) findViewById(R.id.dropdown_vlasnik);
        ArrayAdapter<String> adp1 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);
        adp1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        vlasnici.setAdapter(adp1);

        vlasnici.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
                // TODO Auto-generated method stub
                Toast.makeText(getBaseContext(), list.get(position), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });


        addFile = (ImageButton) findViewById(R.id.button_add_file);
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
