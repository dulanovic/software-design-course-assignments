package komunikacija;

import domen.Kurs;
import domen.Polaznik;
import domen.VrstaKursa;
import forme.FrmServer;
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
        ServerSocket ss = new ServerSocket(9001);
        System.out.println("Server je podignut.");
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
                    case Konstante.VRATI_VRSTE:
                        List<VrstaKursa> listaVrsti = Kontroler.getInstance().vratiVrste();
                        too.setRezultat(listaVrsti);
                        too.setPoruka("Uspesno ucitavanje liste vrsta kurseva.");
                        break;
                    case Konstante.VRATI_POLAZNIKE:
                        List<Polaznik> listaPolaznika = Kontroler.getInstance().vratiPolaznike();
                        too.setRezultat(listaPolaznika);
                        too.setPoruka("Uspesno ucitavanje liste polaznika.");
                        break;
                    case Konstante.SACUVAJ_KURS:
                        Kurs k = (Kurs) toz.getParametar();
                        Kontroler.getInstance().sacuvajKurs(k);
                        too.setPoruka("Kurs je uspesno sacuvan.");
                        break;
                    case Konstante.KRAJ_RADA:
                        kraj = true;
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
        ObjectOutputStream outSocket = new ObjectOutputStream(socket.getOutputStream());
        outSocket.writeObject(too);
    }
    
    public static void main(String[] args) throws Exception {
        FrmServer forma = new FrmServer();
        forma.setVisible(true);
        
        new Komunikacija().pokreniServer();
    }
}
