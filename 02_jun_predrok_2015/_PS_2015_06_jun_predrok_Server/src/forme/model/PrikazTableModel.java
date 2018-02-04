package forme.model;

import domen.Drzava;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class PrikazTableModel extends AbstractTableModel {

    private List<Drzava> lista;
    private String[] kolone = {"Rang", "Drzava", "Broj poena"};

    public PrikazTableModel(List<Drzava> lista) {
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
        Drzava d = lista.get(i);
        switch (i1) {
            case 0:
                return d.getRang();
            case 1:
                return d.getNaziv();
            case 2:
                return d.getBrojPoena();
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
