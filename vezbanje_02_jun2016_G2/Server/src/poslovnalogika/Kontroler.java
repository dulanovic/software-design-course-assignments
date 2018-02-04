package poslovnalogika;

import db.DBBroker;
import domen.Dezurstvo;
import domen.IspitniRok;
import domen.Nastavnik;
import domen.Predmet;
import java.util.List;

public class Kontroler {

    private DBBroker db;
    private static Kontroler instance;

    private Kontroler() {
        db = new DBBroker();
    }

    public static Kontroler getInstance() {
        if (instance == null) {
            instance = new Kontroler();
        }
        return instance;
    }

    public List<IspitniRok> vratiIspitneRokove() throws Exception {
        db.ucitajDriver();
        db.otvoriKonekciju();
        List<IspitniRok> lista = db.vratiIspitneRokove();
        db.commitTransakcije();
        db.zatvoriKonekciju();
        return lista;
    }

    public List<Nastavnik> vratiNastavnike() throws Exception {
        db.ucitajDriver();
        db.otvoriKonekciju();
        List<Nastavnik> lista = db.vratiNastavnike();
        db.commitTransakcije();
        db.zatvoriKonekciju();
        return lista;
    }

    public List<Predmet> vratiPredmete() throws Exception {
        db.ucitajDriver();
        db.otvoriKonekciju();
        List<Predmet> lista = db.vratiPredmete();
        db.commitTransakcije();
        db.zatvoriKonekciju();
        return lista;
    }

    public void sacuvajDezurstva(List<Dezurstvo> lista) throws Exception {
        try {
            db.ucitajDriver();
            db.otvoriKonekciju();
            for (Dezurstvo d : lista) {
                db.sacuvajDezurstvo(d);
            }
            db.commitTransakcije();
        } catch (Exception ex) {
            db.rollbackTransakcije();
        } finally {
            db.zatvoriKonekciju();
        }

    }
    
    public List<Nastavnik> vratiListuZaPrikaz(String upit) throws Exception {
        db.ucitajDriver();
        db.otvoriKonekciju();
        List<Nastavnik> lista = db.vratiListuZaPrikaz(upit);
        db.commitTransakcije();
        db.zatvoriKonekciju();
        return lista;
    }
}
