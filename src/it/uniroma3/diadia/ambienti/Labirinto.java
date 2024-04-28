package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.attrezzi.Attrezzo;

/**
 * Questa classe modella un labirinto per il gioco principale e assegna alle stanze gli oggetti
 * 
 * @see Stanza
 * @see Attrezzo
 * @version base
 */

public class Labirinto {
	private Stanza stanzaIniziale;
	private Stanza stanzaVincente;
	
	public Labirinto() {
		creaStanze();
	}
	
	private void creaStanze() {
		/*crea gli attrezzi*/
		Attrezzo lanterna = new Attrezzo("lanterna", 3);
		Attrezzo osso = new Attrezzo("osso", 1);
		
		/*crea stanze del labirinto*/
		Stanza atrio = new Stanza("Atrio");
		Stanza aulaN11 = new Stanza("Alula N11");
		Stanza aulaN10 = new Stanza("Aula N10");
		Stanza laboratorio = new Stanza("Laboratorio Campus");
		Stanza biblioteca = new Stanza("Biblioteca");
		
		/*collega le stanze tra loro*/
		atrio.impostaStanzaAdiacente("nord", biblioteca);
		atrio.impostaStanzaAdiacente("est", aulaN11);
		atrio.impostaStanzaAdiacente("sud", aulaN10);
		atrio.impostaStanzaAdiacente("ovest", laboratorio);
		aulaN11.impostaStanzaAdiacente("est", laboratorio);
		aulaN11.impostaStanzaAdiacente("ovest", atrio);	
		aulaN10.impostaStanzaAdiacente("nord", atrio);
		aulaN10.impostaStanzaAdiacente("est", aulaN11);
		aulaN10.impostaStanzaAdiacente("ovest", laboratorio);
		laboratorio.impostaStanzaAdiacente("est", atrio);
		laboratorio.impostaStanzaAdiacente("ovest", aulaN11);
		biblioteca.impostaStanzaAdiacente("sud", atrio);
		
		/*pone attrezzi nelle stanze*/
		aulaN10.addAttrezzo(lanterna);
		atrio.addAttrezzo(osso);
		
		/*Il gioco comincia nell'atrio*/
		stanzaIniziale = atrio;
		stanzaVincente = biblioteca;
	}
	
	public Stanza getStanzaVincente() {
		return this.stanzaVincente;
	}
	
	public void setStanzaIniziale(Stanza stanzaIniziale) {
		this.stanzaIniziale = stanzaIniziale;
	}
	
	public Stanza getStanzaIniziale() {
		return this.stanzaIniziale;
	}
}
