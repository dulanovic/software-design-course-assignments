package domen;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ModnaRevija implements Serializable {

    private int modnaRevijaID;
    private String naziv;
    private Date datumOdrzavanja;
    private Dizajner dizajner;
    private List<Angazovanje> listaAngazovanja;

    public ModnaRevija() {
        this.dizajner = new Dizajner();
        this.listaAngazovanja = new ArrayList<>();
    }

    public ModnaRevija(int modnaRevijaID, String naziv, Date datumOdrzavanja, Dizajner dizajner) {
        this.modnaRevijaID = modnaRevijaID;
        this.naziv = naziv;
        this.datumOdrzavanja = datumOdrzavanja;
        this.dizajner = dizajner;
    }

    public ModnaRevija(String naziv, Date datumOdrzavanja, Dizajner dizajner) {
        this.naziv = naziv;
        this.datumOdrzavanja = datumOdrzavanja;
        this.dizajner = dizajner;
    }

    public int getModnaRevijaID() {
        return modnaRevijaID;
    }

    public void setModnaRevijaID(int modnaRevijaID) {
        this.modnaRevijaID = modnaRevijaID;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public Date getDatumOdrzavanja() {
        return datumOdrzavanja;
    }

    public void setDatumOdrzavanja(Date datumOdrzavanja) {
        this.datumOdrzavanja = datumOdrzavanja;
    }

    public Dizajner getDizajner() {
        return dizajner;
    }

    public void setDizajner(Dizajner dizajner) {
        this.dizajner = dizajner;
    }

    public List<Angazovanje> getListaAngazovanja() {
        return listaAngazovanja;
    }

    public void setListaAngazovanja(List<Angazovanje> listaAngazovanja) {
        this.listaAngazovanja = listaAngazovanja;
    }

    @Override
    public String toString() {
        return naziv;
    }

}
