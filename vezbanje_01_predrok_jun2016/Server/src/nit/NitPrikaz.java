package nit;

import domen.Kurs;
import forme.model.PrikazTableModel;
import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.JTextField;
import poslovnalogika.Kontroler;

public class NitPrikaz extends Thread {

    private JTextField jtxtDatumOd;
    private JTextField jtxtDatumDo;
    private JTable jtblPrikaz;
    private boolean aktivna;
    private final DateFormat df = new SimpleDateFormat("dd.MM.yyyy");

    public NitPrikaz(JTextField jtxtDatumOd, JTextField jtxtDatumDo, JTable jtblPrikaz) {
        this.jtxtDatumOd = jtxtDatumOd;
        this.jtxtDatumDo = jtxtDatumDo;
        this.jtblPrikaz = jtblPrikaz;
        this.aktivna = true;
    }

    @Override
    public void run() {
        while (aktivna) {
            try {
                List<Kurs> lista = new ArrayList<>();
                String datumOdS = jtxtDatumOd.getText();
                String datumDoS = jtxtDatumDo.getText();
                String upit = "";

                if (datumOdS.equals("") && datumDoS.equals("")) {
                    upit = "SELECT k.naziv,k.maxbrojpolaznika,COUNT(*) FROM kurs k INNER JOIN polaznikkurs pk ON (k.kursid=pk.kursid) GROUP BY k.kursid";
                } else if (datumOdS.equals("") && !(datumDoS.equals(""))) {
                    upit = "SELECT k.naziv,k.maxbrojpolaznika,COUNT(*) FROM kurs k INNER JOIN polaznikkurs pk ON (k.kursid=pk.kursid)  WHERE k.datumdo < '" + new java.sql.Date(df.parse(datumDoS).getTime()) + "' GROUP BY k.kursid";
                } else if (!(datumOdS.equals("")) && datumDoS.equals("")) {
                    upit = "SELECT k.naziv,k.maxbrojpolaznika,COUNT(*) FROM kurs k INNER JOIN polaznikkurs pk ON (k.kursid=pk.kursid)  WHERE k.datumod > '" + new java.sql.Date(df.parse(datumOdS).getTime()) + "' GROUP BY k.kursid";
                } else if(!(datumOdS.equals("")) && !(datumDoS.equals(""))) {
                    upit = "SELECT k.naziv,k.maxbrojpolaznika,COUNT(*) FROM kurs k INNER JOIN polaznikkurs pk ON (k.kursid=pk.kursid) WHERE k.datumod > '" + new java.sql.Date(df.parse(datumOdS).getTime()) + "' AND k.datumdo < '" + new java.sql.Date(df.parse(datumDoS).getTime()) + "' GROUP BY pk.kursid";
                }
                
                lista = Kontroler.getInstance().vratiListuZaPrikaz(upit);

                PrikazTableModel model = new PrikazTableModel(lista);
                jtblPrikaz.setModel(model);

                Thread.sleep(2000);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

}
