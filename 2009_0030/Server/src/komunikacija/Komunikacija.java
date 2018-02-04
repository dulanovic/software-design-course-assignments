package komunikacija;

import domen.Reprezentacija;
import domen.Utakmica;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
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
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            TransferObjekatZahtev toz = (TransferObjekatZahtev) in.readObject();
            TransferObjekatOdgovor too = new TransferObjekatOdgovor();
            try {
                System.out.println("Operacija: " + toz.getOperacija());
                switch (toz.getOperacija()) {
                    case Konstante.KRAJ_RADA:
                        kraj = true;
                        break;
                    case Konstante.VRATI_UTAKMICE:
                        List<Utakmica> listaUtakmica = Kontroler.getInstance().vratiUtakmice();
                        too.setRezultat(listaUtakmica);
                        break;
                    case Konstante.SACUVAJ_IZMENE:
                        List<Utakmica> listaIzmena = (List<Utakmica>) toz.getParametar();
                        Kontroler.getInstance().sacuvajUtakmice(listaIzmena);
                        break;
                    case Konstante.VRATI_REPREZENTACIJE:
                        List<Reprezentacija> listaReprezentacija = Kontroler.getInstance().vratiReprezentacije();
                        too.setRezultat(listaReprezentacija);
                        break;
                    case Konstante.OBRISI_UTAKMICE:
                        List<Utakmica> listaBrisanje = (List<Utakmica>) toz.getParametar();
                        Kontroler.getInstance().obrisiUtakmice(listaBrisanje);
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
        new Komunikacija().pokreniServer();
    }
}
