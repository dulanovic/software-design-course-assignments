package forme;

import domen.Kurs;
import domen.Polaznik;
import domen.PolaznikKurs;
import domen.VrstaKursa;
import forme.model.PolaznikKursTableModel;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.TableColumn;
import komunikacija.Komunikacija;
import transfer.TransferObjekatOdgovor;
import transfer.TransferObjekatZahtev;
import util.Konstante;

public class FrmGlavna extends javax.swing.JFrame {

    private final DateFormat df = new SimpleDateFormat("dd.MM.yyyy");

    /**
     * Creates new form FrmGlavna
     */
    public FrmGlavna() {
        initComponents();
        srediFormu();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jtxtNazivKursa = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jtxtMaxBrojPolaznika = new javax.swing.JTextField();
        jtxtDatumOd = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jtxtDatumDo = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jcbVrstaKursa = new javax.swing.JComboBox();
        jbtnDodajPolaznika = new javax.swing.JButton();
        jbtnObrisiPolaznika = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtblPolaznik = new javax.swing.JTable();
        jbtnSacuvaj = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("[FON] Unos kursa");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jLabel1.setText("Naziv kursa:");

        jLabel2.setText("Max broj polaznika:");

        jLabel3.setText("Datum od (dd.MM.yyyy):");

        jLabel4.setText("Datum do (dd.MM.yyyy):");

        jLabel5.setText("Vrsta kursa:");

        jbtnDodajPolaznika.setText("Dodaj polaznika");
        jbtnDodajPolaznika.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnDodajPolaznikaActionPerformed(evt);
            }
        });

        jbtnObrisiPolaznika.setText("Obrisi polaznika");
        jbtnObrisiPolaznika.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnObrisiPolaznikaActionPerformed(evt);
            }
        });

        jtblPolaznik.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jtblPolaznik);

        jbtnSacuvaj.setText("Sacuvaj kurs");
        jbtnSacuvaj.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnSacuvajActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                        .addComponent(jtxtNazivKursa, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jtxtMaxBrojPolaznika, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jtxtDatumOd, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jtxtDatumDo, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                            .addComponent(jcbVrstaKursa, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jbtnSacuvaj)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jbtnDodajPolaznika)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jbtnObrisiPolaznika)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jtxtNazivKursa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jtxtMaxBrojPolaznika, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jtxtDatumOd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jtxtDatumDo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jcbVrstaKursa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbtnDodajPolaznika)
                    .addComponent(jbtnObrisiPolaznika))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jbtnSacuvaj)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbtnDodajPolaznikaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnDodajPolaznikaActionPerformed
        try {
            String naziv = jtxtNazivKursa.getText();
            int maxBrojPolaznika = Integer.parseInt(jtxtMaxBrojPolaznika.getText());
            Date datumOd = df.parse(jtxtDatumOd.getText());
            Date datumDo = df.parse(jtxtDatumDo.getText());
            VrstaKursa vrsta = (VrstaKursa) jcbVrstaKursa.getSelectedItem();
            Kurs k = new Kurs(naziv, maxBrojPolaznika, datumOd, datumDo, vrsta);
            PolaznikKurs pk = new PolaznikKurs();
            pk.setKurs(k);
            PolaznikKursTableModel model = (PolaznikKursTableModel) jtblPolaznik.getModel();
            model.dodajRed(pk);
        } catch (ParseException pex) {
            pex.printStackTrace();
        }
    }//GEN-LAST:event_jbtnDodajPolaznikaActionPerformed

    private void jbtnObrisiPolaznikaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnObrisiPolaznikaActionPerformed
        int red = jtblPolaznik.getSelectedRow();
        if (red == -1) {
            JOptionPane.showMessageDialog(this, "Morate odabrati partnera!");
        }
        PolaznikKursTableModel model = (PolaznikKursTableModel) jtblPolaznik.getModel();
        model.obrisiRed(red);
    }//GEN-LAST:event_jbtnObrisiPolaznikaActionPerformed

    private void jbtnSacuvajActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnSacuvajActionPerformed
        try {
            boolean validacija = izvrsiValidaciju();
            if (!validacija) {
                JOptionPane.showMessageDialog(this, "Podaci nisu uneti u isprvnom formatu! Ponovite unos.", "Sistemska poruka", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String naziv = jtxtNazivKursa.getText();
            int maxBrojPolaznika = Integer.parseInt(jtxtMaxBrojPolaznika.getText());
            Date datumOd = df.parse(jtxtDatumOd.getText());
            Date datumDo = df.parse(jtxtDatumDo.getText());
            VrstaKursa vrsta = (VrstaKursa) jcbVrstaKursa.getSelectedItem();
            PolaznikKursTableModel model = (PolaznikKursTableModel) jtblPolaznik.getModel();
            Kurs k = model.getKurs();
            k.setNaziv(naziv);
            k.setMaxBrojPolaznika(maxBrojPolaznika);
            k.setDatumOd(datumOd);
            k.setDatumDo(datumDo);
            k.setVrsta(vrsta);

            TransferObjekatZahtev toz1 = new TransferObjekatZahtev();
            toz1.setOperacija(Konstante.SACUVAJ_KURS);
            toz1.setParametar(k);
            Komunikacija.getInstance().posaljiZahtev(toz1);
            TransferObjekatOdgovor too1 = Komunikacija.getInstance().primiOdgovor();

            JOptionPane.showMessageDialog(this, "Kurs je uspesno sacuvan u bazi.", "Sistemska poruka", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Neuspesno cuvanje u bazi.", "Sistemska poruka", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jbtnSacuvajActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        try {
            TransferObjekatZahtev toz = new TransferObjekatZahtev();
            toz.setOperacija(Konstante.KRAJ_RADA);
            Komunikacija.getInstance().posaljiZahtev(toz);

        } catch (IOException ioex) {
            ioex.printStackTrace();
        }
    }//GEN-LAST:event_formWindowClosing

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrmGlavna.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmGlavna.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmGlavna.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmGlavna.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmGlavna().setVisible(true);
            }
        });
    }

    private void srediFormu() {
        try {
            this.setTitle("[FON] Unos kursa");
            
            TransferObjekatZahtev toz1 = new TransferObjekatZahtev();
            toz1.setOperacija(Konstante.VRATI_VRSTE);
            Komunikacija.getInstance().posaljiZahtev(toz1);
            TransferObjekatOdgovor too1 = Komunikacija.getInstance().primiOdgovor();
            List<VrstaKursa> listaKurseva = (List<VrstaKursa>) too1.getRezultat();
            jcbVrstaKursa.setModel(new DefaultComboBoxModel(listaKurseva.toArray()));

            TransferObjekatZahtev toz2 = new TransferObjekatZahtev();
            toz2.setOperacija(Konstante.VRATI_POLAZNIKE);
            Komunikacija.getInstance().posaljiZahtev(toz2);
            TransferObjekatOdgovor too2 = Komunikacija.getInstance().primiOdgovor();
            List<Polaznik> listaPolaznika = (List<Polaznik>) too2.getRezultat();

            PolaznikKursTableModel model = new PolaznikKursTableModel(new Kurs());
            jtblPolaznik.setModel(model);

            JComboBox jcbPolaznik = new JComboBox();
            TableColumn tcPolaznik = jtblPolaznik.getColumnModel().getColumn(1);
            tcPolaznik.setCellEditor(new DefaultCellEditor(jcbPolaznik));
            jcbPolaznik.setModel(new DefaultComboBoxModel(listaPolaznika.toArray()));

            jcbVrstaKursa.setSelectedItem(null);
            jcbPolaznik.setSelectedItem(null);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex);
        }

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jbtnDodajPolaznika;
    private javax.swing.JButton jbtnObrisiPolaznika;
    private javax.swing.JButton jbtnSacuvaj;
    private javax.swing.JComboBox jcbVrstaKursa;
    private javax.swing.JTable jtblPolaznik;
    private javax.swing.JTextField jtxtDatumDo;
    private javax.swing.JTextField jtxtDatumOd;
    private javax.swing.JTextField jtxtMaxBrojPolaznika;
    private javax.swing.JTextField jtxtNazivKursa;
    // End of variables declaration//GEN-END:variables

    private boolean izvrsiValidaciju() {
        try {
            Date datumOd = df.parse(jtxtDatumOd.getText());
            Date datumDo = df.parse(jtxtDatumDo.getText());
            Date datumTekuci = new Date();
            int maxBrojPolaznika = Integer.parseInt(jtxtMaxBrojPolaznika.getText());
            PolaznikKursTableModel model = (PolaznikKursTableModel) jtblPolaznik.getModel();

            if (jtxtNazivKursa.getText().equals("") || jtxtMaxBrojPolaznika.getText().equals("") || jtxtDatumOd.getText().equals("") || jtxtDatumDo.getText().equals("") || jcbVrstaKursa.getSelectedItem() == null || datumOd.after(datumDo) || datumDo.after(datumTekuci) || model.getKurs().getListaPolaznikKurs().size() < 1 || model.getKurs().getListaPolaznikKurs().size() > maxBrojPolaznika) {
                return false;
            }
            return true;
        } catch (ParseException pex) {
            pex.printStackTrace();
            return false;
        }
    }
}
