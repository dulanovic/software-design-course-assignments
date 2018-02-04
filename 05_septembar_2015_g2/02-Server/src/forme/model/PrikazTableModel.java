package forme.model;

import domen.Kandidat;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class PrikazTableModel extends AbstractTableModel {

    private List<Kandidat> lista;
    private String[] kolone = {"Rang", "Kandidat", "Poeni skola", "Poeni prijemni", "Poeni ukupno"};

    public PrikazTableModel(List<Kandidat> lista) {
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
        Kandidat k = lista.get(i);
        switch (i1) {
            case 0:
                return k.getRang();
            case 1:
                return k.getKandidatImePrezime();
            case 2:
                return k.getBrojPoenaSkola();
            case 3:
                return k.getBrojPoenaPrijemni();
            case 4:
                return k.getBrojPoenaUkupno();
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
