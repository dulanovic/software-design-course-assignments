package domen;

import java.io.Serializable;

public class PrijemniIspit implements Serializable {

    private StudijskiProgram studijskiProgram;
    private Kandidat kandidat;
    private double brojPoenaPrijemni;

    public PrijemniIspit() {
        this.studijskiProgram = new StudijskiProgram();
        this.kandidat = new Kandidat();
    }

    public PrijemniIspit(StudijskiProgram studijskiProgram, Kandidat kandidat, double brojPoenaPrijemni) {
        this.studijskiProgram = studijskiProgram;
        this.kandidat = kandidat;
        this.brojPoenaPrijemni = brojPoenaPrijemni;
    }

    public StudijskiProgram getStudijskiProgram() {
        return studijskiProgram;
    }

    public void setStudijskiProgram(StudijskiProgram studijskiProgram) {
        this.studijskiProgram = studijskiProgram;
    }

    public Kandidat getKandidat() {
        return kandidat;
    }

    public void setKandidat(Kandidat kandidat) {
        this.kandidat = kandidat;
    }

    public double getBrojPoenaPrijemni() {
        return brojPoenaPrijemni;
    }

    public void setBrojPoenaPrijemni(double brojPoenaPrijemni) {
        this.brojPoenaPrijemni = brojPoenaPrijemni;
    }

}
