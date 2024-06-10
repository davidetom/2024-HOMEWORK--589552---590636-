package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.BeforeEach;

import it.uniroma3.diadia.ambienti.*;

import it.uniroma3.diadia.comandi.*;

import it.uniroma3.diadia.IOConsole.*;

import java.util.Scanner;

class ComandoVaiTest {
	private Partita partita;
	private Labirinto labirinto;
	private Comando comando;
	
	@BeforeEach
	public void setUp() throws Exception {
		Scanner scannerDiLinee = new Scanner(System.in);
		this.labirinto = Labirinto.newBuilder("labirinto.txt").getLabirinto();
		IO io = new IOConsole(scannerDiLinee);
		this.partita = new Partita(labirinto, io);
		this.comando = new ComandoVai();
	}
	
	@Test
	public void testComandoVaiDirezioneEsistente() {
		comando.setParametro("nord");
		comando.esegui(partita);
		assertEquals("Corridoio", partita.getStanzaCorrente().getNome());
	}

	@Test
	public void testComandoVaiDirezioneInesistente() {
		comando.setParametro("sud");
		comando.esegui(partita);
		assertEquals("Atrio", partita.getStanzaCorrente().getNome());
	}
}
