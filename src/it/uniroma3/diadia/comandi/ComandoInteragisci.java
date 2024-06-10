package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

import it.uniroma3.diadia.personaggi.AbstractPersonaggio;

public class ComandoInteragisci extends AbstractComando{
	
	private static final String MESSAGGIO_CON_CHI = "Con chi dovrei interagire?...";
	private static final String NOME = "interagisci";
	private String messaggio;
	
	@Override
	public void esegui(Partita partita) {
		AbstractPersonaggio personaggio;
		personaggio = partita.getStanzaCorrente().getPersonaggio();
		if(personaggio!=null) {
			this.messaggio = personaggio.agisci(partita);
			partita.getIO().mostraMessaggio(this.messaggio);
		}
		else
			partita.getIO().mostraMessaggio(MESSAGGIO_CON_CHI);
	}
	
	@Override
	public String getNome() {
		return NOME;
	}
	
}
