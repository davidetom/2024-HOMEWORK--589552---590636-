package it.uniroma3.diadia;

import it.uniroma3.diadia.comandi.AbstractComando;

public class FakeComando extends AbstractComando{
	public final static String MESSAGGIO="Grazie per aver giocato!!";
	public final static String NOME = "Comando Fake";
	
	@Override
	public void esegui(Partita partita) {
		partita.setFinita();
	}
	
	@Override
	public String getNome() {
		return NOME;
	}
}
