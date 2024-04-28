package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.BeforeEach;

import it.uniroma3.diadia.giocatore.*;

import it.uniroma3.diadia.attrezzi.*;

public class BorsaTest {
	/*FIXTURE*/
	private Borsa borsaVuota;
	private Borsa borsaAttrezzi;
	private Attrezzo spada;
	private Attrezzo scudo;
	private Attrezzo candela;
	private String nomeAttrezzo;
	
	@BeforeEach
	public void setUp() {
		borsaVuota = new Borsa();
		borsaAttrezzi = new Borsa();
		
		spada = new Attrezzo("spada", 5);
		scudo = new Attrezzo("scudo", 4);
		candela = new Attrezzo("candela", 1);
		
		borsaAttrezzi.addAttrezzo(scudo);
		borsaAttrezzi.addAttrezzo(candela);
		borsaAttrezzi.addAttrezzo(spada);
	}
	
	
	/**
	 * Test metodo getAttrezzo
	 */
	@Test
	public void testGetAttrezzo_BorsaVuota() {
		assertNull(borsaVuota.getAttrezzo(nomeAttrezzo));
	}
	
	@Test
	public void testGetAttrezzo_PrimoAttrezzoDellaBorsa() {
		nomeAttrezzo = "scudo";
		assertEquals(scudo, borsaAttrezzi.getAttrezzo(nomeAttrezzo));
	}
	
	@Test
	public void testGetAttrezzo_UltimoAttrezzoBorsa() {
		nomeAttrezzo = "spada";
		assertEquals(spada, borsaAttrezzi.getAttrezzo(nomeAttrezzo));
	}
	
	@Test
	public void testGetAttrezzo_NonPresenteNellaBorsa() {
		nomeAttrezzo = "lanterna";
		assertNull(borsaAttrezzi.getAttrezzo(nomeAttrezzo));
	}
	
	/**
	 * Test metodo hasAttrezzo
	 */
	@Test
	public void testHasAttrezzo_BorsaVuota() {
		assertNull(borsaVuota.getAttrezzo(nomeAttrezzo));
	}
	
	@Test
	public void testHasAttrezzo_PrimoAttrezzoDellaBorsa() {
		nomeAttrezzo = "scudo";
		assertTrue(borsaAttrezzi.hasAttrezzo(nomeAttrezzo));
	}
	
	@Test
	public void testHasAttrezzo_UltimoAttrezzoDellaBorsa() {
		nomeAttrezzo = "spada";
		assertTrue(borsaAttrezzi.hasAttrezzo(nomeAttrezzo));
	}
	
	@Test
	public void testHasAttrezzo_InesistenteNellaBorsa() {
		nomeAttrezzo = "osso";
		assertEquals(false, borsaAttrezzi.hasAttrezzo(nomeAttrezzo));
	}
	
	/**
	 * Test metodo removeAttrezzo
	 */
	
	@Test
	public void testRemoveAttrezzo_BorsaVuota() {
		assertNull(borsaVuota.removeAttrezzo(nomeAttrezzo));
	}
	
	@Test
	public void testRemoveAttrezzo_PrimoAttrezzoBorsa() {
		nomeAttrezzo = "scudo";
		assertEquals(scudo, borsaAttrezzi.removeAttrezzo(nomeAttrezzo));
	}
	
	@Test
	public void testRemoveAttrezzo_UltimoAttrezzoBorsa() {
		nomeAttrezzo = "spada";
		assertEquals(spada, borsaAttrezzi.removeAttrezzo(nomeAttrezzo));
	}
	
	@Test
	public void testRemoveAttrezzo_InesistenteNellaBorsa() {
		nomeAttrezzo = "lanterna";
		assertNull(borsaAttrezzi.removeAttrezzo(nomeAttrezzo));
	}
	
	/**
	 * Test metodo addAttrezzo
	 */
	
	@Test
	public void testAddAttrezzo_BorsaVuota() {
		assertEquals(true, borsaVuota.addAttrezzo(candela));
	}
	
	@Test
	public void testAddAttrezzo_BorsaPiena() {
		assertEquals(false, borsaAttrezzi.addAttrezzo(spada), "Errore, non avrebbe dovuto aggiungerlo, borsa piena");
	}

}
