package nit;

import domen.Projekat;
import forme.model.PrikazTableModel;
import java.util.List;
import javax.swing.JTable;
import javax.swing.JTextField;
import poslovnalogika.Kontroler;

public class NitPrikaz extends Thread {
    
    private boolean aktivna;
    private JTable jtblPrikaz;
    private JTextField jtxtDatumOd;
    private JTextField jtxtDatumDo;
    
    public NitPrikaz(JTable jtblPrikaz, JTextField jtxtDatumOd, JTextField jtxtDatumDo) {
        this.aktivna = true;
        this.jtblPrikaz = jtblPrikaz;
        this.jtxtDatumOd = jtxtDatumOd;
        this.jtxtDatumDo = jtxtDatumDo;
    }
    
    @Override
    public void run() {
        while (aktivna) {
            try {
                List<Projekat> lista = Kontroler.getInstance().vratiListuZaServer();
                
                PrikazTableModel model = new PrikazTableModel(lista);
                jtblPrikaz.setModel(model);
                
                Thread.sleep(5000);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
    
}
