package forme.model;

import domen.Nastavnik;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class PrikazTableModel extends AbstractTableModel {

    private List<Nastavnik> lista;
    private final String[] kolone = new String[]{"Nastavnik", "Ukupan br. dezurstava", "Ukupan broj sati", "Ukupno za isplatu (din)"};

    public PrikazTableModel(List<Nastavnik> lista) {
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
        Nastavnik n = lista.get(i);
        switch (i1) {
            case 0:
                return n.getNastavnikID();
            case 1:
                return n.getUkupanBrojDezurstava();
            case 2:
                return n.getUkupanBrojSati();
            case 3:
                return n.getUkupnoZaIsplatu();
            default:
                return "N/A";
        }
    }

}
