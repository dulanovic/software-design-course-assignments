package komunikacija;

import domen.Brend;
import domen.Komponenta;
import domen.Konfiguracija;
import forme.PnlServer;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
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
        System.out.println("Server je podignut.");
        socket = ss.accept();
        System.out.println("Klijent se povezao.");
        izvrsenjeOperacija();
    }

    private void izvrsenjeOperacija() throws IOException, ClassNotFoundException {
        while (!kraj) {
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            TransferObjekatZahtev toz = (TransferObjekatZahtev) in.readObject();
            TransferObjekatOdgovor too = new TransferObjekatOdgovor();
            try {
                System.out.println("Operacija: " + toz.getOperacija());
                switch (toz.getOperacija()) {
                    case Konstante.VRATI_ID:
                        int id = Kontroler.getInstance().vratiID();
                        too.setRezultat(id);
                        break;
                    case Konstante.VRATI_BRENDOVE:
                        List<Brend> listaBrendova = Kontroler.getInstance().vratiBrendove();
                        too.setRezultat(listaBrendova);
                        break;
                    case Konstante.VRATI_KOMPONENTE:
                        List<Komponenta> listaKomponenti = Kontroler.getInstance().vratiKomponente();
                        too.setRezultat(listaKomponenti);
                        break;
                    case Konstante.SACUVAJ_KONFIGURACIJU:
                        Konfiguracija k = (Konfiguracija) toz.getParametar();
                        Kontroler.getInstance().sacuvajKonfiguraciju(k);
                        break;
                    case Konstante.KRAJ_RADA:
                        kraj = true;
                        break;
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            posaljiOdgovor(too);
        }

    }

    private void posaljiOdgovor(TransferObjekatOdgovor too) throws IOException {
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        out.writeObject(too);
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        JFrame f = new JFrame();
        PnlServer panel = new PnlServer();
        f.add(panel);
        f.setVisible(true);
        f.setSize(panel.getPreferredSize());
        new Komunikacija().pokreniServer();
    }
}
