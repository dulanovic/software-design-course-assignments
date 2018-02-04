package forme.model;

import domen.Komponenta;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class PrikazTableModel extends AbstractTableModel {

    private List<Komponenta> lista;
    private String[] kolone = {"Komponenta", "Tip", "Broj ugradjenih komada", "Iznos"};

    public PrikazTableModel(List<Komponenta> lista) {
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
        Komponenta k = lista.get(i);
        switch (i1) {
            case 0:
                return k.getNaziv();
            case 1:
                return k.getTip();
            case 2:
                return k.getBrojUgradjenihKomada();
            case 3:
                return k.getIznos();
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
