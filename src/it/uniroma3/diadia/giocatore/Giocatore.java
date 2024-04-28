package it.uniroma3.diadia.giocatore;

/**
 * Classe che gestisce i CFU del giocatore e di memorizzare gli attrezzi
 *  presenti nella borsa
 * @see Borsa
 * @version Base
 */

public class Giocatore {
	
	static final private int CFU_INIZIALI = 20;
	private int cfu;
	private Borsa borsa;
	
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
