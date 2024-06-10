package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import it.uniroma3.diadia.ambienti.*;
import it.uniroma3.diadia.attrezzi.*;
import it.uniroma3.diadia.personaggi.*;

class CaneTest {
	
	private Labirinto labirinto;
	private Partita partita;
	private int Cfu;
	private Attrezzo attrezzo;
	
	@BeforeEach
	public void setUp() throws Exception{
		this.attrezzo = new Attrezzo("guanti", 2);
		this.labirinto = Labirinto.newBuilder("labirintoTestCane.txt").getLabirinto();
		this.partita = new Partita(labirinto);
	}
	
	@Test
	public void testAgisci() {
		this.Cfu = this.partita.getGiocatore().getCfu()-1;
		AbstractPersonaggio p = this.partita.getStanzaCorrente().getPersonaggio();
		p.agisci(this.partita);
		assertEquals(Cfu, this.partita.getGiocatore().getCfu());
	}
	
	@Test
	void testRiceviRegaloGiusto() {
		AbstractPersonaggio p = this.partita.getStanzaCorrente().getPersonaggio();
		p.riceviRegalo(new Attrezzo("osso", 2), this.partita);
		assertNotEquals(this.attrezzo.getNome(), this.partita.getStanzaCorrente().getAttrezzo(this.attrezzo.getNome()));
	}
	
	@Test
	void testRiceviRegaloSbagliato() {
		this.Cfu = this.partita.getGiocatore().getCfu()-1;
		AbstractPersonaggio p = this.partita.getStanzaCorrente().getPersonaggio();
		p.riceviRegalo(new Attrezzo("lanterna", 2), this.partita);
		assertNull(this.partita.getStanzaCorrente().getAttrezzo(this.attrezzo.getNome()));
		assertEquals(Cfu, this.partita.getGiocatore().getCfu());
	}

}
