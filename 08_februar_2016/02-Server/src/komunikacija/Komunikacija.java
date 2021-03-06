package komunikacija;

import domen.Klijent;
import domen.Proizvod;
import domen.Racun;
import forme.PnlServer;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import javax.swing.JFrame;
import poslovnalogika.Kontroler;
import sun.awt.im.InputMethodJFrame;
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
                    case Konst.NADJI_RACUN:
                        int id = (int) toz.getParametar();
                        Racun r = Kontroler.getInstance().nadjiRacun(id);
                        too.setRezultat(r);
                        break;
                    case Konst.VRATI_KLIJENTE:
                        List<Klijent> listaK = Kontroler.getInstance().vratiKlijente();
                        too.setRezultat(listaK);
                        break;
                    case Konst.VRATI_PROIZVODE:
                        List<Proizvod> listaP = Kontroler.getInstance().vratiProizvode();
                        too.setRezultat(listaP);
                        break;
                    case Konst.SACUVAJ_RACUN:
                        Racun rC = (Racun) toz.getParametar();
                        Kontroler.getInstance().sacuvajRacun(rC);
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
        f.setSize(p.getPreferredSize());
        f.setTitle("[FON] Pregled racuna - server");
        f.setVisible(true);
        
        new Komunikacija().pokreniServer();
    }
}
