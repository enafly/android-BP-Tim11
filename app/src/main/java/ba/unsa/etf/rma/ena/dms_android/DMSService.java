package ba.unsa.etf.rma.ena.dms_android;

import com.google.gson.JsonObject;

import java.util.List;

import ba.unsa.etf.rma.ena.dms_android.classes.Korisnik;
import ba.unsa.etf.rma.ena.dms_android.classes.Uloga;
import ba.unsa.etf.rma.ena.dms_android.model.LoginModel;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;


/**
 * Created by Ena & Elvedin on 08.01.2018..
 *
 */

public interface DMSService {

    @GET("korisniciandroid")
    Call<List<Korisnik>> listaKorisnika();

    @POST("loginandroid")
    Call<JsonObject> login(@Body LoginModel loginModel);

    @GET("ulogeandroid")
    Call<List<Uloga>> listaUloga();


}