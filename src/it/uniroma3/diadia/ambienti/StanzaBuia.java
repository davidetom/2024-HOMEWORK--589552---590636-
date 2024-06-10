package it.uniroma3.diadia.ambienti;

public class StanzaBuia extends Stanza {
	private String nomeAttrezzo;
	
	public StanzaBuia(String nome) {
		super(nome);
		this.nomeAttrezzo = "lanterna";
	}
	
	public StanzaBuia(String nome, String attrezzo) {
		super(nome);
		this.nomeAttrezzo = attrezzo;
	}
	
	@Override
	public String getDescrizione() {
		String descrizione = "Qui c'è un buio pesto";
		if(!this.hasAttrezzo(nomeAttrezzo)) {
			return descrizione;
		}
		else
			return super.getDescrizione();
	}
}
