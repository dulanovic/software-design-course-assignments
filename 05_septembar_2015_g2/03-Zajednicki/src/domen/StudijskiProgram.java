package domen;

import java.io.Serializable;

public class StudijskiProgram implements Serializable {

    private int studijskiProgramID;
    private String naziv;
    private int maxBrojStudenata;
    private Fakultet fakultet;

    public StudijskiProgram() {
        this.fakultet = new Fakultet();
    }

    public StudijskiProgram(int studijskiProgramID, String naziv, int maxBrojStudenata, Fakultet fakultet) {
        this.studijskiProgramID = studijskiProgramID;
        this.naziv = naziv;
        this.maxBrojStudenata = maxBrojStudenata;
        this.fakultet = fakultet;
    }

    public int getStudijskiProgramID() {
        return studijskiProgramID;
    }

    public void setStudijskiProgramID(int studijskiProgramID) {
        this.studijskiProgramID = studijskiProgramID;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public int getMaxBrojStudenata() {
        return maxBrojStudenata;
    }

    public void setMaxBrojStudenata(int maxBrojStudenata) {
        this.maxBrojStudenata = maxBrojStudenata;
    }

    public Fakultet getFakultet() {
        return fakultet;
    }

    public void setFakultet(Fakultet fakultet) {
        this.fakultet = fakultet;
    }

    @Override
    public String toString() {
        return naziv;
    }

}
