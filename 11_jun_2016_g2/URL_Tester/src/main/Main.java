package main;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    public static void main(String[] args) {
        try {
            HttpURLConnection connection = null;

            String urlString = "https://stream-trailer-tushy.ssl-cdn.com/100332-CARTER-CRUISE/1476777466945/TUSHY_100332-CARTER_1080P.mp4?ri=2560&rs=2304&s=1477088786&e=1477095986&ip=24.135.41.24&h=81c6bc53bf1c0f3fd5090aa05fe0549";

            for (int i = 0; i < 10; i++) {
                urlString += i;
                URL myurl = new URL(urlString);
                connection = (HttpURLConnection) myurl.openConnection();
                urlString = urlString.substring(0, urlString.length()-1);
                connection.setRequestMethod("HEAD");
                int code = connection.getResponseCode();

                System.out.println(code + " - " + myurl);
            }

        } catch (MalformedURLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ProtocolException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
