package forme.model;

import domen.Reprezentacija;
import domen.Utakmica;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class UtakmicaTableModel extends AbstractTableModel {

    private List<Utakmica> lista;
    private String[] kolone = {"Grupa", "Domacin", "Gost", "Golova domacin", "Golova gost"};

    public UtakmicaTableModel(List<Utakmica> lista) {
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
        Utakmica u = lista.get(i);
        switch (i1) {
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
    public void setValueAt(Object o, int i, int i1) {
        Utakmica u = lista.get(i);
        switch (i1) {
            case 1:
                u.setDomacin((Reprezentacija) o);
                break;
            case 2:
                u.setGost((Reprezentacija) o);
                break;
            case 3:
                u.setGolovaDomacin(Integer.parseInt(String.valueOf(o)));
                break;
            case 4:
                u.setGolovaGost(Integer.parseInt(String.valueOf(o)));
                break;
        }
    }

    @Override
    public boolean isCellEditable(int i, int i1) {
        if (i1 == 0) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public String getColumnName(int i) {
        return kolone[i];
    }

    public Utakmica obrisiUtakmicu(int red) {
        Utakmica u = lista.get(red);
        lista.remove(red);
        fireTableDataChanged();
        return u;
    }

    public List<Utakmica> vratiListu() {
        return lista;
    }
    
    public Utakmica vratiUtakmicu(int red) {
        return lista.get(red);
    }
}
