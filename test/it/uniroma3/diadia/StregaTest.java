package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import it.uniroma3.diadia.ambienti.*;
import it.uniroma3.diadia.personaggi.*;

class StregaTest {
	private Labirinto labirinto;
	private Partita partita;
	
	@BeforeEach
	void setUp() throws Exception{
		this.labirinto = Labirinto.newBuilder("labirintoTestStrega.txt").getLabirinto();
		this.partita = new Partita(labirinto);
	}
	
	@Test
	void testAgisciSalutata() {
		AbstractPersonaggio p = this.partita.getStanzaCorrente().getPersonaggio();
		p.agisci(this.partita);
		assertEquals("N10", this.partita.getStanzaCorrente().getNome());
	}
	
	@Test
	void testAgisciNonSalutata() {
		AbstractPersonaggio p = this.partita.getStanzaCorrente().getPersonaggio();
		p.saluta();
		p.agisci(this.partita);
		assertEquals("Corridoio", this.partita.getStanzaCorrente().getNome());
	}
}
