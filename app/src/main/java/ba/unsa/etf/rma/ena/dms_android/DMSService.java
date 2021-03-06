package ba.unsa.etf.rma.ena.dms_android;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.List;

import ba.unsa.etf.rma.ena.dms_android.classes.Dokument;
import ba.unsa.etf.rma.ena.dms_android.classes.Korisnik;
import ba.unsa.etf.rma.ena.dms_android.classes.Uloga;
import ba.unsa.etf.rma.ena.dms_android.model.LoginModel;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Url;


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

    @POST("dokumentiandroid")
    Call<JsonArray> sviDokumentiUsera(@Body Integer id);

    @GET
    Call<ResponseBody> downloadDokument(@Url String url);

    //Brisi
    @POST("brisiuloguandroid")
    Call<Void> deleteRole(@Body Integer id);

    @POST("brisikorisnikaandroid")
    Call<Void> deleteKorisnika(@Body Integer id);

    @POST("brisidokumentandroid")
    Call<Void> deleteDokument(@Body Integer id);

    //Dodaj
    @POST("dodajuloguandroid")
    Call<Void> dodajUlogu(@Body Uloga uloga);


    @Multipart
    @POST("dodajdokumentandroid")
    Call<ResponseBody> dodajDokument(
            @Part("id") RequestBody id,
            @Part("naziv") RequestBody naziv,
            @Part("vlasnik") RequestBody vlasnik,
            @Part("vidljivost")RequestBody vidljivost,
            @Part("contentType")RequestBody contentType,
            @Part("extenzija")RequestBody extenzija,
            @Part MultipartBody.Part file
    );

    @POST("dodajkorisnikaandroid")
    Call<Void> dodajKorisnika(@Body Korisnik korisnik);

    //Nadji sa id-em
    @POST("nadjivlasnikasaidemandroid")
    Call<Korisnik> findVlasnikById(@Body Integer id);



}
