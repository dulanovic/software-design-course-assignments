package domen;

import java.io.Serializable;

public class StatusRada implements Serializable {

    private int StatusID;
    private String nazivStatusa;

    public StatusRada() {
    }

    public StatusRada(int StatusID, String nazivStatusa) {
        this.StatusID = StatusID;
        this.nazivStatusa = nazivStatusa;
    }

    public int getStatusID() {
        return StatusID;
    }

    public void setStatusID(int StatusID) {
        this.StatusID = StatusID;
    }

    public String getNaziv() {
        return nazivStatusa;
    }

    public void setNaziv(String nazivStatusa) {
        this.nazivStatusa = nazivStatusa;
    }

    @Override
    public String toString() {
        return this.nazivStatusa;
    }

}
