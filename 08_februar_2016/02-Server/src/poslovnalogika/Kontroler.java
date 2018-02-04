package poslovnalogika;

import db.DBKomunikacija;
import domen.Klijent;
import domen.Proizvod;
import domen.Racun;
import java.util.List;

public class Kontroler {

    private DBKomunikacija db;
    private static Kontroler instance;

    private Kontroler() {
        this.db = new DBKomunikacija();
    }

    public static Kontroler getInstance() {
        if (instance == null) {
            instance = new Kontroler();
        }
        return instance;
    }

    public Racun nadjiRacun(int id) throws Exception {
        db.ucitajDriver();
        db.otvoriKonekciju();
        Racun r = db.nadjiRacun(id);
        db.commitTransakcije();
        db.zatvoriKonekciju();
        return r;
    }

    public List<Klijent> vratiKlijente() throws Exception {
        db.ucitajDriver();
        db.otvoriKonekciju();
        List<Klijent> lista = db.vratiKlijente();
        db.commitTransakcije();
        db.zatvoriKonekciju();
        return lista;
    }

    public List<Proizvod> vratiProizvode() throws Exception {
        db.ucitajDriver();
        db.otvoriKonekciju();
        List<Proizvod> lista = db.vratiProizvode();
        db.commitTransakcije();
        db.zatvoriKonekciju();
        return lista;
    }

    public void sacuvajRacun(Racun r) throws Exception {
        try {
            r.pripremi();
            db.ucitajDriver();
            db.otvoriKonekciju();
            db.sacuvajRacun(r);
            db.commitTransakcije();
        } catch (Exception ex) {
            db.rollbackTransakcije();
            throw ex;
        } finally {
            db.zatvoriKonekciju();
        }

    }
    
    public List<Klijent> vratiListuZaPrikaz() throws Exception {
        db.ucitajDriver();
        db.otvoriKonekciju();
        List<Klijent> lista = db.vratiListuZaPrikaz();
        db.commitTransakcije();
        db.zatvoriKonekciju();
        return lista;
    }
}
