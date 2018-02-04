package poslovnalogika;

import db.DBBroker;
import domen.Brend;
import domen.Komponenta;
import domen.Konfiguracija;
import java.util.List;

public class Kontroler {

    private DBBroker db;
    private static Kontroler instance;

    private Kontroler() {
        this.db = new DBBroker();
    }

    public static Kontroler getInstance() {
        if (instance == null) {
            instance = new Kontroler();
        }
        return instance;
    }

    public List<Brend> vratiBrendove() throws Exception {
        db.ucitajDriver();
        db.otvoriKonekciju();
        List<Brend> lista = db.vratiBrendove();
        db.commitTransakcije();
        db.zatvoriKonekciju();
        return lista;
    }

    public List<Komponenta> vratiKomponente() throws Exception {
        db.ucitajDriver();
        db.otvoriKonekciju();
        List<Komponenta> lista = db.vratiKomponente();
        db.commitTransakcije();
        db.zatvoriKonekciju();
        return lista;
    }

    public int vratiID() throws Exception {
        db.ucitajDriver();
        db.otvoriKonekciju();
        int id = db.vratiID();
        db.commitTransakcije();
        db.zatvoriKonekciju();
        return id;
    }

    public void sacuvajKonfiguraciju(Konfiguracija k) throws Exception {
        try {
            k.pripremiKonfiguraciju();
            db.ucitajDriver();
            db.otvoriKonekciju();
            db.sacuvajKonfiguraciju(k);
            db.commitTransakcije();
        } catch (Exception ex) {
            db.rollbackTransakcije();
            throw ex;
        } finally {
            db.zatvoriKonekciju();
        }

    }
    
    public List<Komponenta> vratiListuZaPrikaz() throws Exception {
        db.ucitajDriver();
        db.otvoriKonekciju();
        List<Komponenta> lista = db.vratiListuZaPrikaz();
        db.commitTransakcije();
        db.zatvoriKonekciju();
        return lista;
    }
}
