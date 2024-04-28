package it.uniroma3.diadia;

import it.uniroma3.diadia.ambienti.*;

import it.uniroma3.diadia.giocatore.*;

import it.uniroma3.diadia.IOConsole.*;

/**
 * Questa classe modella una partita del gioco
 *
 * @author  docente di POO
 * @see Labirinto
 * @see Stanza
 * @see Giocatore
 * @see Borsa
 * @version base
 */

public class Partita {

	private Labirinto labirinto;
	private boolean finita;
	private Giocatore giocatore;
	private Stanza stanzaCorrente;
	private IO io;
	
	public Partita(Labirinto labirinto, IO io){  
		this.labirinto = labirinto;  
		this.giocatore = new Giocatore();
		this.finita = false;
		this.stanzaCorrente = this.labirinto.getStanzaIniziale();
		this.io = io;
		//this.cfu = CFU_INIZIALI;
	}
	
	public Labirinto getLabirinto() {
		return this.labirinto;
	}
	
	public Giocatore getGiocatore() {
		return this.giocatore;
	}
	
	public IO getIO() {
		return this.io;
	}
	
	public boolean giocatoreIsVivo() {
		return this.giocatore.getCfu() > 0;	
	}
	
	/**
	 * Restituisce vero se e solo se la partita e' stata vinta
	 * @return vero se partita vinta
	 */	
	public boolean vinta() {
		return getStanzaCorrente() == this.labirinto.getStanzaVincente();
	}
	

	/**
	 * Restituisce vero se e solo se la partita e' finita
	 * @return vero se partita finita
	 */
	public boolean isFinita() {
		return finita || vinta() || (giocatore.getCfu() == 0);
	}

	/**
	 * Imposta la partita come finita
	 *
	 */
	public void setFinita() {
		this.finita = true;
	}
	
	public Stanza getStanzaCorrente() {
		return this.stanzaCorrente;
	}
	
	public void setStanzaCorrente(Stanza stanzaCorrente) {
		this.stanzaCorrente=stanzaCorrente;
	}

}
