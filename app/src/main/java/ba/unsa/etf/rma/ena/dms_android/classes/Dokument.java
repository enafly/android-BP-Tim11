package ba.unsa.etf.rma.ena.dms_android.classes;

import java.io.InputStream;

/**
 * Created by Ena on 25.12.2017..
 *
 */

public class Dokument {
    private int id;
    private String naziv;
    private InputStream fajl;
    private int vlasnik;
    private Integer vidljivost;

    private String contentType;
    private String extenzija;


    public Dokument(int id, String naziv, int vlasnik, InputStream fajl) {
        this.id = id;
        this.naziv = naziv;
        this.vlasnik = vlasnik;
        this.fajl = fajl;
    }

    public Dokument(int id, String naziv, int vlasnik) {
        this.id = id;
        this.naziv = naziv;
        this.vlasnik = vlasnik;
    }

    public int getId() {
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

    public void setFajl(InputStream fajl) {
        this.fajl = fajl;
    }

    public int getVlasnik() {
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
}
