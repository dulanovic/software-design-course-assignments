package nit;

import domen.Drzava;
import forme.model.PrikazTableModel;
import java.util.List;
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
                List<Drzava> lista = Kontroler.getInstance().vratiListuZaPrikaz();
                lista = Drzava.izvrsiRangiranje(lista);
                PrikazTableModel model = new PrikazTableModel(lista);
                jtblPrikaz.setModel(model);
                Thread.sleep(5000);
            } catch (Exception ex) {

            }
        }
    }

}
