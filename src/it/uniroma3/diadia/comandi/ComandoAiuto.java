package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

public class ComandoAiuto implements Comando{
	
	private final static String NOME = "aiuto";
	
	@Override
	public void setParametro(String parametro) {};
	
	@Override
	public void esegui(Partita partita) {
		String[] elencoComandi = {"vai", "prendi", "posa", "aiuto", "fine"};
		for(int i=0; i< elencoComandi.length; i++) 
			partita.getIO().mostraMessaggio(elencoComandi[i]+" ");
		partita.getIO().mostraMessaggio("\n");
	}
	
	@Override
	public String getNome() {
		return NOME;
	}
	
	@Override
	public String getParametro() {
		return null;	
	}
}
