package org.lesson.java;

import java.time.LocalDate;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Inserisci il titolo dell'evento:");
        String titolo = scanner.nextLine();

        System.out.println("Inserisci la data dell'evento (yyyy-MM-dd):");
        String dataString = scanner.nextLine();
        LocalDate data = LocalDate.parse(dataString);

        System.out.println("Inserisci il numero di posti totali:");
        int postiTotali = Integer.parseInt(scanner.nextLine());

        Evento evento = new Evento(titolo, data, postiTotali);

        System.out.println("Evento creato:");
        System.out.println(evento);


        System.out.println("Vuoi effettuare una prenotazione? (S/N)");
        String risposta = scanner.nextLine();


        if (risposta.equalsIgnoreCase("S")) {
            System.out.println("Quanti posti vuoi prenotare?");
            int postiPrenotati = scanner.nextInt();

            // Tenta di effettuare la prenotazione
            try {
                evento.prenota(postiPrenotati);
                System.out.println("Prenotazione effettuata con successo");
            } catch (IllegalArgumentException e) {
                // Gestisce l'eccezione
                System.out.println(e.getMessage());
            }
        }

        scanner.close();
    }
}
