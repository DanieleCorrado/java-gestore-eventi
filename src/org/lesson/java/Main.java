package org.lesson.java;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        Evento evento = aggiungiEevento();

        if (evento != null) {

            prenota(evento);

            mostraInfo(evento);

            // Controlla che ci siano posti prenotati

            if (evento.getPostiPrenotati() != 0) {

                disdici(evento);

                mostraInfo(evento);
            }
        } else {

            System.out.println("Nessun evento inserito");
        }

        scanner.close();

    }

    // permette l'aggiunta di un evento

    public static Evento aggiungiEevento() {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Che tipo di evento vuoi aggiungere? ");
        System.out.println("1 - concerto");
        System.out.println("2 - altro");
        String tipoEvento = scanner.nextLine();

        if (tipoEvento.equals("1") || tipoEvento.equals("2")) {

            System.out.print("Inserisci il titolo dell'evento: ");
            String titolo = scanner.nextLine();

            System.out.print("Inserisci la data dell'evento (yyyy-MM-dd): ");
            String dataString = scanner.nextLine();
            LocalDate data = LocalDate.parse(dataString);

            System.out.print("Inserisci il numero di posti totali: ");
            int postiTotali = Integer.parseInt(scanner.nextLine());

            if(tipoEvento.equals("1")) {

                System.out.print("Inserisci l'ora del concerto (hh:mm): ");
                String oraString = scanner.nextLine();
                LocalTime ora = LocalTime.parse(oraString);

                System.out.print("Inserisci il prezzo del biglietto ");
                String prezzoString = scanner.nextLine();
                BigDecimal prezzo = new BigDecimal(prezzoString);

                return new Concerto(titolo, data, ora, postiTotali, prezzo);
            } else {

                return new Evento(titolo, data, postiTotali);
            }
        } else {

            return null;
        }

    }

    // Permette la prenotazione a un evento

    public static void prenota(Evento evento) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Vuoi effettuare una prenotazione? (S/N) ");
        String prenota = scanner.nextLine();


        if (prenota.equalsIgnoreCase("S")) {
            System.out.print("Quanti posti vuoi prenotare? ");
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

        System.out.print("Vuoi disdire una prenotazione? (S/N) ");
        String disdici = scanner.nextLine();


        if (disdici.equalsIgnoreCase("S")) {
            System.out.print("Quanti posti vuoi disdire? ");
            int postiDisdetti = Integer.parseInt(scanner.nextLine());

            // Tenta di disdire la prenotazione

            try {
                evento.disdici(postiDisdetti);
                System.out.println("Disdetta effettuata con successo");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    // Mostra il numero di prenotazioni e posti disponibili di un evento
    private static void mostraInfo(Evento evento) {

        if (evento instanceof Concerto) {
            System.out.println(evento);
        } else if (evento != null) {
            System.out.println(evento);
        }

        System.out.println("Il numero di posti prenotati è: " + evento.getPostiPrenotati());
        System.out.println("Il numero di posti disponibili è: " + evento.postDisponibili());

    }
}
