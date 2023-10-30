package org.lesson.java;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ProgrammEventi {

    private String titolo;
    List<Evento> eventi;

    public ProgrammEventi(String titolo) {
        verificaTitolo(titolo);
        this.titolo = titolo;
        this.eventi = new ArrayList<>();
    }

    public void aggiungiEvento(Evento evento) {
        eventi.add(evento);
    }

    public List<Evento> getEventiPerData(LocalDate data) {
        List<Evento> eventiPerData = new ArrayList<>();
        for (Evento evento : eventi) {
            if (evento.getData().equals(data)) {
                eventiPerData.add(evento);
            }
        }
        return eventiPerData;
    }

    public int getNumEventi() {
        return eventi.size();
    }

    public void svuotaEventi() {
        eventi.clear();
    }

    @Override
    public String toString() {
        return titolo + "\n" +
                eventi.stream()
                        .sorted(Comparator.comparing(Evento::getData))
                        .map(evento -> evento.getDataFormattata() + " - " + evento.getTitolo())
                        .collect(Collectors.joining("\n"));
    }

    // VALIDAZIONI

    public static void verificaTitolo(String titolo) throws IllegalArgumentException{
        if (titolo.isEmpty()) {
            throw new IllegalArgumentException("Il titolo non può essere vuoto");
        } else if (titolo.length() < 3) {
            throw new IllegalArgumentException("Il titolo deve contenere almeno tre caratteri");
        }
    }
}
