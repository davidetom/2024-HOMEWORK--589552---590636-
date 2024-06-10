package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;

public class ComandoSaluta extends AbstractComando {
	
	private static final String MESSAGGIO_CHI = "Chi dovrei salutare?...";
	private static final String NOME = "saluta";
	private String messaggio;
	
	@Override
	public void esegui(Partita partita) {
		AbstractPersonaggio personaggio;
		personaggio = partita.getStanzaCorrente().getPersonaggio();
		if(personaggio!=null) {
			this.messaggio = personaggio.saluta();
			partita.getIO().mostraMessaggio(this.messaggio);
		}
		else
			partita.getIO().mostraMessaggio(MESSAGGIO_CHI);
	}
	
	@Override
	public String getNome() {
		return NOME;
	}

}
