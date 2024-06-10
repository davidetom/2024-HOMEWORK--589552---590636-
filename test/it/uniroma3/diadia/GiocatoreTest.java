package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach; 

import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.giocatore.*;


public class GiocatoreTest {
	private Giocatore zero;
	private Giocatore uno;
	private Giocatore due;
	
	@BeforeEach
	public void setUp() {
		zero = new Giocatore();
		uno = new Giocatore();
		due = new Giocatore();

		uno.setCfu(-1);
		due.setCfu(10000);
	}
	
	/**
	 * Test metodo getBorsa
	 */
	@Test
	public void testGetBorsa_NotNullDaGiocatore() {
		assertNotNull(zero.getBorsa());
	}
	
	/**
	 * Test metodo setCfu
	 */
	@Test
	public void testSetCfu() {
		zero.setCfu(0);
		assertEquals(0, zero.getCfu(), "Errore, dovrebbe essere 0");
	}
	
	/**
	 * Test metodo getCfu
	 */
	@Test
	public void testGetCfu_InizialiGiocatore() {
		assertEquals(30, zero.getCfu(), "Eroore, dovrebbe essere 20");
	}
	@Test
	public void testGetCfu_GiocatoreZeroCfu() {
		zero.setCfu(0);
		assertEquals(0, zero.getCfu(), "Errore, deve essere 0");
	}
	
	@Test
	public void testGetCfu_GiocatoreCfuNegativi() {
		assertEquals(-1, uno.getCfu(), "Errore, deve essere uguale a -1");
	}
	
	@Test
	public void testGetCfu_GiocatoreGenerico() {
		assertEquals(100*100, due.getCfu(), "Errore, dovrebbe essere 10000");
	}
	
	
}
