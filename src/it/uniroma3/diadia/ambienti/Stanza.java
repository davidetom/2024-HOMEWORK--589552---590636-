package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.attrezzi.Attrezzo;

import it.uniroma3.diadia.personaggi.AbstractPersonaggio;

import java.util.*;

/**
 * Classe Stanza - una stanza in un gioco di ruolo.
 * Una stanza e' un luogo fisico nel gioco.
 * E' collegata ad altre stanze attraverso delle uscite.
 * Ogni uscita e' associata ad una direzione.
 * 
 * @author docente di POO 
 * @see Attrezzo
 * @version base
*/

public class Stanza {
	
	static final private int NUMERO_MASSIMO_DIREZIONI = 4;
	static final private int NUMERO_MASSIMO_ATTREZZI = 10;
	
	private String nome;
	private Map<String, Attrezzo> attrezzi;
    public int numeroAttrezzi;
	private Map<Direzione, Stanza> stanzeAdiacenti;
    private int numeroStanzeAdiacenti;
    private AbstractPersonaggio personaggio;
    
    /**
     * Crea una stanza. Non ci sono stanze adiacenti, non ci sono attrezzi.
     * @param nome il nome della stanza
     */
    public Stanza(String nome) {
        this.nome = nome;
        this.numeroStanzeAdiacenti = 0;
        this.numeroAttrezzi = 0;
        this.attrezzi = new HashMap<>();
        this.stanzeAdiacenti = new HashMap<>();
    }
    
    public int getNumeroStanzeAdiacenti() {
    	return this.numeroStanzeAdiacenti;
    }

    /**
     * Imposta una stanza adiacente.
     *
     * @param direzione direzione in cui sara' posta la stanza adiacente.
     * @param stanza stanza adiacente nella direzione indicata dal primo parametro.
     */
    public void impostaStanzaAdiacente(Direzione direzione, Stanza stanza) {
       boolean aggiornato = false;
       if(stanzeAdiacenti.containsKey(direzione)) {
    	   this.stanzeAdiacenti.put(direzione, stanza);
    	   aggiornato = true;
       }
       if(!aggiornato)
    	   if(this.numeroStanzeAdiacenti < NUMERO_MASSIMO_DIREZIONI) {
    		   this.stanzeAdiacenti.put(direzione, stanza);
    		   this.numeroStanzeAdiacenti++;
    	   }
    }

    /**
     * Restituisce la stanza adiacente nella direzione specificata
     * @param direzione
     */
	public Stanza getStanzaAdiacente(Direzione direzione) {
		return this.stanzeAdiacenti.get(direzione);
	}
	
	/**
	 * Restituisce la mappa di stanze adiacenti di una specifica stanza
	 */
	public Map<Direzione, Stanza> getMapStanzeAdiacenti(){
		return this.stanzeAdiacenti;
	}
	
    /**
     * Restituisce la nome della stanza.
     * @return il nome della stanza
     */
    public String getNome() {
        return this.nome;
    }

    /**
     * Restituisce la descrizione della stanza.
     * @return la descrizione della stanza
     */
    public String getDescrizione() {
        return this.toString();
    }

    /**
     * Restituisce la collezione di attrezzi presenti nella stanza.
     * @return la collezione di attrezzi nella stanza.
     */
    public Collection<Attrezzo> getAttrezzi() {
        return this.attrezzi.values();
    }
    
    public Map<String, Attrezzo> getMapAttrezzi(){
    	return this.attrezzi;
    }

    /**
     * Mette un attrezzo nella stanza.
     * @param attrezzo l'attrezzo da mettere nella stanza.
     * @return true se riesce ad aggiungere l'attrezzo, false atrimenti.
     */
    public boolean addAttrezzo(Attrezzo attrezzo) {
        if (this.numeroAttrezzi < NUMERO_MASSIMO_ATTREZZI && attrezzo!=null) {
        	this.attrezzi.put(attrezzo.getNome(), attrezzo);
        	this.numeroAttrezzi++;
        	return true;
        }
        return false;
    }
    
    
    /**
     * Rimuove un attrezzo nella stanza.
     * @param nomeAttrezzo nome dell'attrezzo da rimuovere nella stanza.
     * @return attrezzo rimosso
     */
    public boolean removeAttrezzo(String nomeAttrezzo) {
    	if(this.attrezzi.containsKey(nomeAttrezzo)) {
    		this.attrezzi.remove(nomeAttrezzo);
    		this.numeroAttrezzi--;
    		return true;
    	}
    	return false;
    }

   /**
	* Restituisce una rappresentazione stringa di questa stanza,
	* stampadone la descrizione, le uscite e gli eventuali attrezzi contenuti
	* @return la rappresentazione stringa
	*/
    public String toString() {
    	StringBuilder risultato = new StringBuilder();
    	risultato.append(this.nome);
    	risultato.append("\nUscite: ");
    	risultato.append(this.getDirezioni().toString());
    	risultato.append("\nAttrezzi nella stanza: ");
    	risultato.append(this.getAttrezzi().toString());
    	return risultato.toString();
    }

    /**
	* Controlla se un attrezzo esiste nella stanza (uguaglianza sul nome).
	* @return true se l'attrezzo esiste nella stanza, false altrimenti.
	*/
	public boolean hasAttrezzo(String nomeAttrezzo) {
		return this.attrezzi.containsKey(nomeAttrezzo);
	}

	/**
     * Restituisce l'attrezzo nomeAttrezzo se presente nella stanza.
	 * @param nomeAttrezzo
	 * @return l'attrezzo presente nella stanza.
     * 		   null se l'attrezzo non e' presente.
	 */
	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		return this.attrezzi.get(nomeAttrezzo);	
	}
	
	public Set<Direzione> getDirezioni() {
		return this.stanzeAdiacenti.keySet();
    }
	
	public boolean addPersonaggio(AbstractPersonaggio personaggio) {
		if(this.personaggio == null) {
			this.personaggio = personaggio;
			return true;
		}
		return false;
	}
	
	public AbstractPersonaggio getPersonaggio() {
		return this.personaggio;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(nome);
	}
	
	@Override
	public boolean equals(Object obj) {
		if(this==obj) return true;
		if(obj==null) return false;
		if(getClass()!=obj.getClass()) return false;
		Stanza that = (Stanza) obj;
		return this.getNome().equals(that.getNome());
	}
	
	public int prodottoVettoriale(List<Integer> v1, List<Integer> v2) {
		int prodotto = 0;
		for(int i = 0; i<v1.size(); i++) {
			if(i%2==0) {
				prodotto+=v1.get(i)*v2.get(i);
			}
		}
		return prodotto;
	}
	
}