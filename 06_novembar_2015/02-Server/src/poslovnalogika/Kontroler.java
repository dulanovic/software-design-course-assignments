package poslovnalogika;

import db.DBKomunikacija;
import domen.Knjiga;
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

    public List<Knjiga> vratiKnjige() throws Exception {
        db.ucitajDriver();
        db.otvoriKonekciju();
        List<Knjiga> lista = db.vratiKnjige();
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

    public List<Knjiga> vratiListuZaServer() throws Exception {
        db.ucitajDriver();
        db.otvoriKonekciju();
        List<Knjiga> lista = db.vratiListuZaServer();
        db.commitTransakcije();
        db.zatvoriKonekciju();
        return lista;
    }
}
