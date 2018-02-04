package forme.model;

import domen.IstorijaStatusaRada;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class PrikazTableModel extends AbstractTableModel {

    private List<IstorijaStatusaRada> lista;
    private String[] kolone = {"Profesor", "Student", "Broj indeksa", "Godina upisa", "Status rada"};

    public PrikazTableModel(List<IstorijaStatusaRada> lista) {
        this.lista = lista;
    }

    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public Object getValueAt(int i, int i1) {
        IstorijaStatusaRada isr = lista.get(i);
        switch (i1) {
            case 0:
                return isr.getProfesor();
            case 1:
                return isr.getStudent();
            case 2:
                return isr.getBrojIndeksa();
            case 3:
                return isr.getGodinaUpisa();
            case 4:
                return isr.getStatus();
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
