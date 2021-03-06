/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forme;

import domen.Dezurstvo;
import domen.IspitniRok;
import domen.Nastavnik;
import domen.Predmet;
import forme.model.DezurstvoTableModel;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;
import transfer.TransferObjekatOdgovor;
import transfer.TransferObjekatZahtev;
import util.Konst;

/**
 *
 * @author Korisnik
 */
public class FrmKlijent extends javax.swing.JFrame {

    private DateFormat df = new SimpleDateFormat("dd.MM.yyyy hh:mm:ss");

    /**
     * Creates new form FrmKlijent
     */
    public FrmKlijent() {
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
        jcbIspitniRok = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        jcbNastavnik = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        jcbPredmet = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        jtxtDatum = new javax.swing.JTextField();
        jbtnDodaj = new javax.swing.JButton();
        jbtnObrisi = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtblDezurstva = new javax.swing.JTable();
        jbtnSacuvaj = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jLabel1.setText("Ispitni rok:");

        jLabel2.setText("Nastavnik:");

        jLabel3.setText("Predmet:");

        jLabel4.setText("Datum (dd.MM.yyyy hh:mm:ss):");

        jbtnDodaj.setText("Dodaj dezurstvo");
        jbtnDodaj.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnDodajActionPerformed(evt);
            }
        });

        jbtnObrisi.setText("Obrisi dezurstvo");
        jbtnObrisi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnObrisiActionPerformed(evt);
            }
        });

        jtblDezurstva.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jtblDezurstva);

        jbtnSacuvaj.setText("Sacuvaj dezurstva");
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
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel2))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jcbNastavnik, 0, 325, Short.MAX_VALUE)
                            .addComponent(jcbPredmet, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jcbIspitniRok, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtxtDatum))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jbtnDodaj)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jbtnObrisi))
                            .addComponent(jbtnSacuvaj))
                        .addGap(0, 152, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jcbIspitniRok, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jcbNastavnik, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jcbPredmet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtxtDatum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbtnDodaj)
                    .addComponent(jbtnObrisi))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jbtnSacuvaj)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbtnDodajActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnDodajActionPerformed
        try {
            IspitniRok iR = (IspitniRok) jcbIspitniRok.getSelectedItem();
            Nastavnik n = (Nastavnik) jcbNastavnik.getSelectedItem();
            Predmet p = (Predmet) jcbPredmet.getSelectedItem();
            Date datum = df.parse(jtxtDatum.getText());

            Dezurstvo d = new Dezurstvo(datum, iR, n, p);

            DezurstvoTableModel model = (DezurstvoTableModel) jtblDezurstva.getModel();
            model.dodajDezurstvo(d);
        } catch (ParseException ex) {

        }

    }//GEN-LAST:event_jbtnDodajActionPerformed

    private void jbtnObrisiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnObrisiActionPerformed
        int red = jtblDezurstva.getSelectedRow();
        if (red == -1) {
            JOptionPane.showMessageDialog(this, "Niste odbrali red!");
        }
        DezurstvoTableModel model = (DezurstvoTableModel) jtblDezurstva.getModel();
        model.obrisiDezurstvo(red);
    }//GEN-LAST:event_jbtnObrisiActionPerformed

    private void jbtnSacuvajActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnSacuvajActionPerformed
        try {
            DezurstvoTableModel model = (DezurstvoTableModel) jtblDezurstva.getModel();
            List<Dezurstvo> lista = model.vratiListu();

            TransferObjekatZahtev toz = new TransferObjekatZahtev();
            toz.setOperacija(Konst.VRATI_PRIMARNI_KLJUC_DEZURSTVO);
            Komunikacija.getInstance().posaljiZahtev(toz);
            TransferObjekatOdgovor too = Komunikacija.getInstance().primiOdgovor();
            int pkD = (int) too.getRezultat();

            for (Dezurstvo d : lista) {
                d.setDezurstvoID(pkD);
                pkD++;
            }

            TransferObjekatZahtev toz1 = new TransferObjekatZahtev();
            toz1.setOperacija(Konst.SACUVAJ_DEZURSTVA);
            toz1.setParametar(lista);
            Komunikacija.getInstance().posaljiZahtev(toz1);

            JOptionPane.showMessageDialog(this, "Uspesno cuvanje dezurstava!");
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Neuspesno cuvanje dezurstava!");
        }

    }//GEN-LAST:event_jbtnSacuvajActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        try {
            TransferObjekatZahtev toz = new TransferObjekatZahtev();
            toz.setOperacija(Konst.KRAJ_RADA);
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
            java.util.logging.Logger.getLogger(FrmKlijent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmKlijent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmKlijent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmKlijent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmKlijent().setVisible(true);
            }
        });
    }

    private void srediFormu() {
        try {
            this.setTitle("[FON] FONDEZ - klijent");

            DezurstvoTableModel model = new DezurstvoTableModel(new ArrayList<Dezurstvo>());
            jtblDezurstva.setModel(model);

            TransferObjekatZahtev toz1 = new TransferObjekatZahtev();
            toz1.setOperacija(Konst.VRATI_ISPITNE_ROKOVE);
            Komunikacija.getInstance().posaljiZahtev(toz1);
            TransferObjekatOdgovor too1 = Komunikacija.getInstance().primiOdgovor();
            List<IspitniRok> listaIR = (List<IspitniRok>) too1.getRezultat();

            TransferObjekatZahtev toz2 = new TransferObjekatZahtev();
            toz2.setOperacija(Konst.VRATI_NASTAVNIKE);
            Komunikacija.getInstance().posaljiZahtev(toz2);
            TransferObjekatOdgovor too2 = Komunikacija.getInstance().primiOdgovor();
            List<Nastavnik> listaN = (List<Nastavnik>) too2.getRezultat();

            TransferObjekatZahtev toz3 = new TransferObjekatZahtev();
            toz3.setOperacija(Konst.VRATI_PREDMETE);
            Komunikacija.getInstance().posaljiZahtev(toz3);
            TransferObjekatOdgovor too3 = Komunikacija.getInstance().primiOdgovor();
            List<Predmet> listaP = (List<Predmet>) too3.getRezultat();

            jcbIspitniRok.setModel(new DefaultComboBoxModel(listaIR.toArray()));
            jcbNastavnik.setModel(new DefaultComboBoxModel(listaN.toArray()));
            jcbPredmet.setModel(new DefaultComboBoxModel(listaP.toArray()));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Neuspesna inicijalizacija forme i njenih komponenti!");
        }

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jbtnDodaj;
    private javax.swing.JButton jbtnObrisi;
    private javax.swing.JButton jbtnSacuvaj;
    private javax.swing.JComboBox jcbIspitniRok;
    private javax.swing.JComboBox jcbNastavnik;
    private javax.swing.JComboBox jcbPredmet;
    private javax.swing.JTable jtblDezurstva;
    private javax.swing.JTextField jtxtDatum;
    // End of variables declaration//GEN-END:variables
}
