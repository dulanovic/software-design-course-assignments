package domen;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Konfiguracija implements Serializable {

    private int konfiguracijaID;
    private String naziv;
    private Date datumIzrade;
    private Brend brend;
    private List<StavkaKonfiguracije> listaStavki;

    public Konfiguracija() {
        this.brend = new Brend();
        this.listaStavki = new ArrayList<>();
    }

    public Konfiguracija(int konfiguracijaID, String naziv, Date datumIzrade, Brend brend) {
        this.konfiguracijaID = konfiguracijaID;
        this.naziv = naziv;
        this.datumIzrade = datumIzrade;
        this.brend = brend;
    }

    public int getKonfiguracijaID() {
        return konfiguracijaID;
    }

    public void setKonfiguracijaID(int konfiguracijaID) {
        this.konfiguracijaID = konfiguracijaID;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public Date getDatumIzrade() {
        return datumIzrade;
    }

    public void setDatumIzrade(Date datumIzrade) {
        this.datumIzrade = datumIzrade;
    }

    public Brend getBrend() {
        return brend;
    }

    public void setBrend(Brend brend) {
        this.brend = brend;
    }

    public List<StavkaKonfiguracije> getListaStavki() {
        return listaStavki;
    }

    public void setListaStavki(List<StavkaKonfiguracije> listaStavki) {
        this.listaStavki = listaStavki;
    }

    @Override
    public String toString() {
        return naziv;
    }

    public void pripremiKonfiguraciju() {
        int rb = 1;
        for (StavkaKonfiguracije sk : this.getListaStavki()) {
            sk.setRb(rb);
            rb++;
        }
    }
}
