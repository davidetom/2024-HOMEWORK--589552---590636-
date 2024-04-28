package it.uniroma3.diadia;

import it.uniroma3.diadia.attrezzi.*;
import it.uniroma3.diadia.ambienti.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class StanzaTest {
	private Stanza stanzavuota;
	private Stanza stanza2attrezzi;
	private Stanza stanzaPiena;
	private Attrezzo lanterna;
	private Attrezzo osso;
	private Attrezzo spada;
	private Attrezzo scudo;
	private Attrezzo bastone;
	private Attrezzo elmo;
	private Attrezzo libro;
	private Attrezzo pane;
	private Attrezzo martello;
	private Attrezzo anello;
	String nomeAttrezzo;
	
	@BeforeEach
	public void setUp() {
		stanzavuota = new Stanza("stanza vuota");
		stanza2attrezzi = new Stanza(" stanza due attrezzi");
		stanzaPiena = new Stanza ("stanza piena");
		
		lanterna = new Attrezzo("lanterna", 3);
		osso = new Attrezzo("osso", 1);
		spada = new Attrezzo("spada", 4);
		scudo = new Attrezzo("scudo", 4);
		bastone = new Attrezzo("bastone", 2);
		elmo = new Attrezzo("elmo", 3);
		libro = new Attrezzo("libro", 2);
		pane = new Attrezzo("pane", 2);
		martello = new Attrezzo("martello", 6);
		anello = new Attrezzo("anello", 1);
		
		stanza2attrezzi.addAttrezzo(lanterna);
		stanza2attrezzi.addAttrezzo(osso);
		
		stanzaPiena.addAttrezzo(spada);
		stanzaPiena.addAttrezzo(lanterna);
		stanzaPiena.addAttrezzo(osso);
		stanzaPiena.addAttrezzo(scudo);
		stanzaPiena.addAttrezzo(bastone);
		stanzaPiena.addAttrezzo(elmo);
		stanzaPiena.addAttrezzo(libro);
		stanzaPiena.addAttrezzo(pane);
		stanzaPiena.addAttrezzo(martello);
		stanzaPiena.addAttrezzo(anello);
	}
	
	/**
	 * Test metodo removeAttrezzo
	 */
	@Test 
	public void testRemoveAttrezzo_StanzaVuota() {
		nomeAttrezzo = "lanterna";
		assertFalse(stanzavuota.removeAttrezzo(nomeAttrezzo));
	}
	
	@Test
	public void testRemoveAttrezzo_PrimoAttrezzoStanza() {
		nomeAttrezzo = "lanterna";
		assertTrue(stanza2attrezzi.removeAttrezzo(nomeAttrezzo));
	}
	
	@Test
	public void testRemoveAttrezzo_UltimoAttrezzoStanza() {
		nomeAttrezzo = "osso";
		assertTrue(stanza2attrezzi.removeAttrezzo(nomeAttrezzo));
	}
	
	@Test
	public void testRemoveAttrezzo_InesistenteInUnaStanza() {
		nomeAttrezzo = "spada";
		assertFalse(stanza2attrezzi.removeAttrezzo(nomeAttrezzo));
	}
	
	/**
	 * Test metodo hasAttrezzo
	 */
	@Test
	public void testHasAttrezzo_StanzaVuota(){
		nomeAttrezzo = "osso";
		assertEquals(false, stanzavuota.hasAttrezzo(nomeAttrezzo));	
	}
	
	@Test
	public void testHasAttrezzo_NonPresenteNellaStanza() {
		nomeAttrezzo = "spada";
		assertFalse(stanza2attrezzi.hasAttrezzo(nomeAttrezzo));
	}
	
	@Test
	public void testHasAttrezzo_PrimoAttrezzoStanzaDueAttrezzi() {
		nomeAttrezzo = "lanterna";
		assertTrue(stanza2attrezzi.hasAttrezzo(nomeAttrezzo));
	}
	
	@Test
	public void testHasAttrezzo_UltimoAttrezzoStanzaDueAttrezzi() {
		nomeAttrezzo = "osso";
		assertTrue(stanza2attrezzi.hasAttrezzo(nomeAttrezzo));
	}
	
	/**
	 * Test metodo getAttrezzo
	 */
	@Test
	public void testGetAttrezzo_StanzaVuota() {
		nomeAttrezzo = "lanterna";
		assertNull(stanzavuota.getAttrezzo(nomeAttrezzo));
	}
	
	@Test
	public void testGetAttrezzo_PrimoAttrezzoStanzaConDueAttrezzi() {
		nomeAttrezzo = "lanterna";	
		assertEquals(lanterna, stanza2attrezzi.getAttrezzo(nomeAttrezzo));
	}
	
	@Test
	public void testGetAttrezzo_UltimoAttrezzoStanzaConDueAttrezzi() {
		nomeAttrezzo = "osso";
		assertEquals(osso, stanza2attrezzi.getAttrezzo(nomeAttrezzo));
	}
	
	@Test
	public void testGetAttrezzo_AttrezzoInesistenteNellaStanza() {
		nomeAttrezzo = "scudo";
		assertNull(stanza2attrezzi.getAttrezzo(nomeAttrezzo));
	}
	
	/**
	 * Test metodo addAttrezzo
	 */
	
	@Test
	public void testAddAttrezzo_UnaStanzaPiena() {
		assertEquals(false, stanzaPiena.addAttrezzo(anello), "Errore, stanza piena, non si può aggiungere");
	}
	
	@Test
	public void testAddAttrezzo_UnaStanzaVuota() {
		assertEquals(true, stanzavuota.addAttrezzo(anello));
	}
}
