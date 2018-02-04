/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import java.io.Serializable;

/**
 *
 * @author student1
 */
public class Mesto implements Serializable {
    private int ptt;
    private String naziv;

    public Mesto() {
        this.ptt = 0;
        this.naziv = "n/a";
    }

    public Mesto(int ptt, String naziv) {
        this.ptt = ptt;
        this.naziv = naziv;
    }

    public int getPtt() {
        return ptt;
    }

    public void setPtt(int ptt) {
        this.ptt = ptt;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    @Override
    public String toString() {
        return ptt + " - " + naziv;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj != null && obj instanceof Mesto) {
            Mesto m = (Mesto) obj;
            return m.getPtt() == this.ptt;
        }
        return false;
    }
}
