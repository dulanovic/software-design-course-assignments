package nit;

import domen.Model;
import forme.model.PrikazTableModel;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import poslovnalogika.Kontroler;

public class NitPrikaz extends Thread {

    private boolean aktivna;
    private JTable jtblPrikaz;

    public NitPrikaz(JTable jtblPrikaz) {
        this.aktivna = true;
        this.jtblPrikaz = jtblPrikaz;
    }

    @Override
    public void run() {
        while (aktivna) {
            try {
                List<Model> lista = Kontroler.getInstance().vratiListuZaPrikaz();

                PrikazTableModel model = new PrikazTableModel(lista);
                this.jtblPrikaz.setModel(model);

                Thread.sleep(5000);
            } catch (Exception ex) {

            }
        }
    }

}
