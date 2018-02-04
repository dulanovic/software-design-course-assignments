package domen;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Racun implements Serializable {
    
    private int racunID;
    private Date datum;
    private double ukupanIznos;
    private List<StavkaRacuna> lista;
    
    public Racun() {
        this.lista = new ArrayList<>();
    }
    
    public Racun(int racunID, Date datum, double ukupanIznos) {
        this.racunID = racunID;
        this.datum = datum;
        this.ukupanIznos = ukupanIznos;
        this.lista = new ArrayList<>();
    }
    
    public int getRacunID() {
        return racunID;
    }
    
    public void setRacunID(int racunID) {
        this.racunID = racunID;
    }
    
    public Date getDatum() {
        return datum;
    }
    
    public void setDatum(Date datum) {
        this.datum = datum;
    }
    
    public double getUkupanIznos() {
        return ukupanIznos;
    }
    
    public void setUkupanIznos(double ukupanIznos) {
        this.ukupanIznos = ukupanIznos;
    }
    
    public List<StavkaRacuna> getLista() {
        return lista;
    }
    
    public void setLista(List<StavkaRacuna> lista) {
        this.lista = lista;
    }
    
    public void pripremi() {
        double iznos = 0;
        for (StavkaRacuna sr : this.lista) {
            iznos += sr.getIznosStavke();
        }
        setUkupanIznos(iznos);
    }
}
