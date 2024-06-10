package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;


public class ComandoGuarda extends AbstractComando {
	private static final String NOME = "guarda";
	
	@Override
	public void esegui(Partita partita) {
		partita.getIO().mostraMessaggio(partita.getStanzaCorrente().getDescrizione());
		partita.getIO().mostraMessaggio(partita.getGiocatore().mostraInventario());
	}
	
	@Override
	public String getNome() {
		return NOME;
	}
}
