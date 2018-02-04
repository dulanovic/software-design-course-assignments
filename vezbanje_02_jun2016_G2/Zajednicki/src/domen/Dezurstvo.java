package domen;

import java.io.Serializable;
import java.util.Date;

public class Dezurstvo implements Serializable {

    private int dezurstvoID;
    private Date datum;
    private Nastavnik nastavnik;
    private Predmet predmet;
    private IspitniRok ispitniRok;

    public Dezurstvo() {
        this.nastavnik = new Nastavnik();
        this.predmet = new Predmet();
        this.ispitniRok = new IspitniRok();
    }

    public Dezurstvo(int dezurstvoID, Date datum, Nastavnik nastavnik, Predmet predmet, IspitniRok ispitniRok) {
        this.dezurstvoID = dezurstvoID;
        this.datum = datum;
        this.nastavnik = nastavnik;
        this.predmet = predmet;
        this.ispitniRok = ispitniRok;
    }

    public int getDezurstvoID() {
        return dezurstvoID;
    }

    public void setDezurstvoID(int dezurstvoID) {
        this.dezurstvoID = dezurstvoID;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public Nastavnik getNastavnik() {
        return nastavnik;
    }

    public void setNastavnik(Nastavnik nastavnik) {
        this.nastavnik = nastavnik;
    }

    public Predmet getPredmet() {
        return predmet;
    }

    public void setPredmet(Predmet predmet) {
        this.predmet = predmet;
    }

    public IspitniRok getIspitniRok() {
        return ispitniRok;
    }

    public void setIspitniRok(IspitniRok ispitniRok) {
        this.ispitniRok = ispitniRok;
    }

}
