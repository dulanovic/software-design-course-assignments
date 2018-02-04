package forme.model;

import domen.Reprezentacija;
import domen.Utakmica;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class UtakmicaTableModel extends AbstractTableModel {

    private List<Utakmica> lista;
    private final String[] kolone = new String[]{"Grupa", "Domacin", "Gost", "Golova domacin", "Golova gost"};

    public UtakmicaTableModel(List<Utakmica> lista) {
        this.lista = lista;
    }

    @Override
    public int getRowCount() {
        if (lista == null) {
            return 0;
        }
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Utakmica u = lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return u.getGrupa();
            case 1:
                return u.getDomacin();
            case 2:
                return u.getGost();
            case 3:
                return u.getGolovaDomacin();
            case 4:
                return u.getGolovaGost();
            default:
                return "N/A";
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Utakmica u = lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                u.setGrupa((String) aValue);
                break;
            case 1:
                u.setDomacin((Reprezentacija) aValue);
                break;
            case 2:
                u.setGost((Reprezentacija) aValue);
                break;
            case 3:
                u.setGolovaDomacin(Integer.parseInt((String) aValue));
                break;
            case 4:
                u.setGolovaGost(Integer.parseInt((String) aValue));
                break;
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true;
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

    public Utakmica vratiUtakmicu(int red) {
        return lista.get(red);
    }
    
    public void obrisiRed(int red) {
        lista.remove(red);
        fireTableDataChanged();
    }
    
    public List<Utakmica> vratiListu() {
        return lista;
    }
}
