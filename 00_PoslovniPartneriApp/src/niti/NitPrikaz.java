package niti;

import domen.Proizvod;
import forme.racun.model.ProizvodTableModel;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.JTextField;
import poslovnalogika.Kontroler;

public class NitPrikaz extends Thread {

    private JTextField jtxtPretraga;
    private JTable jtblPrikaz;
    private boolean aktivna;

    public NitPrikaz(JTable jtblPrikaz, JTextField jtxtPretraga) {
        this.jtblPrikaz = jtblPrikaz;
        this.jtxtPretraga = jtxtPretraga;
        this.aktivna = true;
    }

    @Override
    public void run() {
        while (aktivna) {
            try {
                List<Proizvod> lista = new ArrayList<>();
                if (jtxtPretraga.getText().equals("")) {
                    lista = Kontroler.getInstance().vratiProizvodePrikaz();
                } else {
                String kriterijumPretrage = jtxtPretraga.getText();
                lista = Kontroler.getInstance().nadjiProizvode(kriterijumPretrage);
                }
                
                ProizvodTableModel model = new ProizvodTableModel(lista);
                jtblPrikaz.setModel(model);
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(NitPrikaz.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (Exception ex) {
                Logger.getLogger(NitPrikaz.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
