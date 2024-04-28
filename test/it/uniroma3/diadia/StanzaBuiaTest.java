package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.BeforeEach;

import it.uniroma3.diadia.ambienti.*;

import it.uniroma3.diadia.attrezzi.Attrezzo;

class StanzaBuiaTest {
	private StanzaBuia stanzaConLanterna;
	private StanzaBuia stanzaSenzaLanterna;
	private Stanza stanzaNormaleConLanterna;
	private Attrezzo lanterna;
	static final private String descrizione = "Qui c'è un buio pesto";
	
	@BeforeEach
	void setUp() {
		stanzaConLanterna = new StanzaBuia("stanza con lanterna");
		stanzaSenzaLanterna = new StanzaBuia("stanza senza lanterna");
		stanzaNormaleConLanterna = new Stanza("stanza con lanterna");
		lanterna = new Attrezzo("lanterna", 2);
		stanzaConLanterna.addAttrezzo(lanterna);
		stanzaNormaleConLanterna.addAttrezzo(lanterna);	
	}
	
	/**
	 * test stanza senza lanterna
	 */
	@Test
	public void testStanzaSogliaSNome() {
		assertEquals(descrizione, stanzaSenzaLanterna.toString());
	}
	
	/**
	 * test stanza con lanterna
	 */
	@Test
	public void testStanzaSogliaNSNome() {
		assertEquals(stanzaNormaleConLanterna.toString(), stanzaConLanterna.toString());
	}
}
