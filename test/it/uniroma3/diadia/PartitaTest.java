package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.BeforeEach;

import it.uniroma3.diadia.ambienti.*;

 public class PartitaTest {
	 
	 private Labirinto labirinto;
	 private Partita partita;
	 private Stanza stanza;
	 
	 @BeforeEach
	 public void setUp() throws FileNotFoundException, FormatoFileNonValidoException{
		 this.labirinto = Labirinto.newBuilder("labirinto2.txt").getLabirinto();
		 this.partita = new Partita(labirinto);
		 this.stanza = new Stanza("stanza");
	 }
	 
	 @Test
	 public void testGetStanzaVincente() {
		 assertEquals("Biblioteca", labirinto.getStanzaVincente().getNome());
	 }
	 
	 @Test
	 public void testGetStanzaVincenteCambiata() {
		 partita.getLabirinto().setStanzaVincente(stanza);
		 assertEquals(stanza, partita.getLabirinto().getStanzaVincente());
	 }
	 
	 @Test
	 public void testIsFinitaPartitaNonTerminata() {
		 assertFalse(partita.isFinita());
	 }
}
