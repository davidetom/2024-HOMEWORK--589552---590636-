package it.uniroma3.diadia.giocatore;

import it.uniroma3.diadia.Configuratore;

/**
 * Classe che gestisce i CFU del giocatore e di memorizzare gli attrezzi
 *  presenti nella borsa
 * @see Borsa
 * @version Base
 */

public class Giocatore {
	
	static final private int CFU_INIZIALI = Configuratore.getCFU();
	public int cfu;
	public Borsa borsa;
	
	public Giocatore() {
		this.cfu = CFU_INIZIALI;
		this.borsa = new Borsa();
	}
	
	public int getCfu() {
		return this.cfu;
	}
	
	public void setCfu(int cfu) {
		this.cfu = cfu;
	}
	
	public String mostraInventario() {
		return this.borsa.toString();
	}
	
	public Borsa getBorsa() {
		return this.borsa;
	}
	
	
}
