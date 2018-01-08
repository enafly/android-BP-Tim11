package ba.unsa.etf.rma.ena.dms_android.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import ba.unsa.etf.rma.ena.dms_android.R;

public class AddUlogaActivity extends AppCompatActivity {

    TextView nazivUloge;
    Button addUloga;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_uloga);

        //TODO change or add
        nazivUloge = (TextView) findViewById(R.id.textView_naziv_role_add);


        addUloga = (Button) findViewById(R.id.button_add_ulogu);
        addUloga.setOnClickListener(view-> dodajUlogu());

        //TODO validations

    }

    private void dodajUlogu() {

    }
}
