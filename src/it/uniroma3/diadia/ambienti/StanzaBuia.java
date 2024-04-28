package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaBuia extends Stanza {
	
	public StanzaBuia(String nome) {
		super(nome);
	}
	
	@Override
	public String toString() {
		Attrezzo attrezzo = super.getAttrezzo("lanterna");
    	if (attrezzo == null) {
    		String descrizione = "Qui c'è un buio pesto";
    		return descrizione;
    	}
    	else
    		return super.toString();
    }
}
