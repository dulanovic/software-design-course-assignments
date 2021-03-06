package forme;

import domen.Reprezentacija;
import domen.Utakmica;
import forme.model.UtakmicaTableModel;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.TableColumn;
import komunikacija.Komunikacija;
import transfer.TransferObjekatOdgovor;
import transfer.TransferObjekatZahtev;
import util.Konstante;

public class FrmKlijent extends javax.swing.JFrame {

    private List<Utakmica> listaBrisanje = new ArrayList<>();

    /**
     * Creates new form FrmKlijent
     */
    public FrmKlijent() {
        initComponents();
        SrediFormu();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtblUtakmica = new javax.swing.JTable();
        jbtnSacuvaj = new javax.swing.JButton();
        jbtnIzmeni = new javax.swing.JButton();
        jbtnObrisi = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Utakmice"));

        jtblUtakmica.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jtblUtakmica);

        jbtnSacuvaj.setText("Sacuvaj izmene");
        jbtnSacuvaj.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnSacuvajActionPerformed(evt);
            }
        });

        jbtnIzmeni.setText("Izmeni utakmicu");

        jbtnObrisi.setText("Obrisi utakmicu");
        jbtnObrisi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnObrisiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jbtnSacuvaj, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jbtnIzmeni)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jbtnObrisi)))
                        .addGap(0, 136, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbtnIzmeni)
                    .addComponent(jbtnObrisi))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jbtnSacuvaj)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        try {
            TransferObjekatZahtev toz = new TransferObjekatZahtev();
            toz.setOperacija(Konstante.KRAJ_RADA);
            Komunikacija.getInstance().posaljiZahtev(toz);
        } catch (IOException ex) {
            Logger.getLogger(FrmKlijent.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_formWindowClosing

    private void jbtnObrisiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnObrisiActionPerformed
        int red = jtblUtakmica.getSelectedRow();
        if (red == -1) {
            JOptionPane.showMessageDialog(this, "Morate odabrati utakmicu!");
        }
        UtakmicaTableModel model = (UtakmicaTableModel) jtblUtakmica.getModel();
        Utakmica u = model.vratiUtakmicu(red);
        this.listaBrisanje.add(u);
        model.obrisiRed(red);
        System.out.println(listaBrisanje.size());
    }//GEN-LAST:event_jbtnObrisiActionPerformed

    private void jbtnSacuvajActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnSacuvajActionPerformed
        try {
            UtakmicaTableModel model = (UtakmicaTableModel) jtblUtakmica.getModel();
            List<Utakmica> lista = model.vratiListu();

            TransferObjekatZahtev toz1 = new TransferObjekatZahtev();
            toz1.setOperacija(Konstante.SACUVAJ_IZMENE);
            toz1.setParametar(lista);
            Komunikacija.getInstance().posaljiZahtev(toz1);

            TransferObjekatZahtev toz2 = new TransferObjekatZahtev();
            toz2.setOperacija(Konstante.OBRISI_UTAKMICE);
            toz2.setParametar(listaBrisanje);
            Komunikacija.getInstance().posaljiZahtev(toz2);
        } catch (IOException ex) {
            Logger.getLogger(FrmKlijent.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jbtnSacuvajActionPerformed

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

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jbtnIzmeni;
    private javax.swing.JButton jbtnObrisi;
    private javax.swing.JButton jbtnSacuvaj;
    private javax.swing.JTable jtblUtakmica;
    // End of variables declaration//GEN-END:variables

    private void SrediFormu() {
        try {
            this.setTitle("[FON] Fudbal - klijentski program");

            TransferObjekatZahtev toz1 = new TransferObjekatZahtev();
            toz1.setOperacija(Konstante.VRATI_REPREZENTACIJE);
            Komunikacija.getInstance().posaljiZahtev(toz1);
            TransferObjekatOdgovor too1 = Komunikacija.getInstance().primiOdgovor();
            List<Reprezentacija> listaReprezentacija = (List<Reprezentacija>) too1.getRezultat();

            TransferObjekatZahtev toz2 = new TransferObjekatZahtev();
            toz2.setOperacija(Konstante.VRATI_UTAKMICE);
            Komunikacija.getInstance().posaljiZahtev(toz2);
            TransferObjekatOdgovor too2 = Komunikacija.getInstance().primiOdgovor();
            List<Utakmica> listaUtakmica = (List<Utakmica>) too2.getRezultat();

            UtakmicaTableModel model = new UtakmicaTableModel(listaUtakmica);
            jtblUtakmica.setModel(model);

            JComboBox jcbDomacin = new JComboBox();
            JComboBox jcbGost = new JComboBox();
            jcbDomacin.setModel(new DefaultComboBoxModel(listaReprezentacija.toArray()));
            jcbGost.setModel(new DefaultComboBoxModel(listaReprezentacija.toArray()));
            TableColumn tcDomacin = jtblUtakmica.getColumnModel().getColumn(1);
            tcDomacin.setCellEditor(new DefaultCellEditor(jcbDomacin));
            TableColumn tcGost = jtblUtakmica.getColumnModel().getColumn(2);
            tcGost.setCellEditor(new DefaultCellEditor(jcbGost));
        } catch (Exception ex) {
            Logger.getLogger(FrmKlijent.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
