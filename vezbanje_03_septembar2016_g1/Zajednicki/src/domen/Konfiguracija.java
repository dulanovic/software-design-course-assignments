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
    private double ukupanIznos;
    
    public Konfiguracija() {
        this.brend = new Brend();
        listaStavki = new ArrayList<>();
    }
    
    public Konfiguracija(int konfiguracijaID, String naziv, Date datumIzrade, Brend brend) {
        this.konfiguracijaID = konfiguracijaID;
        this.naziv = naziv;
        this.datumIzrade = datumIzrade;
        this.brend = brend;
        this.listaStavki = new ArrayList<>();
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
    
    public double getUkupanIznos() {
        return ukupanIznos;
    }
    
    public void setUkupanIznos(double ukupanIznos) {
        this.ukupanIznos = ukupanIznos;
    }
    
    @Override
    public String toString() {
        return naziv;
    }
    
    public void obrisiStavku(int red) {
        listaStavki.remove(red);
    }
    
    public void dodajStavku() {
        listaStavki.add(new StavkaKonfiguracije());
    }
    
    public void pripremiKonfiguraciju() {
        int rb = 0;
        int iznos = 0;
        for (StavkaKonfiguracije sk : listaStavki) {
            iznos += sk.getIznos();
            rb++;
            sk.setRb(rb);
        }
        setUkupanIznos(iznos);
    }
}
