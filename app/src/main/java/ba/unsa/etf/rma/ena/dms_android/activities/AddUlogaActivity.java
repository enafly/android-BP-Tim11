package ba.unsa.etf.rma.ena.dms_android.activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import ba.unsa.etf.rma.ena.dms_android.DMSService;
import ba.unsa.etf.rma.ena.dms_android.R;
import ba.unsa.etf.rma.ena.dms_android.Utils;
import ba.unsa.etf.rma.ena.dms_android.classes.Uloga;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AddUlogaActivity extends AppCompatActivity {

    TextView nazivUloge;
    Button addUloga;
    TextView formTitle;

    private int ulogaId;
    private String ulogaNaziv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_uloga);

        //TODO change or add
        formTitle=(TextView) findViewById(R.id.textView_add_uloga);
        nazivUloge = (TextView) findViewById(R.id.textView_naziv_role_add);
        addUloga = (Button) findViewById(R.id.button_add_ulogu);

        Intent fromUlogaAdapter=getIntent();
        if(fromUlogaAdapter.hasExtra("ulogaNaziv")) {
            ulogaId = fromUlogaAdapter.getIntExtra("ulogaId", 0);
            ulogaNaziv=fromUlogaAdapter.getStringExtra("ulogaNaziv");
            formTitle.setText(R.string.izmjena_uloge_str);
            nazivUloge.setText(ulogaNaziv);
            addUloga.setText(R.string.button_izmijeni_ulogu_str);
            addUloga.setOnClickListener(view-> izmijeniUlogu());
        }
        else {
            addUloga.setOnClickListener(view-> dodajUlogu());
        }

        //TODO validations

    }

    private void izmijeniUlogu(){
        ulogaNaziv=nazivUloge.getText().toString();

        nazivUloge.setError(null);

        if(isRoleNameValid(ulogaNaziv)){
            Uloga u=new Uloga(ulogaId,ulogaNaziv);
            callDodajUloguApi(u);
            finish();
        }
        else {
            nazivUloge.setError(getString(R.string.uloga_validation_string));
        }
    }

    private void dodajUlogu() {
        String nazivUlogeString=nazivUloge.getText().toString();

        nazivUloge.setError(null);

        if(isRoleNameValid(nazivUlogeString)){
            Uloga u=new Uloga(0,nazivUlogeString);
            callDodajUloguApi(u);
            finish();
        }
        else {
            nazivUloge.setError(getString(R.string.uloga_validation_string));
        }
    }

    private void callDodajUloguApi(Uloga u) {
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(Utils.URL)
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();

        DMSService dmsService = retrofit.create(DMSService.class);
        Call<Void> dodajUloguCall = dmsService.dodajUlogu(u);

        dodajUloguCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(@NonNull Call<Void> call, @NonNull Response<Void> response) {
                if(response.isSuccessful())
                    Toast.makeText(getApplicationContext(), "Uspješno", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(getApplicationContext(), "Greška", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(@NonNull Call<Void> call, @NonNull Throwable t) {
                Toast.makeText(getApplicationContext(), "Greška", Toast.LENGTH_SHORT).show();
                Log.i("AAa", "Nesto nije okej:  " + t.toString());
            }
        });
    }

    private boolean isRoleNameValid(String roleName) {
        return Utils.matchRegex(roleName,"^[a-zA-Z)]{3,15}$");
    }

}
