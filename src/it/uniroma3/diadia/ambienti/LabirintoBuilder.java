package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.attrezzi.*;

import java.util.*;

public class LabirintoBuilder{
	private Labirinto labirinto;
	private Stanza ultimaStanza;
	private Map<String, Stanza> mappaStanze;
	
	public LabirintoBuilder() {
		this.labirinto = new Labirinto();
		this.mappaStanze = new HashMap<>();
	}
	
	public Labirinto getLabirinto() {
		return this.labirinto;
	}
	
	/*public List<Stanza> getListaStanze(){
		List<Stanza> lista = new ArrayList<>(mappaStanze.values());
		return lista;
	}*/
	
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
		StanzaBuia stanza = new StanzaBuia(nome); //attrezzo);
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
	
	public LabirintoBuilder addAdiacenza(String stanza, String adiacenza, String direzione) {
		Stanza s1 = mappaStanze.get(stanza);
		Stanza s2 = mappaStanze.get(adiacenza);
		s1.impostaStanzaAdiacente(direzione, s2);
		return this;
	}
}
