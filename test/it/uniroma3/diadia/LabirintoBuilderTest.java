package it.uniroma3.diadia;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.*;
import it.uniroma3.diadia.ambienti.Labirinto.LabirintoBuilder;
import it.uniroma3.diadia.attrezzi.*;
import it.uniroma3.diadia.personaggi.*;

public class LabirintoBuilderTest {
	
	Labirinto.LabirintoBuilder lab;
	
	@BeforeEach
	public void setUp() throws Exception{
		lab = new LabirintoBuilder("labirinto.txt");
	}
	
	@Test
	public void testGetLabirinto() {
		assertNotNull(lab.getLabirinto());
		assertEquals(Labirinto.class, lab.getLabirinto().getClass());
	}
	
	@Test
	public void testAddStanza() {
		lab.addStanza("Ufficio");
		Stanza expected = new Stanza("Ufficio");
		assertEquals(expected, lab.getListaStanze().get("Ufficio"));
	}
	
	@Test
	public void testAddAttrezzoUltimaStanza() {
		lab.addStanzaIniziale("Hall").addAttrezzo("chiave inglese", 3);
		Attrezzo expected = new Attrezzo("chiave inglese", 3);
		assertEquals(expected, lab.getLabirinto().getStanzaIniziale().getAttrezzo("chiave inglese"));
	}
	
	@Test
	public void testAddAttrezzoSenzaUltimaStanza() {
		assertEquals(LabirintoBuilder.class, lab.addAttrezzo("chiave inglese", 2).getClass());
	}
	
	@Test
	public void testAddAttrezzoConStanzaAppenaAggiunta() {
		lab.addStanza("Aula Campus");
		lab.addAttrezzo("computer", 5);
		assertTrue(lab.getListaStanze().get("Aula Campus").hasAttrezzo("computer"));
	}
	
	@Test
	public void testAddAttrezzoStanzeAppenaAggiunteMultiple() {
		lab.addStanza("Aula Campus");
		lab.addStanza("DS1");
		lab.addAttrezzo("Laptop", 3);
		assertFalse(lab.getListaStanze().get("Aula Campus").hasAttrezzo("Laptop"));
		assertTrue(lab.getListaStanze().get("DS1").hasAttrezzo("Laptop"));
	}
	
	@Test
	public void testAddAttrezziMultipliStanzaAppenaAggiunta() {
		lab.addStanza("N14");
		lab.addAttrezzo("Lavagna", 5);
		lab.addAttrezzo("Gesso", 2);
		assertEquals(2, lab.getListaStanze().get("N14").getAttrezzi().size());
		assertTrue(lab.getListaStanze().get("N14").hasAttrezzo("Lavagna"));
		assertTrue(lab.getListaStanze().get("N14").hasAttrezzo("Gesso"));
		assertFalse(lab.getListaStanze().get("N14").hasAttrezzo("chiave"));
	}
	
	@Test
	public void testAddAttrezzoPrimoPrimaStanzaSecondoSeconda() {
		lab.addStanza("N13");
		lab.addAttrezzo("Penna", 1);
		lab.addStanza("N14");
		lab.addAttrezzo("Gomma", 2);
		assertTrue(lab.getListaStanze().get("N13").hasAttrezzo("Penna"));
		assertTrue(lab.getListaStanze().get("N14").hasAttrezzo("Gomma"));
	}
	
	@Test
	public void testGetStanzeInizialiEVincenti() {
		assertEquals("Atrio", lab.getLabirinto().getStanzaIniziale().getNome());
		assertEquals("Biblioteca", lab.getLabirinto().getStanzaVincente().getNome());
	}
	
	@Test
	public void testGetStanzeAdiacentiIniziale() {
		assertEquals(3, lab.getLabirinto().getStanzaIniziale().getMapStanzeAdiacenti().size());
		assertEquals("Corridoio", lab.getLabirinto().getStanzaIniziale().getStanzaAdiacente(Direzione.nord).getNome());
		assertEquals("N11", lab.getLabirinto().getStanzaIniziale().getStanzaAdiacente(Direzione.ovest).getNome());
		assertEquals("Sgabuzzino", lab.getLabirinto().getStanzaIniziale().getStanzaAdiacente(Direzione.est).getNome());
	}
	
	@Test
	public void testChangeStanzaIniziale() {
		lab.addStanzaIniziale("Portineria");
		assertEquals("Portineria", lab.getLabirinto().getStanzaIniziale().getNome());
	}
	
	@Test
	public void testAddStanzaMagica() {
		lab.addStanzaMagica("N1", 3);
		StanzaMagica stanzaMagica = (StanzaMagica)lab.getListaStanze().get("N1");
		assertTrue(stanzaMagica.isMagica());
	}
	
	@Test
	public void testAddAttrezziStanzaMagica() {
		lab.addStanzaMagica("N1", 1);
		lab.addAttrezzo("Foglio", 1);
		lab.addAttrezzo("matita", 2);
		assertEquals(new Attrezzo("Foglio", 1), lab.getListaStanze().get("N1").getAttrezzo("Foglio"));
		assertEquals(new Attrezzo("atitam", 4), lab.getListaStanze().get("N1").getAttrezzo("atitam"));
	}
	
	@Test
	public void testStanzaBloccata_SenzaChiave() {
		lab.addStanzaBloccata("stanza bloccata", "nord", "chiave");
		StanzaBloccata stanzaBloccata = new StanzaBloccata("stanza bloccata", "nord", "chiave");
		assertEquals(stanzaBloccata, lab.getListaStanze().get("stanza bloccata").getStanzaAdiacente(Direzione.nord));
	}
	
	@Test
	public void testStanzaBloccata_ConChiave() {
		lab.addStanzaBloccata("stanza bloccata", "nord", "chiave");
		lab.addAttrezzo("chiave", 1);
		lab.addStanza("N5");
		lab.addAdiacenza("stanza bloccata", "N5", "nord");
		assertEquals("N5", lab.getListaStanze().get("stanza bloccata").getStanzaAdiacente(Direzione.nord).getNome());
	}
	
	@Test
	public void testGetPersonaggioStanzaIniziale() {
		assertEquals("Carlo", lab.getLabirinto().getStanzaIniziale().getPersonaggio().getNome());
	}
	
	@Test
	public void testAddPersonaggioStanzaVincente() {
		AbstractPersonaggio s = new Strega("Circe", "muehehehe");
		lab.getLabirinto().getStanzaVincente().addPersonaggio(s);
		assertEquals(s, lab.getLabirinto().getStanzaVincente().getPersonaggio());
	}
	
	@Test
	public void testAddPersonaggioUltimaStanzaAggiunta() {
		lab.addStanza("N3");
		lab.addCane("fufi", "osso", 2);
		lab.addStanza("N4");
		lab.addMago("Harry", "pietra", 2);
		lab.addStanza("N6");
		lab.addStrega("Hermione");
		assertEquals("fufi", lab.getListaStanze().get("N3").getPersonaggio().getNome());
		assertEquals("Harry", lab.getListaStanze().get("N4").getPersonaggio().getNome());
		assertEquals("Hermione", lab.getListaStanze().get("N6").getPersonaggio().getNome());
	}
	
}
