package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.BeforeEach;

import it.uniroma3.diadia.ambienti.*;

/**
 * Funzione di test per la classe Labirinto
 * 
 * @see Stanza
 *
 */
public class LabirintoTest {
	private Labirinto labirinto;
	private Stanza atrio;
	private Stanza biblioteca;
	
	@BeforeEach
	public void setUp()throws Exception {
		this.labirinto = Labirinto.newBuilder("labirinto.txt").getLabirinto();
		this.atrio = new Stanza("Atrio");
		this.biblioteca = new Stanza("Biblioteca");
	}
	
	/*Test metodo getStanzaCorrente*/
	@Test
	public void testGetStanzaIniziale_Labirinto() {
		assertEquals(atrio, this.labirinto.getStanzaIniziale());
	}
	
	/*Test metodo getStanzaVincente*/
	@Test
	public void testGetStanzaVincente_Labirinto() {
		assertEquals(biblioteca, this.labirinto.getStanzaVincente());
	}

}
