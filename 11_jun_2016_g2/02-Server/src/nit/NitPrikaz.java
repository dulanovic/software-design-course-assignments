package nit;

import domen.Nastavnik;
import forme.model.PrikazTableModel;
import java.util.List;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JTable;
import poslovnalogika.Kontroler;

public class NitPrikaz extends Thread {

    private boolean aktivna;
    private JTable jtblPrikaz;
    private JCheckBox jchkFilter;
    private JComboBox jcbIspitniRok;
    private JComboBox jcbPredmet;

    public NitPrikaz(JTable jtblPrikaz, JCheckBox jchkFilter, JComboBox jcbIspitniRok, JComboBox jcbPredmet) {
        this.aktivna = true;
        this.jtblPrikaz = jtblPrikaz;
        this.jchkFilter = jchkFilter;
        this.jcbIspitniRok = jcbIspitniRok;
        this.jcbPredmet = jcbPredmet;
    }

    @Override
    public void run() {
        while (aktivna) {
            try {
                String kriterijum = "";
                List<Nastavnik> lista = Kontroler.getInstance().vratiListuZaPrikaz(kriterijum);
                
                PrikazTableModel model = new PrikazTableModel(lista);
                jtblPrikaz.setModel(model);
                
                Thread.sleep(5000);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            
            
        }
    }

}
