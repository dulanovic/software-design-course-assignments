package nit;

import domen.Komponenta;
import forme.model.PrikazTableModel;
import java.util.List;
import javax.swing.JTable;
import poslovnalogika.Kontroler;

public class NitPrikaz extends Thread {
    
    private boolean aktivna;
    private JTable jtblPrikaz;

    public NitPrikaz(JTable jtblPrikaz) {
        this.jtblPrikaz = jtblPrikaz;
        this.aktivna = true;
    }

    @Override
    public void run() {
        while(aktivna) {
            try {
                List<Komponenta> lista = Kontroler.getInstance().vratiListuZaPrikaz();
                PrikazTableModel model = new PrikazTableModel(lista);
                jtblPrikaz.setModel(model);
                Thread.sleep(10000);
            } catch (Exception ex) {
                
            }
        }
    }
    
    
}
