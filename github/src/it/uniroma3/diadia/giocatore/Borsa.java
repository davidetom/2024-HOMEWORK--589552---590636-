package it.uniroma3.diadia.giocatore;

import it.uniroma3.diadia.attrezzi.Attrezzo;

/**
 * Classe che modella un oggetto borsa utilizzata dal giocatore
 * 
 * @see Attrezzo
 * @version Base
 */
public class Borsa {
	public final static int DEFAULT_PESO_MAX_BORSA=10;
	private Attrezzo[] attrezzi;
	public int numeroAttrezzi;
	private int pesoMax;
	
	/*costruttori della classe Borsa*/
	public Borsa() {
		this(DEFAULT_PESO_MAX_BORSA);
	}
	
	public Borsa(int pesoMax) {
		this.pesoMax = pesoMax;
		this.attrezzi = new Attrezzo[10];
		this.numeroAttrezzi = 0;
	}
	
	/*METODO PER AGGIUNGERE ATTREZZI ALLA BORSA*/
	public boolean addAttrezzo(Attrezzo attrezzo) {
		if(this.getPeso() + attrezzo.getPeso() > this.getPesoMax())
			return false;
		if(this.numeroAttrezzi==10)
			return false;
		this.attrezzi[this.numeroAttrezzi] = attrezzo;
		this.numeroAttrezzi++;
		return true;
	}
	
	public int getPesoMax() {
		return pesoMax;
	}
	
	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		Attrezzo a = null;
		for(int i=0; i<this.numeroAttrezzi; i++)
			if(this.attrezzi[i].getNome().equals(nomeAttrezzo))
				a=attrezzi[i];
		return a;
	}
	
	public int getPeso() {
		int peso=0;
		for(int i=0; i<this.numeroAttrezzi; i++)
			peso+= this.attrezzi[i].getPeso();
		return peso;
	}
	
	public boolean isEmpty() {
		return this.numeroAttrezzi==0;
	}
	
	public boolean hasAttrezzo(String nomeAttrezzo) {
		return this.getAttrezzo(nomeAttrezzo)!=null;
	}
	
	public Attrezzo removeAttrezzo(String nomeAttrezzo) {
		Attrezzo a = null;
		int i=0;
		for(; i<this.numeroAttrezzi; i++) {
			if(this.attrezzi[i].getNome().equals(nomeAttrezzo)) {
				a=this.attrezzi[i];
				break;
			}
		}
		if(i == numeroAttrezzi) return null;
		
		for(; i<this.numeroAttrezzi-1; i++) {
			this.attrezzi[i] = this.attrezzi[i+1];
		}
		this.numeroAttrezzi--;
		return a;
	}
	
	public String toString() {
		StringBuilder s =new StringBuilder();
		if(!this.isEmpty()) {
			s.append("Contenuto borsa ("+this.getPeso()+"kg/"+this.getPesoMax()+"kg): ");
			for(int i=0; i<this.numeroAttrezzi; i++)
				s.append(attrezzi[i].toString()+" ");
		}
		else
			s.append("Borsa vuota");
		return s.toString();	
	}
 }
