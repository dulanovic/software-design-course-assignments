package forme.model;

import domen.Kandidat;
import domen.PrijemniIspit;
import domen.StudijskiProgram;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class PrijemniIspitTableModel extends AbstractTableModel {

    private List<PrijemniIspit> lista;
    private String[] kolone = {"Studijski program", "Kandidat", "Poeni skola", "Poeni prijemni", "Poeni ukupno"};

    public PrijemniIspitTableModel(List<PrijemniIspit> lista) {
        this.lista = lista;
    }

    @Override
    public int getRowCount() {
        if (lista == null) {
            return 0;
        } else {
            return lista.size();
        }
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public Object getValueAt(int i, int i1) {
        PrijemniIspit pi = lista.get(i);
        switch (i1) {
            case 0:
                return pi.getStudijskiProgram();
            case 1:
                return pi.getKandidat();
            case 2:
                return pi.getKandidat().getBrojPoenaSkola();
            case 3:
                return pi.getBrojPoenaPrijemni();
            case 4:
                return (pi.getKandidat().getBrojPoenaSkola() * 0.4) + (pi.getBrojPoenaPrijemni() * 0.6);
            default:
                return "N/A";
        }
    }

    @Override
    public void setValueAt(Object o, int i, int i1) {
        PrijemniIspit pi = lista.get(i);
        switch (i1) {
            case 0:
                pi.setStudijskiProgram((StudijskiProgram) o);
                break;
            case 1:
                pi.setKandidat((Kandidat) o);
                break;
            case 2:
                pi.getKandidat().setBrojPoenaSkola(Double.parseDouble(String.valueOf(o)));
                fireTableCellUpdated(i, i1 + 2);
                break;
            case 3:
                pi.setBrojPoenaPrijemni(Double.parseDouble(String.valueOf(o)));
                fireTableCellUpdated(i, i1 + 1);
                break;
        }
    }

    @Override
    public boolean isCellEditable(int i, int i1) {
        return false;
    }

    @Override
    public String getColumnName(int i) {
        return kolone[i];
    }

    public void dodajRed(PrijemniIspit pi) {
        lista.add(pi);
        fireTableDataChanged();
    }

    public void obrisiRed(int red) {
        lista.remove(red);
        fireTableDataChanged();
    }

    public List<PrijemniIspit> vratiListu() {
        return lista;
    }
}
