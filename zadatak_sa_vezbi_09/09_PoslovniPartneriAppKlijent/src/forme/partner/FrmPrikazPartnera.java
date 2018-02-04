/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forme.partner;

import domen.PoslovniPartner;
import forme.partner.model.PartnerTableModel;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

/**
 *
 * @author student1
 */
public class FrmPrikazPartnera extends javax.swing.JPanel {

    /**
     * Creates new form FrmPrikazPartnera
     */
    public FrmPrikazPartnera() {
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
        jcbPartner = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtblPartner = new javax.swing.JTable();
        jbtnDetalji = new javax.swing.JButton();

        jLabel1.setText("Partneri:");

        jcbPartner.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jtblPartner.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "PartnerID", "Naziv", "PIB", "Matični broj", "Datum", "Žiro račun", "Ulica", "Broj", "Mesto"
            }
        ));
        jScrollPane1.setViewportView(jtblPartner);

        jbtnDetalji.setText("Detalji");
        jbtnDetalji.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnDetaljiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jcbPartner, 0, 334, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jbtnDetalji)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jcbPartner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jbtnDetalji)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jbtnDetaljiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnDetaljiActionPerformed
        int red = jtblPartner.getSelectedRow();
        if (red == -1) {
            JOptionPane.showMessageDialog(this, "Morate odabrati partnera!");
        } else {
            PartnerTableModel model = (PartnerTableModel) jtblPartner.getModel();
            PoslovniPartner pp = model.getParnter(red);
            // Kontroler.getInstance().put("izabrani_partner", pp);
            FrmUnosPartnera f = new FrmUnosPartnera();
            JDialog dialog = (JDialog) SwingUtilities.getWindowAncestor(this);
            dialog.dispose();
            f.setVisible(true);
        }
    }//GEN-LAST:event_jbtnDetaljiActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jbtnDetalji;
    private javax.swing.JComboBox jcbPartner;
    private javax.swing.JTable jtblPartner;
    // End of variables declaration//GEN-END:variables

    private void srediFormu() {
        try {
            // List<PoslovniPartner> lp = Kontroler.getInstance().vratiPartnere();
            List<PoslovniPartner> lp = new ArrayList<>();
            jcbPartner.setModel(new DefaultComboBoxModel(lp.toArray()));
//        DefaultTableModel model = (DefaultTableModel) jtblPartner.getModel();
//        for (PoslovniPartner p : lp) {
//            Object[] red = new Object[9];
//            red[0] = p.getPartnerID();
//            red[1] = p.getNaziv();
//            red[2] = p.getPib();
//            red[3] = p.getMaticniBroj();
//            red[4] = p.getDatumOsnivanja();
//            red[5] = p.getZiroRacun();
//            red[6] = p.getUlica();
//            red[7] = p.getBroj();
//            red[8] = p.getMesto();
//            model.addRow(red);
//        }
            PartnerTableModel model = new PartnerTableModel(lp);
            jtblPartner.setModel(model);
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }
}