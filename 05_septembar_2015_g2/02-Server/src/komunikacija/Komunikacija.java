package komunikacija;

import domen.Kandidat;
import domen.PrijemniIspit;
import domen.StudijskiProgram;
import forme.PnlServer;
import java.awt.Window;
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
import util.Konst;

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
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            TransferObjekatZahtev toz = (TransferObjekatZahtev) in.readObject();
            TransferObjekatOdgovor too = new TransferObjekatOdgovor();
            try {
                System.out.println("O: " + toz.getOperacija());
                switch (toz.getOperacija()) {
                    case Konst.VRATI_STUDIJSKE_PROGRAME:
                        List<StudijskiProgram> listaStudijskihPrograma = Kontroler.getInstance().vratiStudijskePrograme();
                        too.setRezultat(listaStudijskihPrograma);
                        break;
                    case Konst.VRATI_KANDIDATE:
                        List<Kandidat> listaKandidata = Kontroler.getInstance().vratiKandidate();
                        too.setRezultat(listaKandidata);
                        break;
                    case Konst.SACUVAJ_PRIJEMNI_ISPIT:
                        List<PrijemniIspit> listaPrijemnihIspita = (List<PrijemniIspit>) toz.getParametar();
                        Kontroler.getInstance().sacuvajPrijemneIspite(listaPrijemnihIspita);
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
        JFrame f = new JFrame();
        PnlServer p = new PnlServer();
        f.add(p);
        f.setTitle("Univerzitet u Beogradu - Prijemni 2015.");
        f.setSize(p.getPreferredSize());
        f.setVisible(true);
        new Komunikacija().pokreniServer();
    }
}
