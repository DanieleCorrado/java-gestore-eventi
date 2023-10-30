package org.lesson.java;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        ProgrammEventi programma = listaEventi();

        String nomeEvento;

        Evento evento;

        String continua = "S";

        while (continua.equalsIgnoreCase("S")) {

            System.out.println("Cosa vuoi fare? ");
            System.out.println("1 - Aggiungi prenotazione");
            System.out.println("2 - Rimuovi prenotazione");
            System.out.println("3 - visualizza tutti gli eventi in una certa data");
            System.out.println("4 - visualizza tutti gli eventi presenti in ordine cronologico");
            System.out.println("5 - Visualizza il numero totale di eventi in lista");
            System.out.println("6 - Rimuovi tutti gli eventi in lista");
            System.out.println("7 - Esci");

            String risposta = scanner.nextLine();

            switch (risposta) {

                case "1":

                    System.out.print("Per quale evento vuoi effettuare la prenotazione? ");
                    nomeEvento = scanner.nextLine();
                    String finalNomeEvento = nomeEvento;
                    evento = programma.eventi.stream()
                            .filter(event -> event.getTitolo().equals(finalNomeEvento))
                            .findFirst()
                            .orElse(null);
                    if (evento != null) {

                        prenota(evento);
                        mostraInfo(evento);
                    } else {
                        throw new RuntimeException("L'evento selezionato non è presente!");
                    }

                    break;

                case "2":

                    System.out.print("Per quale evento vuoi disdire la prenotazione? ");
                    nomeEvento = scanner.nextLine();
                    String finalNomeEvento1 = nomeEvento;
                    evento = programma.eventi.stream()
                            .filter(event -> event.getTitolo().equals(finalNomeEvento1))
                            .findFirst()
                            .orElse(null);
                    if (evento != null) {
                        disdici(evento);
                        mostraInfo(evento);
                    } else {
                        throw new RuntimeException("L'evento selezionato non è presente!");
                    }

                    break;

                case "3":

                    System.out.print("inserisci la data di cui vuoi vedere gli eventi: (yyyy-MM-dd) ");
                    String data = scanner.nextLine();

                    System.out.println(programma.getEventiPerData(LocalDate.parse(data)));

                    break;

                case "4":

                    System.out.println(programma);

                    break;

                case "5":

                    System.out.println("Nel programma sono presenti: " + programma.getNumEventi() + " eventi");

                    break;
                case "6":

                    System.out.println("Eliminazione eventi");
                    programma.svuotaEventi();

                    break;
                case "7":

                    continua = "N";

                    break;
                default:

                    System.out.println("Input non valido");
                    break;

            }

            if(!risposta.equalsIgnoreCase("7")) {

                System.out.print("Vuoi fare ancora altro? (S/N) ");
                continua = scanner.nextLine();

            }

        }



        scanner.close();

    }

    // permette l'aggiunta di un evento

    public static ProgrammEventi listaEventi() {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Inserisci il nome della lista di eventi ");
        String nomeLista = scanner.nextLine();

        ProgrammEventi programma = new ProgrammEventi(nomeLista);

        String risposta = "S";

        while (risposta.equalsIgnoreCase("S")) {
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

                   programma.aggiungiEvento(new Concerto(titolo, data, ora, postiTotali, prezzo));
               } else {

                   programma.aggiungiEvento(new Evento(titolo, data, postiTotali));
               }
           }

           System.out.print("Vuoi aggiungere un altro evento? (S/N) ");
           risposta = scanner.nextLine();
       }

        return programma;

    }

    // Permette la prenotazione a un evento

    public static void prenota(Evento evento) {

        Scanner scanner = new Scanner(System.in);

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

    // Permette la prenotazione a un evento

    public static void disdici(Evento evento) {

        Scanner scanner = new Scanner(System.in);

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


    // Mostra il numero di prenotazioni e posti disponibili di un evento
    private static void mostraInfo(Evento evento) {

        if(evento != null) {

            if (evento instanceof Concerto) {
                System.out.println(evento);
            } else {
                System.out.println(evento);
            }

            System.out.println("Il numero di posti prenotati è: " + evento.getPostiPrenotati());
            System.out.println("Il numero di posti disponibili è: " + evento.postDisponibili());

        }
    }
}
