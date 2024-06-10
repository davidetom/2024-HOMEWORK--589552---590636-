package it.uniroma3.diadia;

import java.io.*;
import it.uniroma3.diadia.personaggi.*;
import java.util.*;

import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.ambienti.StanzaBloccata;
import it.uniroma3.diadia.ambienti.StanzaBuia;
import it.uniroma3.diadia.ambienti.StanzaMagica;
import it.uniroma3.diadia.ambienti.Direzione;
import it.uniroma3.diadia.attrezzi.*;

public class CaricatoreLabirinto {
	/* prefisso di una singola riga di testo contenente tutti i nomi delle stanze */
	private static final String STANZE_MARKER = "Stanze:";             

	/* prefisso di una singola riga contenente il nome della stanza iniziale */
	private static final String STANZA_INIZIALE_MARKER = "Inizio:";    

	/* prefisso della riga contenente il nome stanza vincente */
	private static final String STANZA_VINCENTE_MARKER = "Vincente:";  

	/* prefisso della riga contenente il nome stanza magica */
	private static final String STANZA_MAGICA_MARKER = "Magica:"; 
	
	/* prefisso della riga contenente il nome stanza buia */
	private static final String STANZA_BUIA_MARKER = "Buia:"; 
	
	/* prefisso della riga contenente il nome stanza bloccata */
	private static final String STANZA_BLOCCATA_MARKER = "Bloccata:"; 
	
	/* prefisso della riga contenente le specifiche degli attrezzi da collocare nel formato <nomeAttrezzo> <peso> <nomeStanza> */
	private static final String ATTREZZI_MARKER = "Attrezzi:";
	
	/*prefisso della riga contenente le specifiche dei personaggi da collocare nel formato <nomePersonaggio> <presentazione>*/
	private static final String PERSONAGGI_CANE_MAKER = "Cane:";
	
	/*prefisso della riga contenente le specifiche dei personaggi da collocare nel formato <nomePersonaggio> <presentazione>*/
	private static final String PERSONAGGI_MAGO_MAKER = "Mago:";
	
	/*prefisso della riga contenente le specifiche dei personaggi da collocare nel formato <nomePersonaggio> <presentazione>*/
	private static final String PERSONAGGI_STREGA_MAKER = "Strega:";

	/* prefisso della riga contenente le specifiche dei collegamenti tra stanza nel formato <nomeStanzaDa> <direzione> <nomeStanzaA> */
	private static final String USCITE_MARKER = "Uscite:";

	/*
	 *  Esempio di un possibile file di specifica di un labirinto (vedi POO-26-eccezioni-file.pdf)

		Stanze: biblioteca, N10, N11
		Inizio: N10
		Vincente: N11
		Attrezzi: martello 10 biblioteca, pinza 2 N10
		Uscite: biblioteca nord N10, biblioteca sud N11

	 */
	private BufferedReader reader;

	private Map<String, Stanza> nome2stanza;

	private Stanza stanzaIniziale;
	private Stanza stanzaVincente;


	public CaricatoreLabirinto(String nomeFile) throws FileNotFoundException {
		this.nome2stanza = new HashMap<String,Stanza>();
		this.reader = new LineNumberReader(new FileReader(nomeFile));
	}
	
	/*Costruttore per il test*/
	public CaricatoreLabirinto(StringReader reader) throws FileNotFoundException {
		this.nome2stanza = new HashMap<String,Stanza>();
		this.reader = new LineNumberReader(reader);
	}

	public void carica() throws FormatoFileNonValidoException {
		try {
			this.leggiECreaStanze();
			this.leggiECreaStanzeMagiche();
			this.leggiECreaStanzeBuie();
			this.leggiECreaStanzeBloccate();
			this.leggiInizialeEvincente();
			this.leggiECreaMaghi();
			this.leggiECreaCani();
			this.leggiECreaStreghe();
			this.leggiECollocaAttrezzi();
			this.leggiEImpostaUscite();
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}

	}

	private String leggiRigaCheCominciaPer(String marker) throws FormatoFileNonValidoException {
		try {
			String riga = this.reader.readLine();
			check(riga.startsWith(marker),"era attesa una riga che cominciasse per "+marker);
			return riga.substring(marker.length());
		} catch (IOException e) {
			throw new FormatoFileNonValidoException(e.getMessage());
		}
	}

	private void leggiECreaStanze() throws FormatoFileNonValidoException  {
		String nomiStanze = this.leggiRigaCheCominciaPer(STANZE_MARKER);
		for(String nomeStanza : separaStringheAlleVirgole(nomiStanze)) {
			Stanza stanza = new Stanza(nomeStanza);
			this.nome2stanza.put(nomeStanza, stanza);
		}
	}
	
	private void leggiECreaStanzeMagiche() throws FormatoFileNonValidoException  {
		String nomiStanze = this.leggiRigaCheCominciaPer(STANZA_MAGICA_MARKER);
		for(String nomeStanza : separaStringheAlleVirgole(nomiStanze)) {
			Stanza stanza = new StanzaMagica(nomeStanza);
			this.nome2stanza.put(nomeStanza, stanza);
		}
	}
	
	private void leggiECreaStanzeBloccate() throws FormatoFileNonValidoException  {
		String nomiStanzaBloccata = this.leggiRigaCheCominciaPer(STANZA_BLOCCATA_MARKER);
		for(String descrizione : separaStringheAlleVirgole(nomiStanzaBloccata)) {
			
			try(Scanner scannerDiLinea = new Scanner(descrizione)){
				while(scannerDiLinea.hasNext()) {
					check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("la stanza"+descrizione+"non esiste!\n"));
					String nomeStanza = scannerDiLinea.next();
					check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("la direzione della stanza"+descrizione+"non esiste!\n"));
					Direzione direzione = Direzione.valueOf(scannerDiLinea.next());
					check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("c'è stato un problema nella creazione dell'attrezzo sbloccante della stanza..."));
					String attrezzoUnlock = scannerDiLinea.next();
					
					Stanza stanzaBloccata = new StanzaBloccata(nomeStanza, direzione, attrezzoUnlock);
					this.nome2stanza.put(nomeStanza, stanzaBloccata);
					
				}
			}
		}
	}
	
	private void leggiECreaStanzeBuie() throws FormatoFileNonValidoException{
		String nomiStanzaBuia = leggiRigaCheCominciaPer(STANZA_BUIA_MARKER);
		for(String descrizione : separaStringheAlleVirgole(nomiStanzaBuia)) {
			try(Scanner scannerDiLinea = new Scanner(descrizione)){
				while(scannerDiLinea.hasNext()) {
					check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("la stanza"+descrizione+"non esiste!\n"));
					String nomeStanza = scannerDiLinea.next();
					check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("c'è stato un problema nella creazione dell'attrezzo per illuminare la stanza buia..."));
					String attrezzoPerLuce = scannerDiLinea.next();
					Stanza stanzaBuia = new StanzaBuia(nomeStanza, attrezzoPerLuce);
					this.nome2stanza.put(nomeStanza, stanzaBuia);
				}
			}
		}
	}
	
	private void leggiECreaCani() throws FormatoFileNonValidoException{
		String specifichePersonaggi = this.leggiRigaCheCominciaPer(PERSONAGGI_CANE_MAKER);
		for(String descrizione : separaStringheAlleVirgole(specifichePersonaggi)) {
			try(Scanner scannerDiLinea = new Scanner(descrizione)){
				while(scannerDiLinea.hasNext()) {
					check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("la stanza"+descrizione+"per aggiungere il personaggio Cane non esiste!\n"));
					String nomeStanza = scannerDiLinea.next();
					check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("c'è stato un problema nella creazione del personaggio Cane..."));
					String nomeCane = scannerDiLinea.next();
					check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("devi specificare la presentazione del Cane!\n "));
					String presentazione = scannerDiLinea.next();
					check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("c'è stato un problema nella creazione dell'attrezzo da assegnare al cane..."));
					String nomeAttrezzo = scannerDiLinea.next();
					check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("devi specificare il peso dell'attrezzo!!\n"));
					String pesoAttrezzo = scannerDiLinea.next();
					AbstractPersonaggio personaggio = new Cane(nomeCane, presentazione, nomeAttrezzo, pesoAttrezzo);
					this.nome2stanza.get(nomeStanza).addPersonaggio(personaggio);
				}
			}
		}
	}
	
	private void leggiECreaMaghi() throws FormatoFileNonValidoException{
		String specifichePersonaggio = leggiRigaCheCominciaPer(PERSONAGGI_MAGO_MAKER);
		for(String descrizione : separaStringheAlleVirgole(specifichePersonaggio)) {
			try(Scanner scannerDiLinea = new Scanner(descrizione)){
				while(scannerDiLinea.hasNext()) {
					check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("la stanza"+descrizione+"per aggiungere il personaggio Mago non esiste!\n"));
					String nomeStanza = scannerDiLinea.next();
					check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("c'è stato un problema nella creazione del personaggio Mago..."));
					String nomeMago = scannerDiLinea.next();
					check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("devi specificare la presentazione del Mago!\n"));
					String presentazione = scannerDiLinea.next();
					check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("c'è stato un problema nella creazione dell'attrezzo da assegnare al Mago..."));
					String nomeAttrezzo = scannerDiLinea.next();
					check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("devi specificare il peso dell'attrezzo!!\n"));
					String pesoAttrezzo = scannerDiLinea.next();
					AbstractPersonaggio personaggio = new Mago(nomeMago, presentazione, nomeAttrezzo, pesoAttrezzo);
					this.nome2stanza.get(nomeStanza).addPersonaggio(personaggio);
					
				}
			}
		}
	}
	
	private void leggiECreaStreghe() throws FormatoFileNonValidoException{
		String specifichePersonaggio = leggiRigaCheCominciaPer(PERSONAGGI_STREGA_MAKER);
		for(String descrizione : separaStringheAlleVirgole(specifichePersonaggio)) {
			try(Scanner scannerDiLinea = new Scanner(descrizione)){
				while(scannerDiLinea.hasNext()) {
					check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("la stanza"+descrizione+"per aggiungere il personaggio Strega non esiste!\n"));
					String nomeStanza = scannerDiLinea.next();
					check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("c'è stato un problema nella creazione del personaggio Strega..."));
					String nomeStrega = scannerDiLinea.next();
					check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("devi specificare la presentazione della Strega!\n"));
					String presentazione = scannerDiLinea.next();
					AbstractPersonaggio personaggio = new Strega(nomeStrega, presentazione);
					this.nome2stanza.get(nomeStanza).addPersonaggio(personaggio);
				}
			}
		}
	}

	private List<String> separaStringheAlleVirgole(String string) {
		List<String> result = new LinkedList<>();
		Scanner scanner = new Scanner(string);
		scanner.useDelimiter(",");
		try (Scanner scannerDiParole = scanner) {
			while(scannerDiParole.hasNext()) {
				result.add(scannerDiParole.next());
			}
		}
		return result;
	}


	private void leggiInizialeEvincente() throws FormatoFileNonValidoException {
		String nomeStanzaIniziale = null;
		nomeStanzaIniziale = this.leggiRigaCheCominciaPer(STANZA_INIZIALE_MARKER);
		check(this.isStanzaValida(nomeStanzaIniziale), nomeStanzaIniziale +" non definita");
		String nomeStanzaVincente = this.leggiRigaCheCominciaPer(STANZA_VINCENTE_MARKER);
		check(this.isStanzaValida(nomeStanzaVincente), nomeStanzaVincente + " non definita");
		this.stanzaIniziale = this.nome2stanza.get(nomeStanzaIniziale);
		this.stanzaVincente = this.nome2stanza.get(nomeStanzaVincente);
	}

	private void leggiECollocaAttrezzi() throws FormatoFileNonValidoException {
		String specificheAttrezzi = this.leggiRigaCheCominciaPer(ATTREZZI_MARKER);

		for(String specificaAttrezzo : separaStringheAlleVirgole(specificheAttrezzi)) {
			String nomeAttrezzo = null;
			String pesoAttrezzo = null;
			String nomeStanza = null; 
			try (Scanner scannerLinea = new Scanner(specificaAttrezzo)) {
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome di un attrezzo."));
				nomeAttrezzo = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il peso dell'attrezzo "+nomeAttrezzo+"."));
				pesoAttrezzo = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome della stanza in cui collocare l'attrezzo "+nomeAttrezzo+"."));
				nomeStanza = scannerLinea.next();
			}				
			posaAttrezzo(nomeAttrezzo, pesoAttrezzo, nomeStanza);
		}
	}

	private void posaAttrezzo(String nomeAttrezzo, String pesoAttrezzo, String nomeStanza) throws FormatoFileNonValidoException {
		int peso;
		try {
			peso = Integer.parseInt(pesoAttrezzo);
			Attrezzo attrezzo = new Attrezzo(nomeAttrezzo, peso);
			check(isStanzaValida(nomeStanza),"Attrezzo "+ nomeAttrezzo+" non collocabile: stanza " +nomeStanza+" inesistente");
			this.nome2stanza.get(nomeStanza).addAttrezzo(attrezzo);
		}
		catch (NumberFormatException e) {
			check(false, "Peso attrezzo "+nomeAttrezzo+" non valido");
		}
	}


	private boolean isStanzaValida(String nomeStanza) {
		return this.nome2stanza.containsKey(nomeStanza);
	}

	private void leggiEImpostaUscite() throws FormatoFileNonValidoException {
		String specificheUscite = this.leggiRigaCheCominciaPer(USCITE_MARKER);
		for(String specifiche : separaStringheAlleVirgole(specificheUscite)) {
			try (Scanner scannerDiLinea = new Scanner(specifiche)) {			
				while (scannerDiLinea.hasNext()) {
					check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("le uscite di una stanza."));
					String stanzaPartenza = scannerDiLinea.next();
					check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("la direzione di una uscita della stanza "+stanzaPartenza));
					String dir = scannerDiLinea.next();
					check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("la destinazione di una uscita della stanza "+stanzaPartenza+" nella direzione "+dir));
					String stanzaDestinazione = scannerDiLinea.next();

					impostaUscita(stanzaPartenza, dir, stanzaDestinazione);
				}
			}
		}
	}
	
	private String msgTerminazionePrecoce(String msg) {
		return "Terminazione precoce del file prima di leggere "+msg;
	}

	private void impostaUscita(String stanzaDa, String dir, String nomeA) throws FormatoFileNonValidoException {
		check(isStanzaValida(stanzaDa),"Stanza di partenza sconosciuta "+dir);
		check(isStanzaValida(nomeA),"Stanza di destinazione sconosciuta "+ dir);
		Stanza partenzaDa = this.nome2stanza.get(stanzaDa);
		Stanza arrivoA = this.nome2stanza.get(nomeA);
		partenzaDa.impostaStanzaAdiacente(Direzione.valueOf(dir), arrivoA);
	}


	final private void check(boolean condizioneCheDeveEsseraVera, String messaggioErrore) throws FormatoFileNonValidoException {
		if (!condizioneCheDeveEsseraVera)
			throw new FormatoFileNonValidoException("Formato file non valido [" + ((LineNumberReader)this.reader).getLineNumber() + "] "+messaggioErrore);		
	}

	public Stanza getStanzaIniziale() {
		return this.stanzaIniziale;
	}

	public Stanza getStanzaVincente() {
		return this.stanzaVincente;
	}
}
