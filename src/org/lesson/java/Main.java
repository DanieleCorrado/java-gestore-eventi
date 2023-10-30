package org.lesson.java;

import java.time.LocalDate;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {


        Evento evento = aggiungiEevento();

        prenota(evento);

        mostraInfo(evento);

        disdici(evento);

        mostraInfo(evento);

    }


    // permette l'aggiunta di un evento

    public static Evento aggiungiEevento() {

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

        return evento;
    }

    // Permette la prenotazione a un evento

    public static void prenota(Evento evento) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Vuoi effettuare una prenotazione? (S/N)");
        String prenota = scanner.nextLine();


        if (prenota.equalsIgnoreCase("S")) {
            System.out.println("Quanti posti vuoi prenotare?");
            int postiPrenotati = Integer.parseInt(scanner.nextLine());

            // Tenta di effettuare la prenotazione

            try {
                evento.prenota(postiPrenotati);
                System.out.println("Prenotazione effettuata con successo");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

    }

    // Permette la prenotazione a un evento

    public static void disdici(Evento evento) {

        Scanner scanner = new Scanner(System.in);

        // Controlla che ci siano posti prenotati

        if (evento.getPostiPrenotati() == 0) {
            System.out.println("Non ci sono posti prenotati da disdire");
            return;
        }

        System.out.println("Vuoi disdire una prenotazione? (S/N)");
        String disdici = scanner.nextLine();


        if (disdici.equalsIgnoreCase("S")) {
            System.out.println("Quanti posti vuoi disdire?");
            int postiDisdetti = Integer.parseInt(scanner.nextLine());

            // Tenta di disdire la prenotazione

            try {
                evento.disdici(postiDisdetti);
                System.out.println("Disdetta effettuata con successo");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        scanner.close();
    }

    // Mostra il numero di prenotazioni e posti disponibili di un evento
    private static void mostraInfo(Evento evento) {

        System.out.println("Il numero di posti prenotati è: " + evento.getPostiPrenotati());
        System.out.println("Il numero di posti disponibili è: " + evento.postDisponibili());
    }
}
