package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;


public class ComandoGuarda implements Comando {
	private static final String NOME = "guarda";
	
	@Override
	public void setParametro(String parametro) {}
	
	@Override
	public void esegui(Partita partita) {
		partita.getIO().mostraMessaggio(partita.getStanzaCorrente().getDescrizione());
		partita.getIO().mostraMessaggio(partita.getGiocatore().mostraInventario());
	}
	
	@Override
	public String getNome() {
		return NOME;
	}
	
	@Override
	public String getParametro() {
		return null;
	}
}
