package ba.unsa.etf.rma.ena.dms_android.classes;

/**
 * Created by Ena on 25.12.2017..
 *
 */

public class Dokument {

    private int ID;
    private String naziv;
    private String vlasnik;
    private String fajl;

    public Dokument(int ID, String naziv, String vlasnik, String fajl) {
        this.ID = ID;
        this.naziv = naziv;
        this.vlasnik = vlasnik;
        this.fajl = fajl;
    }

    public int getID() {     return ID;    }

    public void setID(int ID) {    this.ID = ID;    }

    public String getNaziv() {       return naziv;   }

    public void setNaziv(String naziv) {       this.naziv = naziv;    }

    public String getVlasnik() {     return vlasnik;    }

    public void setVlasnik(String vlasnik) {       this.vlasnik = vlasnik;  }

    public String getFajl() {      return fajl;    }

    public void setFajl(String fajl) {       this.fajl = fajl;   }
}
