package domen;

import java.io.Serializable;
import java.util.Date;

public class IspitniRok implements Serializable {

    private int ispitniRokID;
    private String naziv;
    private Date datumOd;
    private Date datumDo;

    public IspitniRok() {
    }

    public IspitniRok(int ispitniRokID, String naziv, Date datumOd, Date datumDo) {
        this.ispitniRokID = ispitniRokID;
        this.naziv = naziv;
        this.datumOd = datumOd;
        this.datumDo = datumDo;
    }

    public int getIspitniRokID() {
        return ispitniRokID;
    }

    public void setIspitniRokID(int ispitniRokID) {
        this.ispitniRokID = ispitniRokID;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public Date getDatumOd() {
        return datumOd;
    }

    public void setDatumOd(Date datumOd) {
        this.datumOd = datumOd;
    }

    public Date getDatumDo() {
        return datumDo;
    }

    public void setDatumDo(Date datumDo) {
        this.datumDo = datumDo;
    }

    @Override
    public String toString() {
        return naziv;
    }

}
