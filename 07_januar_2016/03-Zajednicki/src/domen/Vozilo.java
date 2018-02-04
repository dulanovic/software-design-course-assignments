package domen;

import java.io.Serializable;

public class Vozilo implements Serializable {

    private int voziloID;
    private String registracija;
    private double cenaPoDanu;
    private Tip tip;

    public Vozilo() {
        this.tip = new Tip();
    }

    public Vozilo(int voziloID, String registracija, double cenaPoDanu, Tip tip) {
        this.voziloID = voziloID;
        this.registracija = registracija;
        this.cenaPoDanu = cenaPoDanu;
        this.tip = tip;
    }

    public int getVoziloID() {
        return voziloID;
    }

    public void setVoziloID(int voziloID) {
        this.voziloID = voziloID;
    }

    public String getRegistracija() {
        return registracija;
    }

    public void setRegistracija(String registracija) {
        this.registracija = registracija;
    }

    public double getCenaPoDanu() {
        return cenaPoDanu;
    }

    public void setCenaPoDanu(double cenaPoDanu) {
        this.cenaPoDanu = cenaPoDanu;
    }

    public Tip getTip() {
        return tip;
    }

    public void setTip(Tip tip) {
        this.tip = tip;
    }

    @Override
    public String toString() {
        return registracija;
    }

}
