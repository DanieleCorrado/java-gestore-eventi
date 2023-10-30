package org.lesson.java;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Evento {

    // ATTRIBUTI

    private String titolo;
    private LocalDate data;
    private int postiTotali;
    private int postiPrenotati;

    // COSTRUTTORI

    public Evento(String titolo, LocalDate data, int postiTotali) {

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

        // Controlla che ci siano abbastanza posti disponibili

        if (postiPrenotati + posti > postiTotali) {
            throw new IllegalArgumentException("Non ci sono abbastanza posti disponibili");
        }

        postiPrenotati += posti;
    }

    @Override
    public String toString() {
        return data.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + " - " + titolo;
    }

    // VALIDAZIONI

    public static void verificaData(LocalDate data) {
        if (data.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("La data dell'evento deve essere futura");
        }
    }

    public static void verificaPostiTotali(int postiTotali) {
        if (postiTotali <= 0) {
            throw new IllegalArgumentException("Il numero di posti totali deve essere positivo");
        }
    }
}
