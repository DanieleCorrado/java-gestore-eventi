package org.lesson.java;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.StringJoiner;

public class Evento {

    // ATTRIBUTI

    private String titolo;
    private LocalDate data;
    private int postiTotali;
    private int postiPrenotati;

    // COSTRUTTORI

    public Evento(String titolo, LocalDate data, int postiTotali) {

        verificaTitolo(titolo);
        verificaData(data);
        verificaPostiTotali(postiTotali);

        this.titolo = titolo;
        this.data = data;
        this.postiTotali = postiTotali;
        this.postiPrenotati = 0;
    }

    //METODI

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public int getPostiTotali() {
        return postiTotali;
    }

    public int getPostiPrenotati() {
        return postiPrenotati;
    }

    public void prenota(int posti) {

        // Controlla che i posti da prenotare siano positivi

        if (posti <= 0) {
            throw new IllegalArgumentException("Il numero di posti da prenotare deve essere positivo");
        }

        // Controlla che ci siano abbastanza posti disponibili

        if (postiPrenotati + posti > postiTotali) {
            throw new IllegalArgumentException("Non ci sono abbastanza posti disponibili");
        }

        postiPrenotati += posti;
    }

    public void disdici(int posti) {

        // Controlla che i posti da disdire siano positivi

        if (posti <= 0) {
            throw new IllegalArgumentException("Il numero di posti da disdire deve essere positivo");
        }

        // Controlla che i posti da disdire siano stati prenotati

        if (postiPrenotati < posti) {
            throw new IllegalArgumentException("Il numero di posti da disdire è superiore ai posti prenotati");
        }

        postiPrenotati -= posti;
    }

    public int postDisponibili() {

        return this.postiTotali - this.postiPrenotati;
    }

    public String getDataFormattata() {
        return this.getData().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    @Override
    public String toString() {
        return new StringJoiner(" - ")
                .add(getDataFormattata())
                .add(this.getTitolo())
                .toString();
    }

    // VALIDAZIONI

    public static void verificaTitolo(String titolo) throws IllegalArgumentException{
        if (titolo.isEmpty()) {
            throw new IllegalArgumentException("Il titolo non può essere vuoto");
        } else if (titolo.length() < 3) {
            throw new IllegalArgumentException("Il titolo deve contenere almeno tre caratteri");
        }
    }

    public static void verificaData(LocalDate data) throws IllegalArgumentException{
        if (data.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("La data dell'evento deve essere futura");
        }
    }

    public static void verificaPostiTotali(int postiTotali) throws IllegalArgumentException{
        if (postiTotali <= 0) {
            throw new IllegalArgumentException("Il numero di posti totali deve essere positivo");
        }
    }
}
