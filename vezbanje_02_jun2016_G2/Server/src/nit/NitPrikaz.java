package nit;

import domen.Nastavnik;
import forme.model.PrikazTableModel;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import poslovnalogika.Kontroler;

public class NitPrikaz extends Thread {

    private JRadioButton jrbFilter;
    private JComboBox jcbIspitniRok;
    private JComboBox jcbPredmet;
    private JTable jtblPrikaz;
    private boolean aktivna;

    public NitPrikaz(JRadioButton jrbFilter, JComboBox jcbIspitniRok, JComboBox jcbPredmet, JTable jtblPrikaz) {
        this.jrbFilter = jrbFilter;
        this.jcbIspitniRok = jcbIspitniRok;
        this.jcbPredmet = jcbPredmet;
        this.jtblPrikaz = jtblPrikaz;
        this.aktivna = true;
    }

    @Override
    public void run() {
        while (aktivna) {
            try {
                String upit = "SELECT n.nastavnikid, COUNT(*), SUM(p.trajanjeispita)/60,(SUM(p.trajanjeispita)/60)*10 FROM dezurstvo d INNER JOIN nastavnik n ON (n.nastavnikid=d.nastavnikid) INNER JOIN predmet p ON (d.predmetid=p.predmetid) INNER JOIN ispitnirok ir ON (d.ispitnirokid=ir.ispitnirokid) GROUP BY d.nastavnikid";
                
                if (jrbFilter.isSelected()) {
                    if (jcbIspitniRok.getSelectedItem() != null && jcbPredmet.getSelectedItem() == null) {
                        System.out.println(jcbIspitniRok.getSelectedItem());
                        upit = "SELECT n.nastavnikid,COUNT(*),SUM(p.trajanjeispita)/60,(SUM(p.trajanjeispita)/60)*10 FROM dezurstvo d INNER JOIN nastavnik n ON (n.nastavnikid=d.nastavnikid) INNER JOIN predmet p ON (d.predmetid=p.predmetid) INNER JOIN ispitnirok ir ON (d.ispitnirokid=ir.ispitnirokid) WHERE ir.naziv='" + jcbIspitniRok.getSelectedItem().toString() + "' GROUP BY d.nastavnikid";
                    } else if (jcbIspitniRok.getSelectedItem() == null && jcbPredmet.getSelectedItem() != null) {
                        System.out.println(jcbPredmet.getSelectedItem());
                        upit = "SELECT n.nastavnikid,COUNT(*),SUM(p.trajanjeispita)/60,(SUM(p.trajanjeispita)/60)*10 FROM dezurstvo d INNER JOIN nastavnik n ON (n.nastavnikid=d.nastavnikid) INNER JOIN predmet p ON (d.predmetid=p.predmetid) INNER JOIN ispitnirok ir ON (d.ispitnirokid=ir.ispitnirokid) WHERE p.naziv='" + jcbPredmet.getSelectedItem().toString() + "' GROUP BY d.nastavnikid";
                    } else if (jcbIspitniRok.getSelectedItem() != null && jcbPredmet.getSelectedItem() != null) {
                        upit = "SELECT n.nastavnikid,COUNT(*),SUM(p.trajanjeispita)/60,(SUM(p.trajanjeispita)/60)*10 FROM dezurstvo d INNER JOIN nastavnik n ON (n.nastavnikid=d.nastavnikid) INNER JOIN predmet p ON (d.predmetid=p.predmetid) INNER JOIN ispitnirok ir ON (d.ispitnirokid=ir.ispitnirokid) WHERE p.naziv=" + jcbPredmet.getSelectedItem().toString() + " AND ir.naziv='" + jcbIspitniRok.getSelectedItem().toString() + "' GROUP BY d.nastavnikid";
                    }
                }
                
                List<Nastavnik> lista = Kontroler.getInstance().vratiListuZaPrikaz(upit);
                
                PrikazTableModel model = new PrikazTableModel(lista);
                jtblPrikaz.setModel(model);
                
                Thread.sleep(5000);
            } catch (Exception ex) {
                Logger.getLogger(NitPrikaz.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
