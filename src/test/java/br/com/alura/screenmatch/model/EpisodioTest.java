package br.com.alura.screenmatch.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class EpisodioTest {

    @Test
    void naoDisponivelViraZeroENulo() {
        Episodio e = new Episodio(1, new DadosEpisodio("Piloto", 1, "N/A", "N/A"));
        assertEquals(0.0, e.getAvaliacao());
        assertNull(e.getDataLancamento());
        assertTrue(e.toString().contains("dataLancamento=N/A"));
    }

    @Test
    void valoresValidosSaoConvertidos() {
        Episodio e = new Episodio(2, new DadosEpisodio("Ep", 3, "8.5", "2020-01-15"));
        assertEquals(8.5, e.getAvaliacao());
        assertEquals(LocalDate.of(2020, 1, 15), e.getDataLancamento());
        assertTrue(e.toString().contains("dataLancamento=15-01-2020"));
    }
}
