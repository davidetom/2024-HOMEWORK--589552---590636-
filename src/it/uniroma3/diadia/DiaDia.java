package it.uniroma3.diadia;

import it.uniroma3.diadia.ambienti.*;
import it.uniroma3.diadia.comandi.Comando;
import it.uniroma3.diadia.comandi.FabbricaDiComandiFisarmonica;
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
	static final private String MESSAGGIO_BENVENUTO = ""+
			"Ti trovi nell'Universita', ma oggi e' diversa dal solito...\n" +
			"Meglio andare al piu' presto in biblioteca a studiare. Ma dov'e'?\n"+
			"I locali sono popolati da strani personaggi, " +
			"alcuni amici, altri... chissa!\n"+
			"Ci sono attrezzi che potrebbero servirti nell'impresa:\n"+
			"puoi raccoglierli, usarli, posarli quando ti sembrano inutili\n" +
			"o regalarli se pensi che possano ingraziarti qualcuno.\n\n"+
			"Per conoscere le istruzioni usa il comando 'aiuto'.";
	
	private Partita partita;
	private Labirinto labirinto;

	public DiaDia(IO io) {
		this.labirinto=new Labirinto();
		this.partita = new Partita(new Labirinto(), io); 
	}
	
	public DiaDia(Labirinto lab, IO io) {
		this.partita = new Partita(lab, io);
	}

	public void gioca() {
		String istruzione; 
		this.partita.getIO().mostraMessaggio(MESSAGGIO_BENVENUTO);		
		do		
			istruzione = this.partita.getIO().leggiRiga();
		
		while (!processaIstruzione(istruzione));
	}   


	/**
	 * Processa una istruzione 
	 *
	 * @return true se l'istruzione e' eseguita e il gioco continua, false altrimenti
	 */
	private boolean processaIstruzione(String istruzione) {
		
		Comando comandoDaEseguire;
		FabbricaDiComandiFisarmonica factory = new FabbricaDiComandiFisarmonica();
		comandoDaEseguire = factory.costruisciComando(istruzione);
		comandoDaEseguire.esegui(this.partita);
		if(this.partita.vinta())
			this.partita.getIO().mostraMessaggio("Hai vinto! ");
		
		if(!this.partita.giocatoreIsVivo()) {
			this.partita.getIO().mostraMessaggio("Hai esaurito i Cfu...");
		}
		return this.partita.isFinita();
		
	}

	/**
	 * Stampa informazioni di aiuto.
	 */
	/*private void aiuto() {
		for(int i=0; i< elencoComandi.length; i++) 
			io.mostraMessaggio(elencoComandi[i]+" ");
		io.mostraMessaggio("\n");
	}*/

	/**
	 * Cerca di andare in una direzione. Se c'e' una stanza ci entra 
	 * e ne stampa il nome, altrimenti stampa un messaggio di errore
	 */
	/*private void vai(String direzione) {
		if(direzione==null) {
			io.mostraMessaggio("Dove vuoi andare ?");
			direzione = io.leggiRiga();
		}
		
		Stanza prossimaStanza = null;
		prossimaStanza = this.partita.getStanzaCorrente().getStanzaAdiacente(direzione);
		if (prossimaStanza == null)
			io.mostraMessaggio("Direzione inesistente");
		else {
			this.partita.setStanzaCorrente(prossimaStanza);
			int cfu = this.partita.getGiocatore().getCfu();
			cfu--;
			this.partita.getGiocatore().setCfu(cfu);
		}
		io.mostraMessaggio(partita.getStanzaCorrente().getDescrizione() +
				"\nCfu rimasti: " + this.partita.getGiocatore().getCfu());
	}*/
	
	/**
	 * comando "prendi"
	 */
	/*private void prendi(String nomeAttrezzo) {
		if(nomeAttrezzo==null) {
			io.mostraMessaggio("Quale attrezzo vuoi prendere ?");
			nomeAttrezzo = io.leggiRiga();
		}
		Attrezzo a = null;
		a = this.partita.getStanzaCorrente().getAttrezzo(nomeAttrezzo);
		if(a == null) {
			io.mostraMessaggio("Questo attrezzo non è presente nella stanza!");
			return;
		}
		this.partita.getStanzaCorrente().removeAttrezzo(nomeAttrezzo);
		
		if(!this.partita.getGiocatore().getBorsa().addAttrezzo(a)) {
			this.partita.getStanzaCorrente().addAttrezzo(a);
			if(this.partita.getGiocatore().getBorsa().numeroAttrezzi == 10) {
				io.mostraMessaggio("Borsa piena! ");
			}
			else {
				io.mostraMessaggio("Oggetto troppo pesante! ");
			}
		}
		else {
			io.mostraMessaggio("Oggetto raccolto! ");
			io.mostraMessaggio(partita.getStanzaCorrente().getDescrizione());
			io.mostraMessaggio(this.partita.getGiocatore().mostraInventario());
		}
	}*/
	
	/**
	 * comando "posa"
	 */
	/*private void posa(String nomeAttrezzo) {
		if(nomeAttrezzo==null) {
			io.mostraMessaggio("Quale attrezzo vuoi posare?");
			nomeAttrezzo=io.leggiRiga();
		}
		Attrezzo a = null;
		a = this.partita.getGiocatore().getBorsa().removeAttrezzo(nomeAttrezzo);
		if(a==null) {
			io.mostraMessaggio("Questo attrezzo non è presente nella tua borsa!");
			return;
		}
		if(!this.partita.getStanzaCorrente().addAttrezzo(a)) {
			this.partita.getGiocatore().getBorsa().addAttrezzo(a);
			io.mostraMessaggio("La stanza e' piena! ");
		}
		else {
			io.mostraMessaggio("Attrezzo posato! ");	
			io.mostraMessaggio(partita.getStanzaCorrente().getDescrizione());
			io.mostraMessaggio(this.partita.getGiocatore().mostraInventario());
		}
	}*/

	/**
	 * Comando "Fine".
	 */
	/*private void fine() {
		io.mostraMessaggio("Grazie di aver giocato!");  // si desidera smettere
	}*/

	public static void main(String[] argc) {
		IO io = new IOConsole();
		Labirinto labirinto = new LabirintoBuilder()
				.addStanzaIniziale("Atrio")
				.addAttrezzo("chiave", 3)
				.addStanzaVincente("Biblioteca")
				.addStanzaBloccata("Corridoio", "nord", "passepartout")
				.addAdiacenza("Atrio", "Corridoio", "nord")
				.addAdiacenza("Corridoio", "Atrio", "sud")
				.addAdiacenza("Corridoio", "Biblioteca", "nord")
				.addStanza("Aula n10")
				.addAttrezzo("torcia", 2)
				.addAdiacenza("Atrio", "Aula n10", "ovest")
				.addAdiacenza("Aula n10", "Atrio", "est")
				.addStanza("Sgabuzzino")
				.addAdiacenza("Atrio", "Sgabuzzino", "est")
				.addAdiacenza("Sgabuzzino", "Atrio", "ovest")
				.addStanzaBuia("Mensa", "torcia")
				.addAttrezzo("passepartout", 1)
				.addAdiacenza("Sgabuzzino", "Mensa", "sud")
				.addAdiacenza("Mensa", "Sgabuzzino", "nord")
				.getLabirinto();
		DiaDia gioco = new DiaDia(labirinto, io);
		gioco.gioca();
	}
}