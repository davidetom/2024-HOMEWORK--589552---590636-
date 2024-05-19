package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.attrezzi.Attrezzo;

import java.util.*;

/**
 * Classe StanzaProtected - una stanza in un gioco di ruolo.
 * Una stanza e' un luogo fisico nel gioco.
 * E' collegata ad altre stanze attraverso delle uscite.
 * Ogni uscita e' associata ad una direzione.
 * 
 * @author docente di POO 
 * @see Attrezzo
 * @version base
*/

public class StanzaProtected {
	static final protected int NUMERO_MASSIMO_DIREZIONI = 4;
	static final protected int NUMERO_MASSIMO_ATTREZZI = 10;
	
	protected String nome;
	protected Map<String, Attrezzo> attrezzi;
	protected int numeroAttrezzi;
	protected Map<String,Stanza> stanzeAdiacenti;
	protected int numeroStanzeAdiacenti;
	/*protected Attrezzo[] attrezzi;
	protected int numeroAttrezzi;
	protected Stanza[] stanzeAdiacenti;
	protected int numeroStanzeAdiacenti;
	protected String[] direzioni;*/
	
    /**
     * Crea una stanza. Non ci sono stanze adiacenti, non ci sono attrezzi.
     * @param nome il nome della stanza
     */
	public StanzaProtected(String nome) {
		this.nome = nome;
		this.numeroStanzeAdiacenti = 0;
		this.numeroAttrezzi = 0;
		this.attrezzi = new HashMap<>();
		this.stanzeAdiacenti = new HashMap<>();
		/*this.direzioni = new String[NUMERO_MASSIMO_DIREZIONI];
		this.stanzeAdiacenti = new Stanza[NUMERO_MASSIMO_DIREZIONI];
		this.attrezzi = new Attrezzo[NUMERO_MASSIMO_ATTREZZI];*/
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
	public void impostaStanzaAdiacente(String direzione, Stanza stanza) {
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
		/*for(int i=0; i<this.direzioni.length; i++)
			if(direzioni.equals(this.direzioni[i])) {
				this.stanzeAdiacenti[i] = stanza;
				aggiornato = true;
			}
		if(!aggiornato)
			if(this.numeroAttrezzi < NUMERO_MASSIMO_DIREZIONI) {
				this.direzioni[numeroStanzeAdiacenti] = direzioni;
				this.stanzeAdiacenti[numeroStanzeAdiacenti] = stanza;
				this.numeroStanzeAdiacenti++;
			}*/
	}
	
    /**
     * Restituisce la stanza adiacente nella direzione specificata
     * @param direzione
     */	
	public Stanza getStanzaAdiacente(String direzione) {
        /*Stanza stanza = null;
		for(int i=0; i<this.numeroStanzeAdiacenti; i++)
        	if (this.direzioni[i].equals(direzione))
        		stanza = this.stanzeAdiacenti[i];
        return stanza;*/
		return this.stanzeAdiacenti.get(direzione);
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

    /**
     * Mette un attrezzo nella stanza.
     * @param attrezzo l'attrezzo da mettere nella stanza.
     * @return true se riesce ad aggiungere l'attrezzo, false atrimenti.
     */
    public boolean addAttrezzo(Attrezzo attrezzo) {
        if (this.numeroAttrezzi < NUMERO_MASSIMO_ATTREZZI) {
        	this.attrezzi.put(attrezzo.getNome(), attrezzo);
        	this.numeroAttrezzi++;
        	return true;
        }
        return false;
        	/*this.attrezzi[numeroAttrezzi] = attrezzo;
        	this.numeroAttrezzi++;
        	return true;
        }
        else {
        	return false;
        }*/
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
    	/*int i=0;
    	for(; i<numeroAttrezzi; i++) {
    		if(this.attrezzi[i].getNome().equals(nomeAttrezzo)) {
    			break;
    		}
    	}
    	if(i == numeroAttrezzi) return false;
    	
    	for(; i<numeroAttrezzi-1; i++) {
    		this.attrezzi[i] = this.attrezzi[i+1];
    	}
    	numeroAttrezzi--;
    	return true;*/
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
    	/*for (String direzione : this.direzioni)
    		if (direzione!=null)
    			risultato.append(" " + direzione);
    	risultato.append("\nAttrezzi nella stanza: ");
    	if(numeroAttrezzi!=0) {
    		for (int i=0; i<this.numeroAttrezzi; i++) {
    			Attrezzo attrezzo = this.attrezzi[i];
    			if(attrezzo!=null)
    				risultato.append(attrezzo.toString()+" , ");
    		}
    	}*/
    }

    /**
	* Controlla se un attrezzo esiste nella stanza (uguaglianza sul nome).
	* @return true se l'attrezzo esiste nella stanza, false altrimenti.
	*/
	public boolean hasAttrezzo(String nomeAttrezzo) {
		return this.attrezzi.containsKey(nomeAttrezzo);
		/*boolean trovato;
		trovato = false;
		for (int i=0; i<this.numeroAttrezzi; i++) {
			if (attrezzi[i].getNome().equals(nomeAttrezzo))
				trovato = true;
		}
		return trovato;*/
	}

	/**
     * Restituisce l'attrezzo nomeAttrezzo se presente nella stanza.
	 * @param nomeAttrezzo
	 * @return l'attrezzo presente nella stanza.
     * 		   null se l'attrezzo non e' presente.
	 */
	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		/*Attrezzo attrezzoCercato;
		attrezzoCercato = null;
		for (int i=0; i<this.numeroAttrezzi; i++) {
			if (attrezzi[i].getNome().equals(nomeAttrezzo))
				attrezzoCercato = attrezzi[i];
		}
		return attrezzoCercato;	*/
		return this.attrezzi.get(nomeAttrezzo);
	}

	public Set<String> getDirezioni() {
		return this.stanzeAdiacenti.keySet();
		/*String[] direzioni = new String[this.numeroStanzeAdiacenti];
	    for(int i=0; i<this.numeroStanzeAdiacenti; i++)
	    	direzioni[i] = this.direzioni[i];
	    return direzioni;*/
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
		Stanza that = (Stanza)obj;
		return this.getNome().equals(that.getNome());
	}
	
	
}
