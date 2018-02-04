package forme.model;

import domen.Kurs;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class PrikazTableModel extends AbstractTableModel {

    private List<Kurs> lista;
    private final String[] kolone = new String[]{"Kurs", "Max broj polaznika", "Broj prijavljenih polaznika"};

    public PrikazTableModel(List<Kurs> lista) {
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
    public Object getValueAt(int i, int i1) {
        Kurs k = lista.get(i);
        switch (i1) {
            case 0:
                return k.getNaziv();
            case 1:
                return k.getMaxBrojPolaznika();
            case 2:
                return k.getBrojPrijavljenihPolaznika();
            default:
                return "N/A";
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

}
