package it.uniroma3.diadia;

import it.uniroma3.diadia.ambienti.*;
import it.uniroma3.diadia.attrezzi.*;
import it.uniroma3.diadia.IOConsole.*;
/**
 * Classe principale di diadia, un semplice gioco di ruolo ambientato al dia.
 * Per giocare crea un'istanza di questa classe e invoca il metodo gioca
 *
 * Questa e' la classe principale crea e istanzia tutte le altre
 *
 * @author  docente di POO 
 *         (da un'idea di Michael Kolling and David J. Barnes) 
 *          
 * @version base
 */

public class DiaDia {
	private IOConsole console;
	static final private String MESSAGGIO_BENVENUTO = ""+
			"Ti trovi nell'Universita', ma oggi e' diversa dal solito...\n" +
			"Meglio andare al piu' presto in biblioteca a studiare. Ma dov'e'?\n"+
			"I locali sono popolati da strani personaggi, " +
			"alcuni amici, altri... chissa!\n"+
			"Ci sono attrezzi che potrebbero servirti nell'impresa:\n"+
			"puoi raccoglierli, usarli, posarli quando ti sembrano inutili\n" +
			"o regalarli se pensi che possano ingraziarti qualcuno.\n\n"+
			"Per conoscere le istruzioni usa il comando 'aiuto'.";
	
	static final private String[] elencoComandi = {"vai", "prendi", "posa", "aiuto", "fine"};

	private Partita partita;
	private Labirinto labirinto;

	public DiaDia() {
		this.labirinto=new Labirinto();
		this.partita = new Partita(labirinto); 
		console = new IOConsole();
	}
	
	/*public String scanner() {
		Scanner scannerDiLinee;
		scannerDiLinee = new Scanner(System.in);
		return scannerDiLinee.nextLine();
	}*/

	public void gioca() {
		String istruzione; 
		console.mostraMessaggio(MESSAGGIO_BENVENUTO);		
		do		
			istruzione = console.leggiRiga();
		
		while (!processaIstruzione(istruzione));
	}   


	/**
	 * Processa una istruzione 
	 *
	 * @return true se l'istruzione e' eseguita e il gioco continua, false altrimenti
	 */
	private boolean processaIstruzione(String istruzione) {
		if(istruzione.isEmpty()) return false;
		Comando comandoDaEseguire = new Comando(istruzione);
		if (comandoDaEseguire.getNome().equals("fine")) {
			this.fine(); 
			return true;
		} 
		else if (comandoDaEseguire.getNome().equals("vai")) {
			this.vai(comandoDaEseguire.getParametro());
			if(this.partita.getGiocatore().getCfu()==0) {
				console.mostraMessaggio("Partita terminata!");
				console.mostraMessaggio("Hai terminato i CFU...");
				partita.setFinita();
				return true;
			}
		}
		else if (comandoDaEseguire.getNome().equals("aiuto"))
			this.aiuto();
		else if (comandoDaEseguire.getNome().equals("prendi"))
			this.prendi(comandoDaEseguire.getParametro());
		else if (comandoDaEseguire.getNome().equals("posa"))
			this.posa(comandoDaEseguire.getParametro());		
		else
			console.mostraMessaggio("Comando sconosciuto");
		
		if (this.partita.vinta()) {
			console.mostraMessaggio("Hai vinto!");
			return true;
		} else
			return false;
	}   

	// implementazioni dei comandi dell'utente:

	/**
	 * Stampa informazioni di aiuto.
	 */
	private void aiuto() {
		for(int i=0; i< elencoComandi.length; i++) 
			console.mostraMessaggio(elencoComandi[i]+" ");
		console.mostraMessaggio("\n");
	}

	/**
	 * Cerca di andare in una direzione. Se c'e' una stanza ci entra 
	 * e ne stampa il nome, altrimenti stampa un messaggio di errore
	 */
	private void vai(String direzione) {
		if(direzione==null) {
			console.mostraMessaggio("Dove vuoi andare ?");
			direzione = console.leggiRiga();
		}
		
		Stanza prossimaStanza = null;
		prossimaStanza = this.partita.getStanzaCorrente().getStanzaAdiacente(direzione);
		if (prossimaStanza == null)
			console.mostraMessaggio("Direzione inesistente");
		else {
			this.partita.setStanzaCorrente(prossimaStanza);
			int cfu = this.partita.getGiocatore().getCfu();
			cfu--;
			this.partita.getGiocatore().setCfu(cfu);
		}
		console.mostraMessaggio(partita.getStanzaCorrente().getDescrizione() +
				"\nCfu rimasti: " + this.partita.getGiocatore().getCfu());
	}
	
	/**
	 * comando "prendi"
	 */
	private void prendi(String nomeAttrezzo) {
		if(nomeAttrezzo==null) {
			console.mostraMessaggio("Quale attrezzo vuoi prendere ?");
			nomeAttrezzo = console.leggiRiga();
		}
		Attrezzo a = null;
		a = this.partita.getStanzaCorrente().getAttrezzo(nomeAttrezzo);
		if(a == null) {
			console.mostraMessaggio("Questo attrezzo non è presente nella stanza!");
			return;
		}
		this.partita.getStanzaCorrente().removeAttrezzo(nomeAttrezzo);
		
		if(!this.partita.getGiocatore().getBorsa().addAttrezzo(a)) {
			this.partita.getStanzaCorrente().addAttrezzo(a);
			if(this.partita.getGiocatore().getBorsa().numeroAttrezzi == 10) {
				console.mostraMessaggio("Borsa piena! ");
			}
			else {
				console.mostraMessaggio("Oggetto troppo pesante! ");
			}
		}
		else {
			console.mostraMessaggio("Oggetto raccolto! ");
			console.mostraMessaggio(partita.getStanzaCorrente().getDescrizione());
			console.mostraMessaggio(this.partita.getGiocatore().mostraInventario());
		}
	}
	
	/**
	 * comando "posa"
	 */
	private void posa(String nomeAttrezzo) {
		if(nomeAttrezzo==null) {
			console.mostraMessaggio("Quale attrezzo vuoi posare?");
			nomeAttrezzo=console.leggiRiga();
		}
		Attrezzo a = null;
		a = this.partita.getGiocatore().getBorsa().removeAttrezzo(nomeAttrezzo);
		if(a==null) {
			console.mostraMessaggio("Questo attrezzo non è presente nella tua borsa!");
			return;
		}
		if(!this.partita.getStanzaCorrente().addAttrezzo(a)) {
			this.partita.getGiocatore().getBorsa().addAttrezzo(a);
			console.mostraMessaggio("La stanza e' piena! ");
		}
		else {
			console.mostraMessaggio("Attrezzo posato! ");	
			console.mostraMessaggio(partita.getStanzaCorrente().getDescrizione());
			console.mostraMessaggio(this.partita.getGiocatore().mostraInventario());
		}
	}

	/**
	 * Comando "Fine".
	 */
	private void fine() {
		console.mostraMessaggio("Grazie di aver giocato!");  // si desidera smettere
	}

	public static void main(String[] argc) {
		DiaDia gioco = new DiaDia();
		gioco.gioca();
	}
}