package domen;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Racun implements Serializable {

    private int racunID;
    private Date datum;
    private Klijent klijent;
    private double ukupanIznos;
    private List<StavkaRacuna> lista;
    private List<StavkaRacuna> listaZaBrisanje;
    private List<StavkaRacuna> listaZaCuvanje;

    public Racun() {
        this.klijent = new Klijent();
        this.lista = new ArrayList<>();
        this.listaZaBrisanje = new ArrayList<>();
    }

    public Racun(int racunID, Date datum, Klijent klijent) {
        this.racunID = racunID;
        this.datum = datum;
        this.klijent = klijent;
        this.lista = new ArrayList<>();
        this.listaZaBrisanje = new ArrayList<>();
        this.listaZaCuvanje = new ArrayList<>();
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

    public Klijent getKlijent() {
        return klijent;
    }

    public void setKlijent(Klijent klijent) {
        this.klijent = klijent;
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

    public List<StavkaRacuna> getListaZaBrisanje() {
        return listaZaBrisanje;
    }

    public void setListaZaBrisanje(List<StavkaRacuna> listaZaBrisanje) {
        this.listaZaBrisanje = listaZaBrisanje;
    }

    public List<StavkaRacuna> getListaZaCuvanje() {
        return listaZaCuvanje;
    }

    public void setListaZaCuvanje(List<StavkaRacuna> listaZaCuvanje) {
        this.listaZaCuvanje = listaZaCuvanje;
    }

    public void pripremi() {
        int rb = 1;
        double iznos = 0;

        for (StavkaRacuna sr : lista) {
            sr.setRb(rb);
            iznos += sr.getIznos();
            rb++;
        }
        setUkupanIznos(iznos);
    }
}
