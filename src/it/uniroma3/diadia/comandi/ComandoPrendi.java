package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

import it.uniroma3.diadia.attrezzi.*;

public class ComandoPrendi extends AbstractComando{

	private static final String NOME = "prendi";
	
	@Override
	public void esegui(Partita partita) {
		if(this.getParametro()==null) {
			partita.getIO().mostraMessaggio("Quale attrezzo vuoi prendere ?");
			this.setParametro(partita.getIO().leggiRiga());
		}
		Attrezzo a = partita.getStanzaCorrente().getAttrezzo(this.getParametro());
		if(a == null) {
			partita.getIO().mostraMessaggio("Questo attrezzo non è presente nella stanza!");
			return;
		}
		partita.getStanzaCorrente().removeAttrezzo(this.getParametro());
			
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
		}
	}
	
	@Override
	public String getNome() {
		return NOME;
	}
}
