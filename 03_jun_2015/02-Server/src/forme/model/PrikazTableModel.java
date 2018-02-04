package forme.model;

import domen.Model;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class PrikazTableModel extends AbstractTableModel {

    private List<Model> lista;
    private String[] kolone = {"Model", "Broj revija", "Ukupna zarada", "Ukupan broj sati"};

    public PrikazTableModel(List<Model> lista) {
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
        Model m = lista.get(i);
        switch (i1) {
            case 0:
                return m.getModelImePrezime();
            case 1:
                return m.getBrojRevija();
            case 2:
                return m.getUkupnaZarada();
            case 3:
                return m.getUkupanBrojSati();
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
