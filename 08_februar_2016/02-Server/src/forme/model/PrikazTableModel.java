package forme.model;

import domen.Klijent;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class PrikazTableModel extends AbstractTableModel {

    private List<Klijent> lista;
    private String[] kolone = {"Klijent", "Ukupan iznos racuna"};

    public PrikazTableModel(List<Klijent> lista) {
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
        Klijent k = lista.get(i);
        switch (i1) {
            case 0:
                return k.getNaziv();
            case 1:
                return k.getUkupanIznosRacuna();
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
