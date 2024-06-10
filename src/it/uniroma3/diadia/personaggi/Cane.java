package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Cane extends AbstractPersonaggio {
	
	private static final String MESSAGGIO_MORSO = "CRUNCH!";
	private static final String CIBO_PREFERITO = 	"osso";
	private Attrezzo attrezzo;
	
	public Cane(String nome, String presentazione) {
		super(nome, presentazione);
		this.attrezzo = new Attrezzo("chiave", 2);
	}
	
	public Cane(String nome, String presentazione, String nomeAttrezzo, String peso) {
		super(nome, presentazione);
		this.attrezzo = new Attrezzo(nomeAttrezzo, Integer.parseInt(peso));
	}
	
	public Cane(String nome, String presentazione, String nomeAttrezzo, int peso) {
		super(nome, presentazione);
		this.attrezzo = new Attrezzo(nomeAttrezzo, peso);
	}
	
	@Override
	public String agisci(Partita partita) {
		partita.getGiocatore().setCfu(partita.getGiocatore().getCfu()-1);
		return MESSAGGIO_MORSO;
	}
	
	@Override
	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
		String msg;
		if(this.attrezzo.getNome()==CIBO_PREFERITO) {
			msg = "Gnam!";
			partita.getStanzaCorrente().addAttrezzo(attrezzo);
			return msg;
		}
		else
			msg = agisci(partita);
		return msg;
	}
}
