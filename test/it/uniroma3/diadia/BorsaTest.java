package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.BeforeEach;

import it.uniroma3.diadia.giocatore.*;

import it.uniroma3.diadia.attrezzi.*;

import java.util.*
;
public class BorsaTest {
	/*FIXTURE*/
	private Borsa borsaVuota;
	private Borsa borsaAttrezzi;
	private Borsa borsaDueAttrezziStessoPeso;
	private Attrezzo spada;
	private Attrezzo scudo;
	private Attrezzo candela;
	private Attrezzo bastone;
	private String nomeAttrezzo;
	
	@BeforeEach
	public void setUp() {
		borsaVuota = new Borsa();
		borsaAttrezzi = new Borsa();
		borsaDueAttrezziStessoPeso = new Borsa();
		
		spada = new Attrezzo("spada", 4);
		scudo = new Attrezzo("scudo", 3);
		candela = new Attrezzo("candela", 1);
		bastone = new Attrezzo("bastone", 1);
		
		
		borsaAttrezzi.addAttrezzo(scudo);
		borsaAttrezzi.addAttrezzo(candela);
		borsaAttrezzi.addAttrezzo(spada);
		borsaDueAttrezziStessoPeso.addAttrezzo(spada);
		borsaDueAttrezziStessoPeso.addAttrezzo(scudo);
		borsaDueAttrezziStessoPeso.addAttrezzo(candela);
		borsaDueAttrezziStessoPeso.addAttrezzo(bastone);	
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
	
	
	/**
	 * Test metodo getContenutoOrdinatoPerPeso
	 */
	@Test
	public void testGetContenutoOrdinatoPerPeso_BorsaVuota() {
		List<Attrezzo> attuale = this.borsaVuota.getContenutoOrdinatoPerPeso();
		assertEquals(" ]", borsaVuota.toString(attuale));
	}
	
	@Test
	public void testGetContenutoOrdinatoPerPeso() {
		List<Attrezzo> attuale = this.borsaAttrezzi.getContenutoOrdinatoPerPeso();
		assertEquals("[ candela:1, scudo:3, spada:4 ]", borsaAttrezzi.toString(attuale));
		  /*Iterator<Attrezzo> i = attuale.iterator();
		  assertEquals(candela, i.next());
		  assertEquals(scudo, i.next());
		  assertEquals(spada, i.next());
		  assertEquals(piombo, i.next());*/
	}
	
	@Test
	public void testGetContenutoOrdinatoPerPeso_Borsa2PesiUguali() {
		List<Attrezzo> attuale = this.borsaDueAttrezziStessoPeso.getContenutoOrdinatoPerPeso();
		assertEquals("[ bastone:1, candela:1, scudo:3, spada:4 ]", borsaDueAttrezziStessoPeso.toString(attuale));
	}
	
	/**
	 * Test metodo getContenutoOrdinatoPerNome
	 */
	@Test
	public void testGetContenutoOrdinatoPerNome_BorsaVuota() {
		SortedSet<Attrezzo> attuale = this.borsaVuota.getContenutoOrdinatoPerNome();
		assertEquals(" }", borsaVuota.toString(attuale));
	}
	
	@Test
	public void testGetContenutoOrdinatoPerNome() {
		SortedSet<Attrezzo> insieme = this.borsaAttrezzi.getContenutoOrdinatoPerNome();
		assertEquals("{ candela:1, scudo:3, spada:4 }", borsaAttrezzi.toString(insieme));
	}
	
	/**
	 * Test metodo getContenutoRaggruppatoPerPeso
	 */
	@Test
	public void testGetContenutoRaggruppatoPerPeso() {
		Map<Integer, Set<Attrezzo>> mappa = borsaAttrezzi.getContenutoRaggruppatoPerPeso();
		assertEquals("(1, { candela } ) ; (3, { scudo } ) ; (4, { spada } )", borsaAttrezzi.toString(mappa));
	}
	
	/*@Test
	public void testGetContenutoRaggruppatoPerPeso_Borsa2PesiUguali() {
		Map<Integer, Set<Attrezzo>> mappa = borsaDueAttrezziStessoPeso.getContenutoRaggruppatoPerPeso();
		assertEquals("(1, { candela, bastone } ) ; (3, { scudo } ) ; (4, { spada } )", borsaDueAttrezziStessoPeso.toString(mappa));
	}*/
	
	/**
	 * Test metodo getSortedSetOrdinatoPerPeso
	 */
	@Test
	public void testGetSortedSetOrdinatoPerPeso_BorsaVuota() {
		SortedSet<Attrezzo> attuale = this.borsaVuota.getSortedSetOrdinataPerPeso();
		assertEquals(" }", borsaVuota.toString(attuale));
	}
	
	@Test
	public void testGetSortedSetOrdinatoPerPeso() {
		SortedSet<Attrezzo> insieme = borsaAttrezzi.getSortedSetOrdinataPerPeso();
		assertEquals("{ candela:1, scudo:3, spada:4 }", borsaAttrezzi.toString(insieme));
	}
	
	@Test
	public void testGetSortedSetOrdinatoPerPeso_Borsa2PesiUguali() {
		SortedSet<Attrezzo> insieme = borsaDueAttrezziStessoPeso.getSortedSetOrdinataPerPeso();
		assertEquals("{ bastone:1, candela:1, scudo:3, spada:4 }", borsaDueAttrezziStessoPeso.toString(insieme));
	}

	/*@Test
	public void testContenutoRaggruppatoPerPeso() {
		//1-borsa vuota
		//2-1 attrezzo
		//3-2 attrezzi di peso diverso
		//4- 2attrezzi stesso peso
		//5- 3 attrezzi
	}*/


}
