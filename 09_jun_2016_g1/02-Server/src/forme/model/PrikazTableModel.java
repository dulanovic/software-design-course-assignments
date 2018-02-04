package forme.model;

import domen.Projekat;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class PrikazTableModel extends AbstractTableModel {

    private List<Projekat> lista;
    private String[] kolone = {"Projekat", "Vrsta projekta", "Broj angazovanih"};

    public PrikazTableModel(List<Projekat> lista) {
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
        Projekat p = lista.get(i);
        switch (i1) {
            case 0:
                return p.getNaziv();
            case 1:
                return p.getVrstaP();
            case 2:
                return p.getBrojAngaovanih();
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
