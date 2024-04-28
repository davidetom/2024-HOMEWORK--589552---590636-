package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

import it.uniroma3.diadia.attrezzi.*;

public class ComandoPrendi implements Comando{
	
	private String nomeAttrezzo;
	private final static String NOME = "prendi";
	
	@Override
	public void setParametro(String parametro) {
		this.nomeAttrezzo = parametro;
	}
	
	@Override
	public void esegui(Partita partita) {
		if(this.nomeAttrezzo==null) {
			partita.getIO().mostraMessaggio("Quale attrezzo vuoi prendere ?");
			this.nomeAttrezzo = partita.getIO().leggiRiga();
		}
		Attrezzo a = null;
		a = partita.getStanzaCorrente().getAttrezzo(nomeAttrezzo);
		if(a == null) {
			partita.getIO().mostraMessaggio("Questo attrezzo non è presente nella stanza!");
			return;
		}
		partita.getStanzaCorrente().removeAttrezzo(nomeAttrezzo);
			
		if(!partita.getGiocatore().getBorsa().addAttrezzo(a)) {
			partita.getStanzaCorrente().addAttrezzo(a);
			if(partita.getGiocatore().getBorsa().getNumeroAttrezzi() == 10) {
				partita.getIO().mostraMessaggio("Borsa piena! ");
			}
			else {
				partita.getIO().mostraMessaggio("Oggetto troppo pesante! ");
			}
		}
		else {
			partita.getIO().mostraMessaggio("Oggetto raccolto! ");
			//console.mostraMessaggio(partita.getStanzaCorrente().getDescrizione());
			//console.mostraMessaggio(partita.getGiocatore().mostraInventario());
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
