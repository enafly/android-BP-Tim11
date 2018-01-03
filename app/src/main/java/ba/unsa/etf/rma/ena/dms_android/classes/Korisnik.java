package ba.unsa.etf.rma.ena.dms_android.classes;

/**
 * Created by Ena on 25.12.2017..
 *
 */

public class Korisnik {

    private int id;
    private String ime;
    private String prezime;
    private String korisnickoIme;
    private String sifra;
    private int uloga;

    public Korisnik(int id, String ime, String prezime, String korisnickoIme, String sifra) {
        this.id = id;
        this.ime = ime;
        this.prezime = prezime;
        this.korisnickoIme = korisnickoIme;
        this.sifra = sifra;
    }

    public int getId() {    return id;    }

    public void setId(int id) {     this.id = id;    }

    public String getIme() {    return ime;    }

    public void setIme(String ime) {    this.ime = ime;  }

    public String getPrezime() {    return prezime;     }

    public void setPrezime(String prezime) {    this.prezime = prezime;    }

    public String getKorisnickoIme() {      return korisnickoIme;    }

    public void setKorisnickoIme(String korisnickoIme) {    this.korisnickoIme = korisnickoIme;    }

    public String getSifra() {      return sifra;    }

    public void setSifra(String sifra) {    this.sifra = sifra;      }

    public int getUloga() {     return uloga;   }

    public void setUloga(int uloga) {    this.uloga = uloga;     }
}
