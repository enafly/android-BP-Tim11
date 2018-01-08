package ba.unsa.etf.rma.ena.dms_android.classes;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by Ena on 08.01.2018..
 *
 */

public class LoggedIn implements Parcelable{

    private String ime;
    private String prezime;
    private String korisnickoIme;
    private int uloga;

    public LoggedIn(String ime, String prezime, String korisnickoIme, int uloga) {
        this.ime = ime;
        this.prezime = prezime;
        this.korisnickoIme = korisnickoIme;
        this.uloga = uloga;
    }

    private LoggedIn(Parcel in) {
        ime = in.readString();
        prezime = in.readString();
        korisnickoIme = in.readString();
        uloga = in.readInt();
    }

    public static final Creator<LoggedIn> CREATOR = new Creator<LoggedIn>() {
        @Override
        public LoggedIn createFromParcel(Parcel in) {
            return new LoggedIn(in);
        }

        @Override
        public LoggedIn[] newArray(int size) {
            return new LoggedIn[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(ime);
        dest.writeString(prezime);
        dest.writeString(korisnickoIme);
        dest.writeInt(uloga);
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    public int getUloga() {
        return uloga;
    }

    public void setUloga(int uloga) {
        this.uloga = uloga;
    }
}