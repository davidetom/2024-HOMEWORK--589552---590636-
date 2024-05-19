package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaBuia extends Stanza {
	private String nomeAttrezzo;
	
	public StanzaBuia(String nome) {
		super(nome);
		this.nomeAttrezzo = "lanterna";
	}
	
	//QUI HO SOVRASCRITTO IL GETDESCRIZIONE INVECE CHE DIRETTAMENTE TOSTRING
	@Override
	public String getDescrizione() {
		String descrizione = "Qui c'è un buio pesto";
		Attrezzo attrezzo = super.getAttrezzo(nomeAttrezzo);
		if(attrezzo==null)
			return descrizione;
		else
			return super.getDescrizione();
	}
	/*@Override
	public String toString() {
		Attrezzo attrezzo = super.getAttrezzo(nomeAttrezzo);
		if(attrezzo == null) {
			String descrizione = "Qui c'è un buio pesto";
			return descrizione;
		}
		else
			return super.toString();
	}*/

}
