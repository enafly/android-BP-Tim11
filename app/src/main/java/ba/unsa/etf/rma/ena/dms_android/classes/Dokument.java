package ba.unsa.etf.rma.ena.dms_android.classes;

import android.os.Build;
import android.support.annotation.RequiresApi;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

/**
 * Created by Ena on 25.12.2017..
 *
 */

public class Dokument {
    private Integer id;
    private String naziv;
    private InputStream fajl;
    private Integer vlasnik;
    private Integer vidljivost;

    private String contentType;
    private String extenzija;


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public Dokument(int id, String naziv, int vlasnik, String fajl, Integer vidljivost, String contentType, String extenzija) {
        this.id = id;
        this.naziv = naziv;
        this.vlasnik = vlasnik;
        this.fajl = contentToInputStream(fajl);
        this.vidljivost=vidljivost;
        this.contentType=contentType;
        this.extenzija=extenzija;
    }

    public Dokument(int id, String naziv, int vlasnik, InputStream fajl, Integer vidljivost, String contentType, String extenzija) {
        this.id = id;
        this.naziv = naziv;
        this.vlasnik = vlasnik;
        this.fajl = fajl;
        this.vidljivost=vidljivost;
        this.contentType=contentType;
        this.extenzija=extenzija;
    }

    public Dokument(int id, String naziv, int vlasnik) {
        this.id=id;
        this.naziv=naziv;
        this.vlasnik=vlasnik;

    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public InputStream getFajl() {
        return fajl;
    }

    public void setFajl(ByteArrayInputStream fajl) {
        this.fajl = fajl;
    }

    public Integer getVlasnik() {
        return vlasnik;
    }

    public void setVlasnik(int vlasnik) {
        this.vlasnik = vlasnik;
    }

    public Integer getVidljivost() {
        return vidljivost;
    }

    public void setVidljivost(Integer vidljivost) {
        this.vidljivost = vidljivost;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getExtenzija() {
        return extenzija;
    }

    public void setExtenzija(String extenzija) {
        this.extenzija = extenzija;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)

    private InputStream contentToInputStream(String s) {
        try {
            return fajl= new ByteArrayInputStream(s.getBytes(StandardCharsets.UTF_8.name()));
        } catch (UnsupportedEncodingException e) {

            e.printStackTrace();
            return null;
        }
    }

}
