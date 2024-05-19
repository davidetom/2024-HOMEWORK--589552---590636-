package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.*;

import it.uniroma3.diadia.attrezzi.*;

class LabirintoBuilderTest {
	private LabirintoBuilder labirintoBuilder;
	private String nomeStanzaIniziale = "Atrio";
	private String nomeStanzaVincente = "Uscita";
	
	@BeforeEach
	public void setUp() throws Exception{
		labirintoBuilder = new LabirintoBuilder();
	}
	
	@Test
	public void testMonoocale() {
		Labirinto monolocale = labirintoBuilder
				.addStanzaIniziale(nomeStanzaIniziale)
				.addStanzaVincente(nomeStanzaIniziale)
				.getLabirinto();
		assertEquals(nomeStanzaIniziale, monolocale.getStanzaVincente().getNome());
		assertEquals(nomeStanzaIniziale, monolocale.getStanzaIniziale().getNome());
	}
	
	@Test
	public void testMonolocaleConAttrezzo() {
		Labirinto monolocale = labirintoBuilder
				.addStanzaIniziale(nomeStanzaIniziale).addAttrezzo("spada", 1)
				.addStanzaVincente(nomeStanzaIniziale).addAttrezzo("spadina", 3)
				.getLabirinto();
		assertEquals(nomeStanzaIniziale, monolocale.getStanzaVincente().getNome());
		assertEquals(nomeStanzaIniziale, monolocale.getStanzaIniziale().getNome());
		assertEquals("spada", monolocale.getStanzaIniziale().getAttrezzo("spada").getNome());
		assertEquals("spadina", monolocale.getStanzaVincente().getAttrezzo("spadina").getNome());
	}
	
	//DEMO
	@Test
	public void testMonolocaleConAttrezzoSingoloDuplicato() {
		Labirinto monolocale = labirintoBuilder
				.addStanzaIniziale(nomeStanzaIniziale)
				.addAttrezzo("spada", 1)
				.addAttrezzo("spada", 1)
				.getLabirinto();
		
		int size = monolocale.getStanzaIniziale().getAttrezzi().size();
		assertTrue(size==1);
		assertEquals(new Attrezzo("spada" ,1), monolocale.getStanzaIniziale().getMapAttrezzi().get("spada"));
	}
	
	//DEMO
	@Test
	public void testBilocale() {
		Labirinto bilocale = labirintoBuilder
				.addStanzaIniziale(nomeStanzaIniziale)
				.addStanzaVincente(nomeStanzaVincente)
				.addAdiacenza(nomeStanzaIniziale, nomeStanzaVincente, "nord")
				.addAdiacenza(nomeStanzaVincente, nomeStanzaIniziale, "sud")
				.getLabirinto();
		assertEquals(bilocale.getStanzaVincente(), bilocale.getStanzaIniziale().getStanzaAdiacente("nord"));
		assertEquals(Collections.singleton("nord"), bilocale.getStanzaIniziale().getDirezioni());
		assertEquals(Collections.singleton("sud"), bilocale.getStanzaVincente().getDirezioni());
	}
	
	@Test
	public void testTrilocale() {
		Labirinto trilocale = labirintoBuilder
				.addStanzaIniziale(nomeStanzaIniziale).addAttrezzo("sedia", 1)
				.addStanza("biblioteca")
				.addAdiacenza(nomeStanzaIniziale, "biblioteca", "sud")
				.addAdiacenza("biblioteca", nomeStanzaIniziale, "nord")
				.addAttrezzo("libro antico", 5)
				.addStanzaVincente(nomeStanzaVincente)
				.addAdiacenza("biblioteca", nomeStanzaVincente, "est")
				.addAdiacenza(nomeStanzaVincente, "biblioteca", "ovest")
				.getLabirinto();
		assertEquals(nomeStanzaVincente, trilocale.getStanzaVincente().getNome());
		assertEquals(nomeStanzaIniziale, trilocale.getStanzaIniziale().getNome());
		assertEquals("biblioteca", trilocale.getStanzaIniziale().getStanzaAdiacente("sud").getNome());
	}
	
	@Test
	public void testTrilocaleConStanzaDuplicata() {
			labirintoBuilder
			.addStanzaIniziale(nomeStanzaIniziale)
			.addStanza("stanza generica")
			.addStanza("stanza generica")
			.addAdiacenza(nomeStanzaIniziale, "stanza generica", "nord")
			.getLabirinto();
		assertTrue(labirintoBuilder.getListaStanze().size()<=2);
	}
	
	@Test
	public void testPiuDiQuattroAdiacenti() {
		Labirinto maze = labirintoBuilder
				.addStanzaIniziale(nomeStanzaIniziale)
				.addStanza("stanza 1")
				.addStanza("stanza 2")
				.addStanza("stanza 3")
				.addStanza("stanza 4")
				.addStanza("stanza 5")
				.addAdiacenza(nomeStanzaIniziale, "stanza 1", "nord")
				.addAdiacenza(nomeStanzaIniziale, "stanza 2", "ovest")
				.addAdiacenza(nomeStanzaIniziale, "stanza 3", "sud")
				.addAdiacenza(nomeStanzaIniziale, "stanza 4", "est")
				.addAdiacenza(nomeStanzaIniziale, "stanza 5", "nord-est")
				.getLabirinto();
		Stanza test = new Stanza("stanza 5");
		assertNull(maze.getStanzaIniziale().getStanzaAdiacente("nord-est"));
		assertTrue(maze.getStanzaIniziale().getMapStanzeAdiacenti().size()<=4);
		assertTrue(!maze.getStanzaIniziale().getMapStanzeAdiacenti().containsValue(test));
		Map<String, Stanza> mappa = new HashMap<>();
		mappa.put("nord", new Stanza("stanza 1"));
		mappa.put("ovest", new Stanza("stanza 2"));
		mappa.put("sud", new Stanza("stanza 3"));
		mappa.put("est", new Stanza("stanza 4"));
		assertEquals(mappa, maze.getStanzaIniziale().getMapStanzeAdiacenti());
	}
	
	@Test
	public void testImpostaStanzaInizialeCambiandola() {
		Labirinto maze = labirintoBuilder
				.addStanzaIniziale(this.nomeStanzaIniziale)
				.addStanza("nuova iniziale")
				.addStanzaIniziale("nuova iniziale")
				.getLabirinto();
		assertEquals("nuova iniziale", maze.getStanzaIniziale().getNome());
	}
	
	@Test
	public void testAggiuntaAttrezziAStanze_Iniziale() {
		String nomeAttrezzo = "attrezzo";
		int peso = 1;
		Labirinto maze = labirintoBuilder
				.addStanzaIniziale(this.nomeStanzaIniziale)
				.addAttrezzo(nomeAttrezzo, peso)
				.getLabirinto();
		Attrezzo attrezzo = new Attrezzo(nomeAttrezzo, peso);
		assertEquals(attrezzo, maze.getStanzaIniziale().getAttrezzo(nomeAttrezzo));
	}
	
	@Test
	public void testAggiuntaAttrezziAStanze_AppenaAggiunte() {
		String nomeAttrezzo = "attrezzo";
		int peso = 1;
		String nomeStanza = "stanza 1";
		labirintoBuilder
			.addStanzaIniziale(nomeStanzaIniziale)
			.addStanza(nomeStanza)
			.addAttrezzo(nomeAttrezzo, peso)
			.getLabirinto();
		assertTrue(this.labirintoBuilder.getListaStanze().get(nomeStanza).getAttrezzi().contains(new Attrezzo(nomeAttrezzo, peso)));
		assertEquals(new Attrezzo(nomeAttrezzo, peso), this.labirintoBuilder.getListaStanze().get(nomeStanza).getAttrezzo(nomeAttrezzo));
	}
	//DEMO
	@Test
	public void testAggiuntaAttrezzoAStanze_AppenaAggiunteMultiple() {
		String nomeAttrezzo = "attrezzo";
		int peso = 1;
		String nomeStanza = "stanza 1";
		this.labirintoBuilder
			.addStanzaIniziale(nomeStanzaIniziale)
			.addStanza(nomeStanza)
			.addAttrezzo(nomeAttrezzo, peso)
			.getLabirinto();
		Attrezzo attrezzo = new Attrezzo(nomeAttrezzo, peso);
		assertEquals(attrezzo, labirintoBuilder.getListaStanze().get(nomeStanza).getMapAttrezzi().get("attrezzo"));
	}
	
	@Test
	public void testAggiuntaAttrezzoAStanze_MoltepliciAttrezziStessaStanza() {
		String nomeAttrezzo1 = "attrezzo 1";
		int peso1 = 1;
		String nomeAttrezzo2 = "attrezzo 2";
		int peso2 = 2;
		String nomeStanza1 = "Stanza 1";
		this.labirintoBuilder
			.addStanza(nomeStanza1)
			.addAttrezzo(nomeAttrezzo1, peso1)
			.addAttrezzo(nomeAttrezzo2, peso2);
		Map<String, Stanza> listaStanze = labirintoBuilder.getListaStanze();
		assertEquals(new Attrezzo(nomeAttrezzo1, peso1), listaStanze.get(nomeStanza1).getAttrezzo(nomeAttrezzo1));
		assertEquals(new Attrezzo(nomeAttrezzo2, peso2), listaStanze.get(nomeStanza1).getAttrezzo(nomeAttrezzo2));
	}
	
	@Test
	public void testAggiuntaAttrezzoAStanze_AttrezzoAggiuntoAllaSecondaStanza() {
		String nomeAttrezzo1 = "attrezzo 1";
		int peso1 = 1;
		String nomeAttrezzo2 = "attrezzo 2";
		int peso2 = 2;
		String nomeStanza1 = "Stanza 1";
		String nomeStanza2 = "Stanza 2";
		this.labirintoBuilder
			.addStanza(nomeStanza1)
			.addStanza(nomeStanza2)
			.addAttrezzo(nomeAttrezzo1, peso1)
			.addAttrezzo(nomeAttrezzo2, peso2);
		Map<String, Stanza> listaStanze = labirintoBuilder.getListaStanze();
		assertEquals(new Attrezzo(nomeAttrezzo1, peso1), listaStanze.get(nomeStanza2).getAttrezzo(nomeAttrezzo1));
		assertEquals(new Attrezzo(nomeAttrezzo2, peso2), listaStanze.get(nomeStanza2).getAttrezzo(nomeAttrezzo2));
	}
	
	@Test
	public void testAggiuntaAttrezzoAStanze_PrimoAttrezzoInUnaStanzaSecondoAttrezzoSecondaStanza() {
		String nomeAttrezzo1 = "attrezzo 1";
		int peso1 = 1;
		String nomeAttrezzo2 = "attrezzo 2";
		int peso2 = 2;
		String nomeStanza1 = "Stanza 1";
		String nomeStanza2 = "Stanza 2";
		this.labirintoBuilder
			.addStanza(nomeStanza1)
			.addAttrezzo(nomeAttrezzo1, peso1)
			.addStanza(nomeStanza2)
			.addAttrezzo(nomeAttrezzo2, peso2);
	Map<String, Stanza> listaStanze = labirintoBuilder.getListaStanze();
	assertEquals(new Attrezzo(nomeAttrezzo1, peso1), listaStanze.get(nomeStanza1).getAttrezzo(nomeAttrezzo1));
	assertEquals(new Attrezzo(nomeAttrezzo2, peso2), listaStanze.get(nomeStanza2).getAttrezzo(nomeAttrezzo2));
	}
	
	@Test
	public void testLabirintoConStanzaMagica() {
		int sogliaMagica = 1;
		String nomeStanzaMagica = "Stanza Magica";
		this.labirintoBuilder
			.addStanzaMagica(nomeStanzaMagica, sogliaMagica);
		StanzaMagica stanzaMagica = (StanzaMagica)labirintoBuilder.getListaStanze().get(nomeStanzaMagica);
		assertTrue(stanzaMagica.isMagica());
	}
	
	@Test
	public void testLabirintoConStanzaMagica_AggiuntaElementoOltreSogliaMagica() {
		String nomeAttrezzo1 = "attrezzo 1";
		String nomeAttrezzo2 = "attrezzo 2";
		String nomeAttrezzo2Inv = "2 ozzertta";
		int sogliaMagica = 1;
		int peso1 = 1;
		int peso2 = 2;
		int peso2x2 = peso2*2;
		String nomeStanzaMagica = "Stanza Magica";
		this.labirintoBuilder
			.addStanzaMagica(nomeStanzaMagica, sogliaMagica)
			.addAttrezzo(nomeAttrezzo1, peso1)
			.addAttrezzo(nomeAttrezzo2, peso2);
		Map<String, Stanza> listaStanze = labirintoBuilder.getListaStanze();
		assertEquals(new Attrezzo(nomeAttrezzo1, peso1), listaStanze.get(nomeStanzaMagica).getAttrezzo(nomeAttrezzo1));
		assertEquals(new Attrezzo(nomeAttrezzo2Inv, peso2x2), listaStanze.get(nomeStanzaMagica).getAttrezzo(nomeAttrezzo2Inv));
	}
	
	@Test
	public void testLabirintoConStanzaBloccata_SenzaPassepartout() {
		this.labirintoBuilder
			.addStanzaIniziale(nomeStanzaIniziale)
			.addStanzaBloccata("stanza bloccata", "nord", "chiave")
			.addAdiacenza(nomeStanzaIniziale, "stanza bloccata", "nord")
			.addAdiacenza("stanza bloccata", nomeStanzaIniziale, "sud")
			.addStanzaVincente(nomeStanzaVincente)
			.addAdiacenza("stanza bloccata", nomeStanzaVincente, "nord")
			.addAdiacenza(nomeStanzaVincente, "stanza bloccata", "sud");
		Stanza stanzaBloccata = new StanzaBloccata("stanza bloccata", "nord", "chiave");
		//Asserisce che in caso di mancanza di chiave, invoca il metodo getStanzaAdicente();la stanza bloccata ritorna se stessa
		assertEquals(stanzaBloccata, labirintoBuilder.getListaStanze().get("stanza bloccata").getStanzaAdiacente("nord"));
	}
	
	@Test
	public void testLabirintoConStanzaBloccata_ConPassepartout() {
		this.labirintoBuilder
			.addStanzaIniziale(nomeStanzaIniziale)
			.addStanzaBloccata("stanza bloccata", "nord", "chiave").addAttrezzo("chiave", 1)
			.addAdiacenza(nomeStanzaIniziale, "stanza bloccata", "nord")
			.addAdiacenza("stanza bloccata", nomeStanzaIniziale, "sud")
			.addStanzaVincente(nomeStanzaVincente)
			.addAdiacenza("stanza bloccata", nomeStanzaVincente, "nord")
			.addAdiacenza(nomeStanzaVincente, "stanza bloccata", "sud");
		Stanza stanzaVincente = new Stanza(nomeStanzaVincente);
		//Asserisce che in presenza di passepartout, invocato il metodo getStanzaAdiacente(), la stanza bloccata ritorna la corretta adiacente
		assertEquals(stanzaVincente, labirintoBuilder.getListaStanze().get("stanza bloccata").getStanzaAdiacente("nord"));
	}
	
	//DEMO
	@Test
	public void testLabirintoCompletoConTutteLeStanze() {
		Labirinto completo = this.labirintoBuilder
				.addStanzaIniziale(nomeStanzaIniziale)
				.addStanzaVincente(nomeStanzaVincente)
				.addStanza("corridoio")
				.addAttrezzo("chiave", 1)
				.addAttrezzo("lanterna", 1)
				.addStanzaBloccata("corridoio bloccato", "nord", "chiave")
				.addStanzaMagica("stanza magica", 1)
				.addStanzaBuia("stanza buia", "lanterna")
				.addStanza("Aula 1")
				.addAdiacenza(nomeStanzaIniziale, "corridoio", "nord")
				.addAdiacenza("corridoio", nomeStanzaIniziale, "sud")
				.addAdiacenza("corridoio", "corridoio bloccato", "nord")
				.addAdiacenza("corridoio bloccato", "corridoio", "sud")
				.addAdiacenza("corridoio bloccato", "Aula 1", "nord")
				.addAdiacenza("Aula 1", "corridoio bloccato", "sud")
				.addAdiacenza("Aula 1", nomeStanzaVincente, "nord")
				.addAdiacenza(nomeStanzaVincente, "Aula 1", "sud")
				.addAdiacenza("corridoio", "stanza magica", "est")
				.addAdiacenza("stanza magica", "corridoio", "ovest")
				.addAdiacenza("corridoio", "stanza buia", "ovest")
				.addAdiacenza("stanza buia", "corridoio", "est")
				.getLabirinto();
		assertEquals(nomeStanzaIniziale, completo.getStanzaIniziale().getNome());
		assertEquals(nomeStanzaVincente, completo.getStanzaVincente().getNome());
		Stanza corridoio = completo.getStanzaIniziale().getStanzaAdiacente("nord");
		assertEquals("corridoio", corridoio.getNome());
		assertTrue(corridoio.getDirezioni().containsAll(Arrays.asList("nord", "sud", "est", "ovest")));
		/*Map<String, Stanza> mapAdiacenti = new HashMap<>();
		mapAdiacenti.put("nord", new Stanza("corridoio bloccato"));
		mapAdiacenti.put("sud", new Stanza(nomeStanzaIniziale));
		mapAdiacenti.put("est", new Stanza("stanza magica"));
		mapAdiacenti.put("ovest", new Stanza("stanza buia"));
		assertEquals(mapAdiacenti, corridoio.getMapStanzeAdiacenti());*/
		assertEquals(new Attrezzo("chiave", 1), corridoio.getMapAttrezzi().get("chiave"));
		assertEquals(new Attrezzo("lanterna", 1), corridoio.getMapAttrezzi().get("lanterna"));
		
	}
	
	
	
	
	
	
	
	
	
}
