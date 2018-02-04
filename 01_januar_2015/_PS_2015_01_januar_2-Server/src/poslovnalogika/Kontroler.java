package poslovnalogika;

import db.DBKomunikacija;
import domen.IstorijaStatusaRada;
import domen.Profesor;
import domen.Rad;
import domen.StatusRada;
import domen.Student;
import java.util.ArrayList;
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

    public List<Student> vratiStudente() throws Exception {
        List<Student> lista = new ArrayList<>();
        db.ucitajDriver();
        db.otvoriKonekciju();
        lista = db.vratiStudente();
        db.commitTransakcije();
        db.zatvoriKonekciju();
        return lista;
    }

    public List<Profesor> vratiProfesore() throws Exception {
        List<Profesor> lista = new ArrayList<>();
        db.ucitajDriver();
        db.otvoriKonekciju();
        lista = db.vratiProfesore();
        db.commitTransakcije();
        db.zatvoriKonekciju();
        return lista;
    }

    public List<StatusRada> vratiStatuse() throws Exception {
        List<StatusRada> lista = new ArrayList<>();
        db.ucitajDriver();
        db.otvoriKonekciju();
        lista = db.vratiStatuse();
        db.commitTransakcije();
        db.zatvoriKonekciju();
        return lista;
    }
    
    public int vratiKljuc() throws Exception {
        int key = 0;
        db.ucitajDriver();
        db.otvoriKonekciju();
        key = db.vratiKljuc();
        db.commitTransakcije();
        db.zatvoriKonekciju();
        return key;
    }

    public void sacuvajRad(Rad rad) throws Exception {
        try {
            db.ucitajDriver();
            db.otvoriKonekciju();
            db.sacuvajRad(rad);
            db.commitTransakcije();
        } catch (Exception ex) {
            db.rollbackTransakcije();
            throw ex;
        } finally {
            db.zatvoriKonekciju();
        }
    }
    
    public List<IstorijaStatusaRada> vratiListuZaPrikaz() throws Exception {
        List<IstorijaStatusaRada> lista = new ArrayList<>();
        db.ucitajDriver();
        db.otvoriKonekciju();
        lista = db.vratiListuZaPrikaz();
        db.commitTransakcije();
        db.zatvoriKonekciju();
        return lista;
    }
}
