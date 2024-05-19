package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.BeforeEach;

import it.uniroma3.diadia.ambienti.*;

import it.uniroma3.diadia.comandi.*;

import it.uniroma3.diadia.IOConsole.*;

class ComandoVaiTest {
	private Partita partita;
	private Labirinto labirinto;
	private IO io;
	private Comando comando;
	private Stanza stanza1;
	private Stanza stanza2;
	
	@BeforeEach
	public void setUp() {
		this.labirinto = new LabirintoBuilder()
				.addStanzaIniziale("Atrio")
				.addAttrezzo("spada", 3)
				.addStanzaVincente("Biblioteca")
				.addAdiacenza("Atrio", "Biblioteca", "nord")
				.addAdiacenza("Biblioteca", "Atrio", "sud")
				.getLabirinto();
		this.io = new IOConsole();
		this.partita = new Partita(labirinto, io);
		this.stanza1 = new Stanza("stanza 1");
		this.stanza2 = new Stanza("stanza 2");
		this.comando = new ComandoVai();
	}
	
	@Test
	public void testComandoVaiDirezioneEsistente() {
		partita.setStanzaCorrente(stanza1);
		stanza1.impostaStanzaAdiacente("sud", stanza2);
		comando.setParametro("sud");
		comando.esegui(partita);
		assertEquals(stanza2, partita.getStanzaCorrente());
	}
	
	@Test
	public void testComandoVaiDirezioneInesistente() {
		partita.setStanzaCorrente(stanza1);
		stanza1.impostaStanzaAdiacente("sud", stanza2);
		comando.setParametro("nord");
		comando.esegui(partita);
		assertNotEquals(stanza2, partita.getStanzaCorrente());
	}
}
