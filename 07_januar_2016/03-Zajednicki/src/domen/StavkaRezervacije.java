package domen;

import java.io.Serializable;
import java.util.Date;

public class StavkaRezervacije implements Serializable {

    private int rb;
    private Vozilo vozilo;
    private Date datumOd;
    private Date datumDo;
    private double iznos;

    public StavkaRezervacije() {
        this.vozilo = new Vozilo();
    }

    public StavkaRezervacije(int rb, Vozilo vozilo, Date datumOd, Date datumDo, double iznos) {
        this.rb = rb;
        this.vozilo = vozilo;
        this.datumOd = datumOd;
        this.datumDo = datumDo;
        this.iznos = iznos;
    }

    public int getRb() {
        return rb;
    }

    public void setRb(int rb) {
        this.rb = rb;
    }

    public Vozilo getVozilo() {
        return vozilo;
    }

    public void setVozilo(Vozilo vozilo) {
        this.vozilo = vozilo;
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

    public double getIznos() {
        return iznos;
    }

    public void setIznos(double iznos) {
        this.iznos = iznos;
    }

}
