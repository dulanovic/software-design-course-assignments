package forme.racun.model;

import domen.Proizvod;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class ProizvodTableModel extends AbstractTableModel {

    private List<Proizvod> lista;
    private String[] kolone = new String[]{"ProizvodID", "Naziv", "Cena", "Broj racuna", "Ukupna kolicina", "Ukupan iznos"};

    public ProizvodTableModel(List<Proizvod> lista) {
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
    public Object getValueAt(int rowIndex, int columnIndex) {
        Proizvod p = lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return p.getProizvodID();
            case 1:
                return p.getNaziv();
            case 2:
                return p.getCena();
            case 3:
                return p.getBrojRacuna();
            case 4:
                return p.getUkupnaKolicina();
            case 5:
                return p.getUkupanIznos();
            default:
                return "N/A";
        }
    }

    @Override
    public void setValueAt(Object o, int rowIndex, int columnIndex) {
        Proizvod p = lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                p.setProizvodID(Integer.parseInt((String) o));
                break;
            case 1:
                p.setNaziv((String) o);
                break;
            case 2:
                p.setCena(Double.parseDouble((String) o));
                break;
            case 3:
                p.setBrojRacuna(Integer.parseInt((String) o));
                break;
            case 4:
                p.setUkupnaKolicina(Integer.parseInt((String) o));
                break;
            case 5:
                p.setUkupanIznos(Double.parseDouble((String) o));
                break;
        }
    }

    @Override
    public boolean isCellEditable(int i, int i1) {
        return false;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return kolone[columnIndex];
    }

}
