package it.uniroma3.diadia.ambienti;

public class StanzaBloccata extends Stanza {
	private Direzione direzioneBloccata;
	private String unlock;
	
	public StanzaBloccata(String nome, Direzione direzioneBloccata, String attrezzoSbloccante) {
		super(nome);
		this.direzioneBloccata = direzioneBloccata;
		this.unlock = attrezzoSbloccante;
	}
	
	public StanzaBloccata(String nome, String direzioneBloccata, String attrezzoSbloccante) {
		super(nome);
		this.direzioneBloccata = Direzione.stringaToDirezione(direzioneBloccata);
		this.unlock = attrezzoSbloccante;
	}

	@Override
	public Stanza getStanzaAdiacente(Direzione direzione) {
		if(!this.hasAttrezzo(unlock) && direzioneBloccata.equals(direzione)) {
			return this;
		}
		else return super.getStanzaAdiacente(direzione);
	}
	
	@Override
	public String getDescrizione() {
		if(!this.hasAttrezzo(unlock)) {
			String descrizione = "Stanza bloccata, vietato l'accesso senza l'attrezzo richiesto: chiave\n";
			return descrizione;
		}
		else return super.getDescrizione();
	}
	
}
