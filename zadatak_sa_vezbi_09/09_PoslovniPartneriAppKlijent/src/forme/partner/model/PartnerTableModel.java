/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forme.partner.model;

import domen.Mesto;
import domen.PoslovniPartner;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author student1
 */
public class PartnerTableModel extends AbstractTableModel {

    private List<PoslovniPartner> lp;
    private final String[] kolone = new String[]{"PartnerID", "Naziv", "PIB", "MB", "Datum", "ZR", "Ulica", "Broj", "Mesto"};
    private final DateFormat df = new SimpleDateFormat("dd.MM.yyyy");

    public PartnerTableModel(List<PoslovniPartner> lp) {
        this.lp = lp;
    }

    @Override
    public int getRowCount() {
        if (lp == null) {
            return 0;
        }
        return lp.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        PoslovniPartner pp = lp.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return pp.getPartnerID();
            case 1:
                return pp.getNaziv();
            case 2:
                return pp.getPib();
            case 3:
                return pp.getMaticniBroj();
            case 4:
                return pp.getDatumOsnivanja();
            case 5:
                return pp.getZiroRacun();
            case 6:
                return pp.getUlica();
            case 7:
                return pp.getBroj();
            case 8:
                return pp.getMesto();
            default:
                return "n/a";
        }
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        PoslovniPartner pp = lp.get(rowIndex);
        switch (columnIndex) {
            case 0:
                pp.setPartnerID(Integer.parseInt((String) aValue));
                break;
            case 1:
                pp.setNaziv((String) aValue);
                break;
            case 2:
                pp.setPib(Integer.parseInt((String) aValue));
                break;
            case 3:
                pp.setMaticniBroj(Integer.parseInt((String) aValue));
                break;
            case 4:
                try {
                    pp.setDatumOsnivanja(df.parse((String) aValue));
                } catch (ParseException ex) {
                    ex.printStackTrace();
                }
                break;
            case 5:
                pp.setZiroRacun((String) aValue);
                break;
            case 6:
                pp.setUlica((String) aValue);
                break;
            case 7:
                pp.setBroj((String) aValue);
                break;
            case 8:
                pp.setMesto((Mesto) aValue);
                break;
        }
    }

    public PoslovniPartner getParnter(int red) {
        return lp.get(red);
    }
    
    public void dodajRed() {
        lp.add(new PoslovniPartner());
        System.out.println("Dodat je novi partner!");
        fireTableDataChanged();
    }
    
    public void obrisiRed(int red) {
        lp.remove(red);
        System.out.println("Obrisan je red!");
        fireTableDataChanged();
    }
    
    public List<PoslovniPartner> vratiPartnere() {
        return lp;
    }

}
