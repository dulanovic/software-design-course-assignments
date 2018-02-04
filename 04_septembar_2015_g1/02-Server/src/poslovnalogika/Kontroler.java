package poslovnalogika;

import db.DBKomunikacija;
import domen.Brend;
import domen.Komponenta;
import domen.Konfiguracija;
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

    public int vratiPrimarniKljuc() throws Exception {
        db.ucitajDriver();
        db.otvoriKonekciju();
        int kljuc = db.vratiPrimarniKljuc();
        db.commitTransakcije();
        db.zatvoriKonekciju();
        return kljuc;
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

    public List<Komponenta> vratiListuZaServer() throws Exception {
        db.ucitajDriver();
        db.otvoriKonekciju();
        List<Komponenta> lista = db.vratiListuZaServer();
        db.commitTransakcije();
        db.zatvoriKonekciju();
        return lista;
    }
}
