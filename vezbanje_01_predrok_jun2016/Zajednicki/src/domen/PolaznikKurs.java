package domen;

import java.io.Serializable;
import java.util.Date;

public class PolaznikKurs implements Serializable {

    private int polaznikKursID;
    private Polaznik polaznik;
    private Kurs kurs;
    private Date datumPrijave;

    public PolaznikKurs() {
        this.polaznik = new Polaznik();
        this.kurs = new Kurs();
    }
    
    public PolaznikKurs(Kurs kurs) {
        this.polaznik = new Polaznik();
        this.kurs = kurs;
    }

    public PolaznikKurs(Polaznik polaznik, Kurs kurs, Date datumPrijave) {
        this.polaznik = polaznik;
        this.kurs = kurs;
        this.datumPrijave = datumPrijave;
    }

    public int getPolaznikKursID() {
        return polaznikKursID;
    }

    public void setPolaznikKursID(int polaznikKursID) {
        this.polaznikKursID = polaznikKursID;
    }

    public Polaznik getPolaznik() {
        return polaznik;
    }

    public void setPolaznik(Polaznik polaznik) {
        this.polaznik = polaznik;
    }

    public Kurs getKurs() {
        return kurs;
    }

    public void setKurs(Kurs kurs) {
        this.kurs = kurs;
    }

    public Date getDatumPrijave() {
        return datumPrijave;
    }

    public void setDatumPrijave(Date datumPrijave) {
        this.datumPrijave = datumPrijave;
    }

}
