package org.lesson.java;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.StringJoiner;

public class Concerto extends Evento{

    // ATTRIBUTI

    private final LocalTime ora;

    private final BigDecimal prezzo;

    // COSTRUTTORI

    public Concerto(String titolo, LocalDate data, LocalTime ora,int postiTotali, BigDecimal prezzo) {
        super(titolo, data, postiTotali);
        verificaOra(ora);
        verificaPrezzo(prezzo);
        this.ora = ora;
        this.prezzo = prezzo;
    }


    // METODI


    public String getOraFormattata() {
        return ora.format(DateTimeFormatter.ofPattern("HH:mm"));
    }

    public String getPrezzoFormattato() {
        DecimalFormat df = new DecimalFormat("#,###.00");
        return df.format( this.prezzo);
    }

    @Override
    public String toString() {
        return new StringJoiner(" - ")
                .add(this.getDataFormattata() + " " + getOraFormattata())
                .add(this.getTitolo())
                .add(getPrezzoFormattato() + "Є")
                .toString();
    }

    // VALIDAZIONI

    public static void verificaOra(LocalTime ora) throws IllegalArgumentException{
        if (ora.isBefore(LocalTime.now())) {
            throw new IllegalArgumentException("L'ora dell'evento deve essere futura");
        }
    }

    public static void verificaPrezzo(BigDecimal prezzo) throws IllegalArgumentException{
        if (prezzo.compareTo(new BigDecimal(0)) < 0 || prezzo.equals(new BigDecimal(0))) {
            throw new IllegalArgumentException("Il prezzo deve essere positivo");
        }
    }
}
