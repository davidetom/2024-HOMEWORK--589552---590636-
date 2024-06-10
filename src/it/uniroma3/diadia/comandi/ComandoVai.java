package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

import it.uniroma3.diadia.ambienti.Stanza;

import it.uniroma3.diadia.ambienti.Direzione;

public class ComandoVai extends AbstractComando{

	private static final String NOME = "vai";

	/**
	* esecuzione del comando
	*/
	@Override
	public void esegui(Partita partita) {
		Stanza stanzaCorrente = partita.getStanzaCorrente();
		Stanza prossimaStanza = null;
		if(this.getParametro()==null) {
			partita.getIO().mostraMessaggio("Dove vuoi andare ?" +"\n" 
									+ "Specifica una direzione");
			this.setParametro(partita.getIO().leggiRiga());
		}
		
		prossimaStanza = stanzaCorrente.getStanzaAdiacente(Direzione.valueOf(this.getParametro()));
		if (prossimaStanza == null)
			partita.getIO().mostraMessaggio("Direzione inesistente");
		else {
			partita.setStanzaCorrente(prossimaStanza);
			int cfu = partita.getGiocatore().getCfu();
			cfu--;
			partita.getGiocatore().setCfu(cfu);
		}
		partita.getIO().mostraMessaggio(partita.getStanzaCorrente().getDescrizione() +
				"\nCfu rimasti: " + partita.getGiocatore().getCfu());
	}
	
	@Override
	public String getNome() {
		return NOME;
	}
}
