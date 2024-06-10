package it.uniroma3.diadia.ambienti;

import java.io.FileNotFoundException;

import it.uniroma3.diadia.*;

import it.uniroma3.diadia.attrezzi.Attrezzo;

import it.uniroma3.diadia.personaggi.*;

import java.util.*;

/**
 * Questa classe modella un labirinto per il gioco principale e assegna alle stanze gli oggetti
 * 
 * @see Stanza
 * @see Attrezzo
 * @see Personaggi
 * @version base
 */

public class Labirinto {
	private Stanza stanzaIniziale;
	private Stanza stanzaVincente;

	private Labirinto(String nomeFile) throws FileNotFoundException, FormatoFileNonValidoException{
		CaricatoreLabirinto c = 
				new CaricatoreLabirinto(nomeFile);
		c.carica();
		this.stanzaIniziale = c.getStanzaIniziale();
		this.stanzaVincente = c.getStanzaVincente();
	}

	public void setStanzaVincente(Stanza vincente) {
		this.stanzaVincente = vincente;
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

	public static LabirintoBuilder newBuilder(String labirinto) throws FileNotFoundException, FormatoFileNonValidoException{
		return new LabirintoBuilder(labirinto);
	}

	/*LABIRINTOBUILDER NIDIFICATO*/
	public static class LabirintoBuilder{
		private Labirinto labirinto;
		private Stanza ultimaStanza;
		private Map<String, Stanza> mappaStanze;

		public LabirintoBuilder(String labirinto)throws FileNotFoundException, FormatoFileNonValidoException{
			this.labirinto = new Labirinto(labirinto);
			this.mappaStanze = new HashMap<>();
		}

		public Labirinto getLabirinto() {
			return this.labirinto;
		}

		public Map<String, Stanza> getListaStanze(){
			return this.mappaStanze;
		}

		public void setUltimaStanza(Stanza s) {
			this.ultimaStanza = s;
			this.mappaStanze.put(s.getNome(), s);
		}

		public LabirintoBuilder addStanzaIniziale(String nome) {
			Stanza stanza = new Stanza(nome);
			this.labirinto.setStanzaIniziale(stanza);
			this.setUltimaStanza(stanza);
			return this;
		}

		public LabirintoBuilder addStanzaVincente(String nome) {
			Stanza stanza = new Stanza(nome);
			this.labirinto.setStanzaVincente(stanza);
			this.setUltimaStanza(stanza);
			return this;
		}

		public LabirintoBuilder addStanza(String nome) {
			Stanza stanza = new Stanza(nome);
			this.setUltimaStanza(stanza);
			return this;
		}

		public LabirintoBuilder addStanzaBuia(String nome, String attrezzo) {
			StanzaBuia stanza = new StanzaBuia(nome, attrezzo);
			this.setUltimaStanza(stanza);
			return this;
		}

		public LabirintoBuilder addStanzaMagica(String nome, int soglia) {
			StanzaMagica stanza = new StanzaMagica(nome, soglia);
			this.setUltimaStanza(stanza);
			return this;
		}

		public LabirintoBuilder addStanzaBloccata(String nome, String dirBloccata, String key) {
			StanzaBloccata stanza = new StanzaBloccata(nome, dirBloccata, key);
			this.setUltimaStanza(stanza);
			return this;
		}

		public LabirintoBuilder addAttrezzo(String nome, int peso) {
			Attrezzo a = new Attrezzo(nome, peso);
			if(this.ultimaStanza!=null)
				this.ultimaStanza.addAttrezzo(a);
			return this;
		}

		public LabirintoBuilder addMago(String nome, String nomeAttrezzo, int peso) {
			Mago m = new Mago(nome, "abracadabra", nomeAttrezzo, peso);
			if(this.ultimaStanza!=null)
				this.ultimaStanza.addPersonaggio(m);
			return this;
		}

		public LabirintoBuilder addStrega(String nome) {
			Strega s = new Strega(nome, "magicabula!");
			if(this.ultimaStanza!=null) {
				this.ultimaStanza.addPersonaggio(s);
			}
			return this;
		}

		public LabirintoBuilder addCane(String nome, String nomeAttrezzo, int peso) {
			Cane c = new Cane(nome, "bau bau!", nomeAttrezzo, peso);
			if(this.ultimaStanza!=null) {
				this.ultimaStanza.addPersonaggio(c);
			}
			return this;
		}

		public LabirintoBuilder addAdiacenza(String stanza, String adiacenza, Direzione direzione) {
			Stanza s1 = mappaStanze.get(stanza);
			Stanza s2 = mappaStanze.get(adiacenza);
			s1.impostaStanzaAdiacente(direzione, s2);
			return this;
		}

		public LabirintoBuilder addAdiacenza(String stanza, String adiacenza, String direzione) {
			Stanza s1 = mappaStanze.get(stanza);
			Stanza s2 = mappaStanze.get(adiacenza);
			Direzione direzioneAdiacenza = Direzione.stringaToDirezione(direzione);
			s1.impostaStanzaAdiacente(direzioneAdiacenza, s2);
			return this;
		}
	}
}
