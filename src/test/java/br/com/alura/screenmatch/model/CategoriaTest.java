package br.com.alura.screenmatch.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CategoriaTest {

    @Test
    void fromStringIgnoraMaiusculasEMinusculas() {
        assertEquals(Categoria.COMEDIA, Categoria.fromString("comedy"));
        assertEquals(Categoria.FICCAO_CIENTIFICA, Categoria.fromString("SCIENCE FICTION"));
        assertEquals(Categoria.FILME_NOIR, Categoria.fromString("film-noir"));
    }

    @Test
    void fromStringUsaNomeOmdbENaoONomeDoEnum() {
        assertThrows(IllegalArgumentException.class, () -> Categoria.fromString("COMEDIA"));
    }

    @Test
    void fromStringInvalidaLancaExcecaoComMensagem() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> Categoria.fromString("Unknown"));
        assertTrue(ex.getMessage().contains("Unknown"));
    }

    @Test
    void fromStringNaoAceitaEspacosExtras() {
        assertThrows(IllegalArgumentException.class, () -> Categoria.fromString(" Drama"));
    }
}
