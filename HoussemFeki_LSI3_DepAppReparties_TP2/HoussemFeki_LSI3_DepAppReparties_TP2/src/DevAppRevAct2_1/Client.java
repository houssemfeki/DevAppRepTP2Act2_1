package DevAppRevAct2_1;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try {
            // Connexion au serveur
            Socket socket = new Socket("localhost", 1234);

            // Flux d'envoi des données au serveur
            OutputStream os = socket.getOutputStream();
            PrintWriter pw = new PrintWriter(os, true);

            Scanner scanner = new Scanner(System.in);

            // Demande des opérandes et de l'opération à l'utilisateur
            System.out.print("Donner le premier opérande : ");
            int op1 = scanner.nextInt();
            scanner.nextLine(); // Consomme le caractère de fin de ligne

            System.out.print("\nDonner l'opération (+, -, *, /) : ");
            String operation = scanner.nextLine(); 
            System.out.print("\nDonner le deuxième opérande : ");
            int op2 = scanner.nextInt();
            scanner.nextLine(); 

            // Envoi des données au serveur
            pw.println(op1);
            pw.println(op2);
            pw.println(operation);

            // Flux de réception des données du serveur
            InputStream is = socket.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader bfr = new BufferedReader(isr);

            // Affichage du résultat reçu du serveur
            String resultat = bfr.readLine();
            System.out.println("\nRésultat : " + resultat);

            // Fermeture des ressources
            socket.close();

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
