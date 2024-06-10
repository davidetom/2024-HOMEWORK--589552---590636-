package it.uniroma3.diadia.giocatore;

import java.util.*;

import it.uniroma3.diadia.Configuratore;
import it.uniroma3.diadia.attrezzi.Attrezzo;

/**
 * Classe che modella un oggetto borsa utilizzata dal giocatore
 * 
 * @see Attrezzo
 * @version Base
 */
public class Borsa {
	public final static int DEFAULT_PESO_MAX_BORSA= Configuratore.getPesoMax();
	private List<Attrezzo> attrezzi;
	private int pesoMax;
	
	/*costruttori della classe Borsa*/
	public Borsa() {
		this(DEFAULT_PESO_MAX_BORSA);
	}
	
	public Borsa(int pesoMax) {
		this.pesoMax = pesoMax;
		this.attrezzi = new ArrayList<Attrezzo>();
	}
	
	/*METODO PER AGGIUNGERE ATTREZZI ALLA BORSA*/
	public boolean addAttrezzo(Attrezzo attrezzo) {
		if(this.getPeso() + attrezzo.getPeso() > this.getPesoMax())
			return false;
		return this.attrezzi.add(attrezzo);
	}
	
	public int getPesoMax() {
		return this.pesoMax;
	}
	
	public int getNumeroAttrezzi() {
		return this.attrezzi.size();
	}
	
	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		Attrezzo a = null;
		Iterator<Attrezzo> iteratore = this.attrezzi.iterator();
		while(iteratore.hasNext()) {
			a = iteratore.next();
			if(a.getNome().equals(nomeAttrezzo))
				return a;
		}
		return null;
	}
	
	public int getPeso() {
		int peso=0;
		Iterator<Attrezzo> iteratore = this.attrezzi.iterator();
		while(iteratore.hasNext()) {
			Attrezzo a = iteratore.next();
			peso+=a.getPeso();
		}
		return peso;
	}
	
	public boolean isEmpty() {
		return this.attrezzi.size()==0;
	}
	
	public boolean hasAttrezzo(String nomeAttrezzo) {
		return this.getAttrezzo(nomeAttrezzo)!=null;
	}
	
	public Attrezzo removeAttrezzo(String nomeAttrezzo) {
		Attrezzo a = null;
		Iterator<Attrezzo> iteratore = this.attrezzi.iterator();
		while(iteratore.hasNext()) {
			a = iteratore.next();
			if(a.getNome().equals(nomeAttrezzo)) {
				iteratore.remove();
				return a;
			}
		}
		return null;
	}
	
	public String toString() {
		StringBuilder s =new StringBuilder();
		if(!this.isEmpty()) {
			s.append("Contenuto borsa ("+this.getPeso()+"kg/"+this.getPesoMax()+"kg): ");
			for(Attrezzo a : this.attrezzi)
				s.append(a.toString()+" ");
		}
		else
			s.append("Borsa vuota");
		return s.toString();	
	}
	
	/**
	 * Overloading del metodo toString
	 * 
	 */
	public String toString(List<Attrezzo> lista) {
		StringBuilder s = new StringBuilder();
		s.append("[ ");
		for(Attrezzo a : lista) {
			s.append(a.getNome()+":"+a.getPeso()+", ");
		}
		s.deleteCharAt(s.length()-2);
		s.append("]");
		return s.toString();
	}
	
	public String toString(SortedSet<Attrezzo> insieme) {
		StringBuilder s = new StringBuilder();
		Attrezzo a = null;
		s.append("{ ");
		Iterator<Attrezzo> iteratore = insieme.iterator();
		while(iteratore.hasNext()) {
			a = iteratore.next();
			s.append(a.getNome()+":"+a.getPeso()+", ");
		}
		s.deleteCharAt(s.length()-2);
		s.append("}");
		return s.toString();
	}
	
	public String toString(Map<Integer, Set<Attrezzo>> mappa) {
		StringBuilder s = new StringBuilder();
		Set<Integer> chiavi = mappa.keySet();
		Iterator<Integer> iteratore_chiavi = chiavi.iterator();
		while(iteratore_chiavi.hasNext()) {
			int k = iteratore_chiavi.next();
			s.append("("+k+", { ");
			Iterator<Attrezzo> iteratore_attr = mappa.get(k).iterator();
			while(iteratore_attr.hasNext()) {
				Attrezzo a = iteratore_attr.next();
				s.append(a.getNome()+", ");
			}
			s.deleteCharAt(s.length()-2);
			s.append("} ) ; ");
		}
		s.delete(s.length()-3, s.length());
		return s.toString();
	}
	
	public List<Attrezzo> getContenutoOrdinatoPerPeso(){
		final List<Attrezzo> ordinata = new ArrayList<>(this.attrezzi);
		final ComparatorePerPeso cmp = new ComparatorePerPeso();
		Collections.sort(ordinata, cmp);
		return ordinata;	
	}
	
	public SortedSet<Attrezzo> getContenutoOrdinatoPerNome(){
		final SortedSet<Attrezzo> ordinata = new TreeSet<>(this.attrezzi);
		return ordinata;
	}
	
	public Map<Integer,Set<Attrezzo>> getContenutoRaggruppatoPerPeso(){
		final Map<Integer,Set<Attrezzo>> peso2attrezzi = new HashMap<>();
		for(Attrezzo attrezzo : this.attrezzi) {
			if(peso2attrezzi.containsKey(attrezzo.getPeso())){
				//questo attrezzo ha un peso che ho già visto
				//pesco il vecchio Set con lo stesso peso e aggiungo il nuovo arrivato
				final Set<Attrezzo> stessoPeso = peso2attrezzi.get(attrezzo.getPeso());
				stessoPeso.add(attrezzo);
			}
			else {
				//questo attrezzo ha un peso che non ho mai visto prima
				//creo un nuovo Set per ospitare tutti gli attrezzi correnti e futuri con questo peso
				final Set<Attrezzo> nuovoSet = new HashSet<>(); 
				nuovoSet.add(attrezzo);
				peso2attrezzi.put(attrezzo.getPeso(), nuovoSet);
			}
		}
		return peso2attrezzi;
	}
	
	public SortedSet<Attrezzo> getSortedSetOrdinataPerPeso(){
		final ComparatorePerPeso cmp = new ComparatorePerPeso();
		final SortedSet<Attrezzo> ordinata = new TreeSet<>(cmp);
		for(Attrezzo a : this.attrezzi)
			ordinata.add(a);
		return ordinata;
	}
	
	static class ComparatorePerPeso implements Comparator<Attrezzo>{
		
		@Override
		public int compare(Attrezzo a1, Attrezzo a2) {
			int cmp = a1.getPeso()-a2.getPeso();
			if(cmp == 0) {
				cmp = a1.getNome().compareTo(a2.getNome());
			}
			return cmp;
		}
	}
 }
