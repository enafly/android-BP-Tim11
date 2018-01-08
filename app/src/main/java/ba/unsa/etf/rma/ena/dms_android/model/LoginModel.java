package ba.unsa.etf.rma.ena.dms_android.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Ena on 08.01.2018..
 *
 */

public class LoginModel {

    @Expose
    @SerializedName("korisnickoIme")
    private String korisnickoIme;
    @Expose
    @SerializedName("sifra")
    private  String sifra;


    public LoginModel(String username, String password) {
        this.korisnickoIme = username;
        this.sifra = password;
    }


    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    public String getSifra() {
        return sifra;
    }

    public void setSifra(String sifra) {
        this.sifra = sifra;
    }

}
