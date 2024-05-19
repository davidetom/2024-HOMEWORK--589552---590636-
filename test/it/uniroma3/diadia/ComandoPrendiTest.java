package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.comandi.*;

import it.uniroma3.diadia.attrezzi.*;

import it.uniroma3.diadia.ambienti.*;

import it.uniroma3.diadia.IOConsole.*;

class ComandoPrendiTest {
	/**
	 * FIXTURE	
	 */
	private Partita partita;
	private Labirinto labirinto;
	private IO io;
	private Attrezzo spada;
	private Comando comando;
	private String parametro;
	
	@BeforeEach
	public void setUp() {
		this.labirinto = new LabirintoBuilder()
				.addStanzaIniziale("Atrio")
				.addAttrezzo("martello", 3)
				.addStanzaVincente("Biblioteca")
				.addAdiacenza("Atrio", "Biblioteca", "nord")
				.addAdiacenza("Biblioteca", "Atrio", "sud")
				.getLabirinto();
		this.io = new IOConsole();
		this.partita = new Partita(labirinto, io);
		this.spada = new Attrezzo("spada", 3);
		this.parametro = "spada";
		this.comando = new ComandoPrendi();
	}
	
	/**
	 * FIXURE
	 */
	public void creaAttrezzi() {
		for(int i = 0; i<10; i++) {
			this.partita.getGiocatore().getBorsa().addAttrezzo(new Attrezzo("object"+i, 1));
		}
	}
	
	@Test
	public void testComandoPrendi_AttrezzoPreso() {
		partita.getStanzaCorrente().addAttrezzo(spada);
		comando.setParametro(parametro);
		comando.esegui(partita);
		assertTrue(partita.getGiocatore().getBorsa().hasAttrezzo(parametro));
	}
	
	@Test
	public void testComandoPrendi_AttrezzoPresoNull() {
		comando.setParametro(parametro);
		comando.esegui(partita);
		assertFalse(partita.getGiocatore().getBorsa().hasAttrezzo(parametro));
	}
	
	@Test
	public void testComandoPrendi_BorsaPienaPesante() {
		this.creaAttrezzi();
		partita.getStanzaCorrente().addAttrezzo(spada);
		comando.setParametro(parametro);
		comando.esegui(partita);
		assertFalse(partita.getGiocatore().getBorsa().hasAttrezzo(parametro));
	}

}
