package poslovnalogika;

import db.DBKomunikacija;
import domen.Rezervacija;
import domen.Vozilo;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    
    public List<Vozilo> vratiVozila() throws Exception {
        db.ucitajDriver();
        db.otvoriKonekciju();
        List<Vozilo> lista = db.vratiVozila();
        db.commitTransakcije();
        db.zatvoriKonekciju();
        return lista;
    }
    
    public void sacuvajRezervaciju(Rezervacija r) throws Exception {
        try {
            r.pripremi();
            db.ucitajDriver();
            db.otvoriKonekciju();
            db.sacuvajRezervaciju(r);
            db.commitTransakcije();
        } catch (Exception ex) {
            db.rollbackTransakcije();
            throw ex;
        } finally {
            db.zatvoriKonekciju();
        }
    }
}
