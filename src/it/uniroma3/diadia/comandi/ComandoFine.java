package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;


public class ComandoFine extends AbstractComando{
	private final static String NOME="fine";

	@Override
	public void esegui(Partita partita) {
		partita.getIO().mostraMessaggio("Grazie di aver giocato!");
		partita.setFinita();
	}
	
	@Override
	public String getNome() {
		return NOME;
	}
}
