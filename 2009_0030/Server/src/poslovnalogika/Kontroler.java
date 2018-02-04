package poslovnalogika;

import db.DBBroker;
import domen.Reprezentacija;
import domen.Utakmica;
import java.util.List;

public class Kontroler {

    private DBBroker db;
    private static Kontroler instance;

    public Kontroler() {
        this.db = new DBBroker();
    }

    public static Kontroler getInstance() {
        if (instance == null) {
            instance = new Kontroler();
        }
        return instance;

    }

    public List<Utakmica> vratiUtakmice() throws Exception {
        db.ucitajDriver();
        db.otvoriKonekciju();
        List<Utakmica> lista = db.vratiUtakmice();
        db.commitTransakcije();
        db.zatvoriKonekciju();
        return lista;
    }

    public void sacuvajUtakmice(List<Utakmica> lista) throws Exception {
        try {
            db.ucitajDriver();
            db.otvoriKonekciju();
            for (Utakmica u : lista) {
                db.sacuvajIzmene(u);
            }
            db.commitTransakcije();
        } catch (Exception ex) {
            db.rollbackTransakcije();
        } finally {
            db.zatvoriKonekciju();
        }

    }

    public List<Reprezentacija> vratiReprezentacije() throws Exception {
        db.ucitajDriver();
        db.otvoriKonekciju();
        List<Reprezentacija> lista = db.vratiReprezentacije();
        db.commitTransakcije();
        db.zatvoriKonekciju();
        return lista;
    }

    public void obrisiUtakmice(List<Utakmica> lista) throws Exception {
        try {
            db.ucitajDriver();
            db.otvoriKonekciju();
            for (Utakmica u : lista) {
                db.obrisiUtakmicu(u);
            }
            db.commitTransakcije();
        } catch (Exception ex) {
            db.rollbackTransakcije();
        } finally {
            db.zatvoriKonekciju();
        }
    }
}
