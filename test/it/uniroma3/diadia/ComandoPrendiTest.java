package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Scanner;

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
	private Comando comando;
	private String parametro1;
	private String parametro2;

	@BeforeEach
	public void setUp() throws Exception{
		Scanner scannerDiLinee = new Scanner(System.in);
		this.labirinto = Labirinto.newBuilder("labirinto.txt").getLabirinto();
		IO io = new IOConsole(scannerDiLinee);
		this.partita = new Partita(labirinto, io);
		this.parametro1 = "martello";
		this.parametro2 = "ciondolo";
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
		comando.setParametro(parametro1);
		comando.esegui(partita);
		assertTrue(partita.getGiocatore().getBorsa().hasAttrezzo(parametro1));
	}
	
	@Test
	public void testComandoPrendi_AttrezzoPresoNull() {
		comando.setParametro(parametro2);
		comando.esegui(partita);
		assertFalse(partita.getGiocatore().getBorsa().hasAttrezzo(parametro2));
	}
	
	@Test
	public void testComandoPrendi_BorsaPienaPesante() {
		this.creaAttrezzi();
		comando.setParametro(parametro1);
		comando.esegui(partita);
		assertFalse(partita.getGiocatore().getBorsa().hasAttrezzo(parametro1));
	}

}
