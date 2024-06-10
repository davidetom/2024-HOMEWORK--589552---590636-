package it.uniroma3.diadia;
//CHANGE
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.*;

import it.uniroma3.diadia.attrezzi.Attrezzo;

class StanzaBloccataTest {
	
	private StanzaBloccata stanzaConChiave;
	private StanzaBloccata stanzaSenzaChiave;
	private Stanza stanzaCollegata;
	private Attrezzo chiave;
	private Direzione direzione;
	
	@BeforeEach
	void setUp() {	
		direzione = Direzione.nord;
		stanzaConChiave = new StanzaBloccata("stanza Con Chiave", direzione, "chiave");
		stanzaSenzaChiave = new StanzaBloccata("stanza Senza Chiave", direzione, "chiave");
		chiave = new Attrezzo("chiave", 1);
		stanzaCollegata = new Stanza("stanza collegata");
		stanzaConChiave.impostaStanzaAdiacente(direzione, stanzaCollegata);
		stanzaSenzaChiave.impostaStanzaAdiacente(direzione, stanzaCollegata);
		stanzaConChiave.addAttrezzo(chiave);
	}
	
	/**
	 * test per una stanza con la chiave
	 */
	@Test
	public void testStanzaBloccata_StanzaConChiave() {
		assertEquals(stanzaCollegata, stanzaConChiave.getStanzaAdiacente(direzione));
	}
	
	/**
	 * test per stanza senza chiave
	 */
	@Test
	public void testStanzaBloccata_StanzaSenzaChiave() {
		assertEquals(stanzaSenzaChiave, stanzaSenzaChiave.getStanzaAdiacente(direzione));
	}
	
	/**
	 * test getDescrizione stanza bloccata
	 */
	@Test
	public void testGetDescrizione_StanzaConChiave(){
		assertEquals(stanzaConChiave.toString(), stanzaConChiave.getDescrizione());
	}
	
	@Test
	public void testGetDescrizione_StanzaSenzaChiave() {
		String info = "Stanza bloccata, vietato l'accesso senza l'attrezzo richiesto: chiave\n";
		assertEquals(info, stanzaSenzaChiave.getDescrizione());
	}

}
