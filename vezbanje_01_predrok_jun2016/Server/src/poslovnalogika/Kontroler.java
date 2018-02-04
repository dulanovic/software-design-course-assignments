package poslovnalogika;

import db.DBBroker;
import domen.Kurs;
import domen.Polaznik;
import domen.PolaznikKurs;
import domen.VrstaKursa;
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

    public List<VrstaKursa> vratiVrste() throws Exception {
        db.ucitajDriver();
        db.otvoriKonekciju();
        List<VrstaKursa> lista = db.vratiVrste();
        db.commitTransakcije();
        db.zatvoriKonekciju();
        System.out.println(lista.size());
        return lista;
    }

    public List<Polaznik> vratiPolaznike() throws Exception {
        db.ucitajDriver();
        db.otvoriKonekciju();
        List<Polaznik> lista = db.vratiPolaznike();
        db.commitTransakcije();
        db.zatvoriKonekciju();
        return lista;
    }

    public void sacuvajKurs(Kurs k) throws Exception {
        try {
            db.ucitajDriver();
            db.otvoriKonekciju();
            db.sacuvajKurs(k);
            db.commitTransakcije();
        } catch (Exception ex) {
            db.rollbackTransakcije();
            throw ex;
        } finally {
            db.zatvoriKonekciju();
        }
    }
    
    public List<Kurs> vratiListuZaPrikaz(String pretraga) throws Exception {
        db.ucitajDriver();
        db.otvoriKonekciju();
        List<Kurs> lista = db.vratiListuZaPrikaz(pretraga);
        db.commitTransakcije();
        db.zatvoriKonekciju();
        return lista;
    }
}
