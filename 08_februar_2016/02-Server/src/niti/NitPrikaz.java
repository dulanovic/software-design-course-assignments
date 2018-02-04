package niti;

import domen.Klijent;
import forme.model.PrikazTableModel;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JTable;
import poslovnalogika.Kontroler;

public class NitPrikaz extends Thread {

    private boolean aktivna;
    private JTable jtblPrikaz;
    private JCheckBox jchkbFilter;
    private JLabel jlblVreme;

    public NitPrikaz(JTable jtblPrikaz, JLabel jlblVreme) {
        this.aktivna = true;
        this.jtblPrikaz = jtblPrikaz;
        this.jlblVreme = jlblVreme;
    }

    @Override
    public void run() {
        while(aktivna) {
            try {
                Date datumIVreme = new Date();
                jlblVreme.setText(String.valueOf(datumIVreme));
                
                List<Klijent> lista = Kontroler.getInstance().vratiListuZaPrikaz();
                
                PrikazTableModel model = new PrikazTableModel(lista);
                jtblPrikaz.setModel(model);
                
                Thread.sleep(5000);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            
        }
    }

}
