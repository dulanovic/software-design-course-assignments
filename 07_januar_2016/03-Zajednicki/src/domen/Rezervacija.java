package domen;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Rezervacija implements Serializable {

    private int rezervacijaID;
    private String klijent;
    private double ukupanIznos;
    private List<StavkaRezervacije> lista;

    public Rezervacija() {
        this.lista = new ArrayList<>();
    }

    public Rezervacija(int rezervacijaID, String klijent, double ukupanIznos) {
        this.rezervacijaID = rezervacijaID;
        this.klijent = klijent;
        this.ukupanIznos = ukupanIznos;
        this.lista = new ArrayList<>();
    }

    public int getRezervacijaID() {
        return rezervacijaID;
    }

    public void setRezervacijaID(int rezervacijaID) {
        this.rezervacijaID = rezervacijaID;
    }

    public String getKlijent() {
        return klijent;
    }

    public void setKlijent(String klijent) {
        this.klijent = klijent;
    }

    public double getUkupanIznos() {
        return ukupanIznos;
    }

    public void setUkupanIznos(double ukupanIznos) {
        this.ukupanIznos = ukupanIznos;
    }

    public List<StavkaRezervacije> getLista() {
        return lista;
    }

    public void setLista(List<StavkaRezervacije> lista) {
        this.lista = lista;
    }

    public void pripremi() {
        double iznos = 0;
        for (StavkaRezervacije sr : lista) {
            iznos += sr.getIznos();
        }
        setUkupanIznos(iznos);
    }
}
