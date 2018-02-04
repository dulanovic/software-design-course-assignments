package forme.model;

import domen.Drzava;
import domen.Glasanje;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class GlasanjeTableModel extends AbstractTableModel {

    private List<Glasanje> lista;
    private String[] kolone = {"Od drzave", "Za drzavu", "Broj poena"};

    public GlasanjeTableModel(List<Glasanje> lista) {
        this.lista = lista;
    }

    @Override
    public int getRowCount() {
        if (lista.isEmpty()) {
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
        Glasanje g = lista.get(i);
        switch (i1) {
            case 0:
                return g.getOdDrzava();
            case 1:
                return g.getZaDrzava();
            case 2:
                return g.getBrojPoena();
            default:
                return "N/A";
        }
    }

    @Override
    public void setValueAt(Object o, int i, int i1) {
        Glasanje g = lista.get(i);
        switch (i1) {
            case 0:
                g.setOdDrzava((Drzava) o);
                break;
            case 1:
                g.setZaDrzava((Drzava) o);
                break;
            case 2:
                g.setBrojPoena(Integer.parseInt((String) o));
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

    public void dodajRed(Glasanje glasanje) {
        lista.add(glasanje);
        fireTableDataChanged();
    }

    public void obrisiRed(int red) {
        lista.remove(red);
        fireTableDataChanged();
    }
    
    public List<Glasanje> vratiListu() {
        return lista;
    }
}
