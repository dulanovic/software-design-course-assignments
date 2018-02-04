package domen;

import java.io.Serializable;
import java.util.Date;

public class Dezurstvo implements Serializable {

    private int dezurstvoID;
    private Date datum;
    private IspitniRok ispitniRok;
    private Nastavnik nastavnik;
    private Predmet predmet;

    public Dezurstvo() {
        this.ispitniRok = new IspitniRok();
        this.nastavnik = new Nastavnik();
        this.predmet = new Predmet();
    }

    public Dezurstvo(int dezurstvoID, Date datum, IspitniRok ispitniRok, Nastavnik nastavnik, Predmet predmet) {
        this.dezurstvoID = dezurstvoID;
        this.datum = datum;
        this.ispitniRok = ispitniRok;
        this.nastavnik = nastavnik;
        this.predmet = predmet;
    }

    public Dezurstvo(Date datum, IspitniRok ispitniRok, Nastavnik nastavnik, Predmet predmet) {
        this.datum = datum;
        this.ispitniRok = ispitniRok;
        this.nastavnik = nastavnik;
        this.predmet = predmet;
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

    public IspitniRok getIspitniRok() {
        return ispitniRok;
    }

    public void setIspitniRok(IspitniRok ispitniRok) {
        this.ispitniRok = ispitniRok;
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

}
