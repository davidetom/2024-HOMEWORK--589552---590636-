package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.IOConsole.*;

public abstract class AbstractComando implements Comando {
	
	private String parametro;
	private IO io;
	private final static String NOME = "Comando Astratto";
	
	abstract public void esegui(Partita partita);
	
	@Override
	public String getParametro() {
		return this.parametro;
	}
	
	@Override
	public void setParametro(String parametro) {
		this.parametro = parametro;
	}
	
	public IO getIO() {
		return io;
	}
	
	/**
	 * Metodo per i test
	 */
	@Override
	public String getNome() {
		return NOME;
	}

}
