package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

import it.uniroma3.diadia.ambienti.Stanza;

public class ComandoVai implements Comando{
	private String direzione;
	private static final String NOME = "vai";
	/**
	* set parametro del comando
	*/
	@Override
	public void setParametro(String parametro) {
		this.direzione = parametro;
	}

	/**
	* esecuzione del comando
	*/
	@Override
	public void esegui(Partita partita) {
		Stanza stanzaCorrente = partita.getStanzaCorrente();
		Stanza prossimaStanza = null;
		if(this.direzione==null) {
			partita.getIO().mostraMessaggio("Dove vuoi andare ?" +"\n" 
									+ "Specifica una direzione");
			direzione = partita.getIO().leggiRiga();
		}
		
		prossimaStanza = stanzaCorrente.getStanzaAdiacente(this.direzione);
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
	
	@Override
	public String getParametro() {
		return null;
	}
}
