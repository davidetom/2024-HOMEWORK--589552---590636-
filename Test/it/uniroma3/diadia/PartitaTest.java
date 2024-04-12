package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.BeforeEach;

import it.uniroma3.diadia.ambienti.*;

 public class PartitaTest {
	 /*Fixture per metodo vinta*/
	 private Labirinto labirinto;
	 private Partita partita;
	 private Stanza corrente;
	 private Partita partita2;
	 private Partita partita3;
	 
	 /*Fixture per metodo isFinita*/
	 private Partita partita4;
	 private Partita partita5;
	 private Partita partita6;
	 private Partita partita7;
	 private Partita partita8;
	 private Partita partita9;
	 private Partita partita10;
	 private Partita partita11;
	 
	 @BeforeEach
	 public void setUp() {
		 /*Queste non so se le lasciamo perché chiamano labirinto*/
		 /*Stanza corrente e stanza vincente differenti*/
		 this.labirinto=new Labirinto();
		 this.partita = new Partita(labirinto);
		 this.corrente = new Stanza("corrente");
		 this.partita.setStanzaCorrente(corrente);
		 
		 /*stanza corrente uguale a stanza vincente*/
		 this.partita2 = new Partita(labirinto);
		 this.partita2.setStanzaCorrente(this.partita2.getLabirinto().getStanzaVincente());
		 
		 /*una delle due stanze è null*/
		 this.partita3 = new Partita(labirinto);
		 
		 /*Per verificare metodo isFinita*/
		 /*partita con giocatore con 0 cfu*/
		 this.partita4 = new Partita(labirinto);
		 this.partita.setFinita();
		 this.partita4.getGiocatore().setCfu(0);
		 
		 /*Partita vinta con cfu 0*/
		 this.partita5 = new Partita(labirinto);
		 this.partita5.setStanzaCorrente(this.partita5.getLabirinto().getStanzaVincente());
		 this.partita5.getGiocatore().setCfu(0);
		 
		 this.partita6 = new Partita(labirinto);
		 
		 /*partita finita e vinta con 0 cfu*/
		 this.partita7 = new Partita(labirinto);
		 this.partita7.setFinita();
		 this.partita7.setStanzaCorrente(this.partita7.getLabirinto().getStanzaVincente());
		 this.partita7.getGiocatore().setCfu(0);
		 
		 /*partita finita*/
		 this.partita8 = new Partita(labirinto);
		 this.partita8.setFinita();
		 
		 /*partita vinta*/
		 this.partita9 = new Partita(labirinto);
		 this.partita9.setStanzaCorrente(this.partita9.getLabirinto().getStanzaVincente());
		 
		 /*partita finita e vinta*/
		 this.partita10 = new Partita(labirinto);
		 this.partita10.setFinita();
		 this.partita10.setStanzaCorrente(this.partita10.getLabirinto().getStanzaVincente());
		 
		 /*partita finita per cfu terminati*/
		 this.partita11 = new Partita(labirinto);
		 this.partita11.getGiocatore().setCfu(0);
	 }
	 
	 /**
	  * Test metodo vinta
	  */
	 @Test
	 public void testVinta_StanzaCorrenteDiversaDaVincente() {
		 assertFalse(partita.vinta());
	 }
	 
	 @Test
	 public void testVinta_StanzaCorrenteUgualeVincente() {
		 assertTrue(partita2.vinta());
	 }
	 
	 @Test
	 public void testVinta_StanzaCorrenteInesistente() {
		 assertFalse(partita3.vinta());
	 }
	 
	 /**
	  * Test metodo isFinita
	  */
	 
	 @Test
	 public void testIsFinita_PartitaFinitaConCfu0() {
		 assertTrue(partita4.isFinita());
	 }
	 
	 @Test
	 public void testIsFinita_PartitaVintaConCfu0() {
		 assertTrue(partita5.isFinita());
	 }
	 
	 @Test
	 public void testIsFinita_PartitaNonVintaNonFinita() {
		 assertFalse(partita6.isFinita());
	 }
	 
	 @Test
	 public void testIsFinita_PartitaFinitaEVintaConCfu0() {
		 assertTrue(partita7.isFinita());
	 }
	 
	 @Test
	 public void testIsFinita_PartitaFinitaConCfu() {
		 assertTrue(partita8.isFinita());
	 }
	 
	 @Test
	 public void testIsFinita_PartitaVintaConCfu() {
		 assertTrue(partita9.isFinita());
	 }
	 
	 @Test
	 public void testIsFinita_PartitaFinitaVintaConCfu() {
		 assertTrue(partita10.isFinita());
	 }
	 
	 @Test
	 public void testIsFinita_PartitaNonFinitaNonVintaConCfu0() {
		 assertTrue(partita11.isFinita());
	 }
}
