/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package komunikacija;

import domen.Mesto;
import domen.PoslovniPartner;
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

/**
 *
 * @author student1
 */
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
            TransferObjekatZahtev toZahtev = (TransferObjekatZahtev) inSocket.readObject();
            TransferObjekatOdgovor toOdgovor = new TransferObjekatOdgovor();
            try {
                System.out.println("O: " + toZahtev.getOperacija());
                switch (toZahtev.getOperacija()) {
                    case Konstante.VRATI_MESTA:
                        List<Mesto> lm = Kontroler.getInstance().vratiMesta();
                        toOdgovor.setRezultat(lm);
                        toOdgovor.setPoruka("Mesta su ucitana.");
                        break;
                    case Konstante.SACUVAJ_PARTNERA:
                        PoslovniPartner pp = (PoslovniPartner) toZahtev.getParametar();
                        Kontroler.getInstance().dodajPartnera(pp);
                        toOdgovor.setPoruka("Partner je uspesno sacuvan.");
                        break;
                }
            } catch (Exception ex) {
                toOdgovor.setPoruka(ex.getMessage());
                toOdgovor.setIzuzetak(ex);
            }
            posaljiOdgovor(toOdgovor);
        }
    }

    private void posaljiOdgovor(TransferObjekatOdgovor toOdgovor) throws IOException {
        ObjectOutputStream outSocket = new ObjectOutputStream(socket.getOutputStream());
        outSocket.writeObject(toOdgovor);
    }
    
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        new Komunikacija().pokreniServer();
    }
}
