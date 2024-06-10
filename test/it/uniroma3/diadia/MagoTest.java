package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.BeforeEach;
import it.uniroma3.diadia.ambienti.*;
import it.uniroma3.diadia.attrezzi.*;
import it.uniroma3.diadia.personaggi.*;

class MagoTest {
	private Labirinto labirinto;
	private Partita partita;
	private Attrezzo attrezzo;
	
	@BeforeEach
	void setUp() throws Exception{
		this.labirinto = Labirinto.newBuilder("labirintoTestMago.txt").getLabirinto();
		this.partita = new Partita(labirinto);
		this.attrezzo = new Attrezzo("anello", 3);
	}
	
	@Test
	void testAgisci() {
		AbstractPersonaggio p = this.partita.getStanzaCorrente().getPersonaggio();
		p.saluta();
		p.agisci(partita);
		assertEquals(this.attrezzo, this.partita.getStanzaCorrente().getAttrezzo(this.attrezzo.getNome()));
		assertEquals("Mi spiace, ma non ho piu' nulla...", p.agisci(this.partita));
	}
	
	@Test
	void testRiceviRegalo() {
		Attrezzo a = new Attrezzo("Palla", 4);
		AbstractPersonaggio p = this.partita.getStanzaCorrente().getPersonaggio();
		p.riceviRegalo(a, this.partita);
		assertNotNull(this.partita.getStanzaCorrente().getAttrezzo(a.getNome()));
		assertEquals(a.getPeso()/2, this.partita.getStanzaCorrente().getAttrezzo(a.getNome()).getPeso());
	}
}
