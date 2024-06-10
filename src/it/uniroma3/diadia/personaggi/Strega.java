package it.uniroma3.diadia.personaggi;

import java.util.*;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.ambienti.Direzione;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Strega extends AbstractPersonaggio{
	
	private static final String MESSAGGIO_SPOSTAMENTO_BUONO = "Ti ho spostato nella stanza con più attrezzi!";
	private static final String MESSAGGIO_SPOSTAMENTO_CATTIVO = "Ti ho spostato nella stanza con meno attrezzi!";
	
	public Strega(String nome, String presentazione) {
		super(nome, presentazione);
	}
	
	@Override
	public String agisci(Partita partita) {
		String msg;
		List<Stanza> stanzeOrdinate = this.getStanzeOrdinatePerNumeroDiAttrezzi(partita);
		
		if(this.haSalutato()) {
			partita.setStanzaCorrente(stanzeOrdinate.get(0));
			msg = MESSAGGIO_SPOSTAMENTO_BUONO;
		}
		else {
			partita.setStanzaCorrente(stanzeOrdinate.get(stanzeOrdinate.size()-1));
			msg = MESSAGGIO_SPOSTAMENTO_CATTIVO;
		}
		return msg;
	}
	
	public List<Stanza> getStanzeOrdinatePerNumeroDiAttrezzi(Partita partita){
		Stanza corrente = partita.getStanzaCorrente();
		Map<Stanza, Integer> stanza2numeroAttrezzi = new HashMap<>();
		for( Direzione d: corrente.getMapStanzeAdiacenti().keySet()) {
			Stanza adiacente = corrente.getMapStanzeAdiacenti().get(d);
			stanza2numeroAttrezzi.put(adiacente, Integer.valueOf(adiacente.getAttrezzi().size()));
		}
		List<Stanza> stanzeOrdinatePerAttrezzi = new ArrayList<Stanza>(stanza2numeroAttrezzi.keySet());
		Collections.sort(stanzeOrdinatePerAttrezzi, new Comparator<Stanza>(){
			
			@Override
			public int compare(Stanza o1, Stanza o2) {
				return stanza2numeroAttrezzi.get(o2)-stanza2numeroAttrezzi.get(o1);
			}
		});
		return stanzeOrdinatePerAttrezzi;
	}
	
	@Override
	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
		String msg = "AHAHAHAHAHA";
		return msg;
	}

}
