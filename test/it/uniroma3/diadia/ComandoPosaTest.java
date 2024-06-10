package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.comandi.*;

import it.uniroma3.diadia.attrezzi.*;

import it.uniroma3.diadia.ambienti.*;

import it.uniroma3.diadia.IOConsole.*;

import java.util.Scanner;

class ComandoPosaTest {
	/**
	 * FIXTURE
	 */
	private Partita partita;
	private Labirinto labirinto;
	private Comando comando;
	private Attrezzo lanterna;
	private String parametro;
	
	/**
	 * SetUp
	 */

	@BeforeEach
	public void setUp() throws Exception {
		Scanner scannerDiLinee = new Scanner(System.in);
		this.labirinto = Labirinto.newBuilder("labirinto.txt").getLabirinto();
		IO io = new IOConsole(scannerDiLinee);
		this.partita = new Partita(labirinto, io);
		this.lanterna = new Attrezzo("lanterna", 3);
		this.comando = new ComandoPosa();
		this.parametro = "lanterna";
	}
	
	/*FIXTURE*/
	public void creaAttrezzi() {
		for(int i=0; i<10; i++) {
			partita.getStanzaCorrente().addAttrezzo(new Attrezzo("attrezzo"+i, 1));
		}
	}
	
	@Test
	public void testComandoPosa_AttrezzoPosato() {
		partita.getGiocatore().getBorsa().addAttrezzo(lanterna);
		comando.setParametro(parametro);
		comando.esegui(partita);
		assertTrue(partita.getStanzaCorrente().hasAttrezzo(parametro));
	}
	
	@Test
	public void testComandoPosa_AttrezzoPosatoNull() {
		comando.setParametro(parametro);
		comando.esegui(partita);
		assertFalse(partita.getStanzaCorrente().hasAttrezzo(parametro));
	}
	
	@Test
	public void testComandoPosa_StanzaPiena() {
		this.creaAttrezzi();
		partita.getGiocatore().getBorsa().addAttrezzo(lanterna);
		comando.setParametro(parametro);
		comando.esegui(partita);
		assertFalse(partita.getStanzaCorrente().hasAttrezzo(parametro));
	}
}
