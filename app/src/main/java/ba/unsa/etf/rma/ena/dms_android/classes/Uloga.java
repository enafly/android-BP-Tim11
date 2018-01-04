package ba.unsa.etf.rma.ena.dms_android.classes;

/**
 * Created by Ena on 25.12.2017..
 *
 */

public class Uloga {

    private int id;
    private String naziv;

    public Uloga(int id, String naziv) {
        this.id = id;
        this.naziv = naziv;
    }

    public int getId() {     return id;     }

    public void setId(int id) {      this.id = id;      }

    public String getNaziv() {        return naziv;     }

    public void setNaziv(String naziv) {       this.naziv = naziv;      }

}
