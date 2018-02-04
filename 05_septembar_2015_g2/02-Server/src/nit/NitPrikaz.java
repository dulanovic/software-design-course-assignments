package nit;

import domen.Kandidat;
import forme.model.PrikazTableModel;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTable;
import poslovnalogika.Kontroler;

public class NitPrikaz extends Thread {

    private boolean aktivna;
    private JTable jtblPrikaz;
    private JComboBox jcbPretraga;

    public NitPrikaz(JTable jtblPrikaz, JComboBox jcbPretraga) throws Exception {
        this.aktivna = true;
        this.jtblPrikaz = jtblPrikaz;
        this.jcbPretraga = jcbPretraga;
        this.jcbPretraga.setModel(new DefaultComboBoxModel(Kontroler.getInstance().vratiStudijskePrograme().toArray()));
    }

    @Override
    public void run() {
        while (aktivna) {
            try {

                String kriterijum = jcbPretraga.getSelectedItem().toString();
                List<Kandidat> lista = Kontroler.getInstance().vratiListuZaServer(kriterijum);
                List<Kandidat> listaZaPrikaz = Kandidat.rangiraj(lista);

                for (Kandidat k : listaZaPrikaz) {
                    System.out.println(k.getRang() + ". " + k.getKandidatImePrezime());
                }

                PrikazTableModel model = new PrikazTableModel(listaZaPrikaz);
                jtblPrikaz.setModel(model);

                Thread.sleep(3000);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

}
