package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.BeforeEach;

import it.uniroma3.diadia.comandi.*;

class FabbricaDiComandiFisarmonicaTest {
	/**
	 * FIXTURE
	 */
	private FabbricaDiComandiFisarmonica fabbrica;
	private Comando verifica;
	
	/**
	 * setUp
	 */
	@BeforeEach
	public void setUp() {
		fabbrica = new FabbricaDiComandiFisarmonica();
	}
	
	@Test
	public void testFabbricaComandi_ComandoNonValido() {
		verifica = new ComandoNonValido();
		assertEquals(verifica.getNome(), fabbrica.costruisciComando("none").getNome(), "Errore sarebbero dovuti essere uguali");
		
	}
	
	@Test
	public void testFabbricaComandi_ComandoSenzaParametro() {
		verifica = new ComandoFine();
		assertEquals(verifica.getNome(), fabbrica.costruisciComando("fine").getNome());
	}
	
	@Test
	public void testFabbricaComandi_ComandoConParametro() {
		verifica = new ComandoVai();
		verifica.setParametro("nord");
		Comando corrente = fabbrica.costruisciComando("vai nord");
		assertEquals(verifica.getNome(), corrente.getNome());
		assertEquals(verifica.getParametro(), corrente.getParametro());
	}
	
	@Test
	public void testFabbricaComandi_ComandoAiuto() {
		verifica = new ComandoAiuto();
		assertEquals(verifica.getNome(), fabbrica.costruisciComando("aiuto").getNome());
	}
	
	@Test
	public void testFabbricaComandi_ComandoGuarda() {
		verifica = new ComandoGuarda();
		assertEquals(verifica.getNome(), fabbrica.costruisciComando("guarda").getNome());
	}
	
	@Test
	public void testFabbricaComandi_ComandoPosa() {
		verifica = new ComandoPosa();
		assertEquals(verifica.getNome(), fabbrica.costruisciComando("posa").getNome());
	}
	
	@Test
	public void testFabbricaComandi_ComandoPrendi() {
		verifica = new ComandoPrendi();
		assertEquals(verifica.getNome(), fabbrica.costruisciComando("prendi").getNome());
	}
}
