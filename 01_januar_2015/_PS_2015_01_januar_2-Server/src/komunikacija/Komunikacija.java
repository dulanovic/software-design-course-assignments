package komunikacija;

import domen.Profesor;
import domen.Rad;
import domen.StatusRada;
import domen.Student;
import forme.PnlServer;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import javax.swing.JDialog;
import javax.swing.JFrame;
import poslovnalogika.Kontroler;
import transfer.TransferObjekatOdgovor;
import transfer.TransferObjekatZahtev;
import util.Konstante;

public class Komunikacija {

    private Socket socket;
    private boolean kraj = false;

    public void pokreniServer() throws IOException, ClassNotFoundException {
        ServerSocket ss = new ServerSocket(9000);
        System.out.println("Server je pokrenut.");
        socket = ss.accept();
        System.out.println("Klijent se povezao.");
        izvrsenjeOperacija();
    }

    private void izvrsenjeOperacija() throws IOException, ClassNotFoundException {
        while (!kraj) {
            ObjectInputStream inSocket = new ObjectInputStream(socket.getInputStream());
            TransferObjekatZahtev toz = (TransferObjekatZahtev) inSocket.readObject();
            TransferObjekatOdgovor too = new TransferObjekatOdgovor();
            try {
                System.out.println("O: " + toz.getOperacija());
                switch (toz.getOperacija()) {
                    case Konstante.VRATI_STUDENTE:
                        List<Student> listaStudenata = Kontroler.getInstance().vratiStudente();
                        too.setRezultat(listaStudenata);
                        break;
                    case Konstante.VRATI_PROFESORE:
                        List<Profesor> listaProfesora = Kontroler.getInstance().vratiProfesore();
                        too.setRezultat(listaProfesora);
                        break;
                    case Konstante.VRATI_STATUSE:
                        List<StatusRada> listaStatusa = Kontroler.getInstance().vratiStatuse();
                        too.setRezultat(listaStatusa);
                        break;
                    case Konstante.VRATI_KLJUC:
                        int key = Kontroler.getInstance().vratiKljuc();
                        too.setRezultat(key);
                        break;
                    case Konstante.SACUVAJ_RAD:
                        Rad rad = (Rad) toz.getParametar();
                        Kontroler.getInstance().sacuvajRad(rad);
                        break;
                }
            } catch (Exception ex) {
                too.setPoruka(ex.getMessage());
                too.setIzuzetak(ex);
            }
            posaljiOdgovor(too);
        }
    }

    private void posaljiOdgovor(TransferObjekatOdgovor too) throws IOException {
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        out.writeObject(too);
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        PnlServer p = new PnlServer();
        JFrame f = new JFrame("FrmServer");
        f.add(p);
        f.setVisible(true);
        f.setSize(p.getPreferredSize());
        new Komunikacija().pokreniServer();
    }
}
