package DevAppRevAct2_1;

import java.io.*;
import java.net.*;

public class Serveur {
    public static void main(String[] args) {
        try {
            // Réservation du port et attente de la connexion du client
            ServerSocket serverSocket = new ServerSocket(1234);
            Socket socket = serverSocket.accept();

            System.out.println("Client connecté");

            // Flux de réception des données du client
            InputStream is = socket.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader bfr = new BufferedReader(isr);

            // Lecture des données du client
            int op1 = Integer.parseInt(bfr.readLine());
            int op2 = Integer.parseInt(bfr.readLine());
            String operation = bfr.readLine();

            // Traitement des données (opération mathématique)
            int resultat = 0;
            switch (operation) {
                case "+":
                    resultat = op1 + op2;
                    break;
                case "-":
                    resultat = op1 - op2;
                    break;
                case "*":
                    resultat = op1 * op2;
                    break;
                case "/":
                    if (op2 != 0) {
                        resultat = op1 / op2;
                    } else {
                        System.out.println("Division par zéro détectée.");
                    }
                    break;
                default:
                    System.out.println("Opération non valide.");
            }

            // Flux d'envoi du résultat au client
            OutputStream os = socket.getOutputStream();
            PrintWriter pw = new PrintWriter(os, true);
            pw.println(resultat);

            // Fermeture des ressources
            serverSocket.close();
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
