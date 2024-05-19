package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

import it.uniroma3.diadia.attrezzi.*;

public class ComandoPosa implements Comando {
	private String nomeAttrezzo;
	private static final String NOME = "posa";
	
	@Override
	public void setParametro(String parametro) {
		this.nomeAttrezzo = parametro;
	}
	
	@Override
	public void esegui(Partita partita) {
		if(nomeAttrezzo==null) {
			partita.getIO().mostraMessaggio("Quale attrezzo vuoi posare?");
			nomeAttrezzo=partita.getIO().leggiRiga();
		}
		Attrezzo a = null;
		a = partita.getGiocatore().getBorsa().removeAttrezzo(nomeAttrezzo);
		if(a==null) {
			partita.getIO().mostraMessaggio("Questo attrezzo non è presente nella tua borsa!");
			return;
		}
		if(!partita.getStanzaCorrente().addAttrezzo(a)) {
			partita.getGiocatore().getBorsa().addAttrezzo(a);
			partita.getIO().mostraMessaggio("La stanza e' piena! ");
		}
		else {
			partita.getIO().mostraMessaggio("Attrezzo posato! ");	
			partita.getIO().mostraMessaggio(partita.getStanzaCorrente().getDescrizione());
			partita.getIO().mostraMessaggio(partita.getGiocatore().mostraInventario());
		}
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
