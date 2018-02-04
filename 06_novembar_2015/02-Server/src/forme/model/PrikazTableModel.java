package forme.model;

import domen.Knjiga;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class PrikazTableModel extends AbstractTableModel {

    private List<Knjiga> lista;
    private String[] kolone = {"Knjiga", "Autor", "Izdavac", "Broj prodatih primeraka", "Prihod"};

    public PrikazTableModel(List<Knjiga> lista) {
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
        Knjiga k = lista.get(i);
        switch (i1) {
            case 0:
                return k.getNaziv();
            case 1:
                return k.getAutor();
            case 2:
                return k.getIzdavac();
            case 3:
                return k.getBrojProdatihPrimeraka();
            case 4:
                return k.getPrihod();
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
