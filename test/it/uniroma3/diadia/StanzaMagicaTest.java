package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

import it.uniroma3.diadia.ambienti.*;

class StanzaMagicaTest {
	private StanzaMagica stanzaMagicaSenzaAttrezzi;
	private StanzaMagica stanzaMagica1Attrezzo;
	private StanzaMagica stanzaMagica2Attrezzi;
	private StanzaMagica stanzaMagica3Attrezzi;
	private Attrezzo chiave;
	private Attrezzo cacciavite;
	private Attrezzo bottiglia;
	private Attrezzo orologio;
	
	@BeforeEach
	void setUp() {
		this.stanzaMagicaSenzaAttrezzi = new StanzaMagica("stanza non magica");
		this.stanzaMagica1Attrezzo = new StanzaMagica("stanza magica 1 attrezzo");
		this.stanzaMagica2Attrezzi = new StanzaMagica("stanza magica 2 attrezzi");
		this.stanzaMagica3Attrezzi = new StanzaMagica("stanza magica 3 attrezzi");
		this.chiave = new Attrezzo("chiave", 2);
		this.cacciavite = new Attrezzo("cacciavite", 3);
		this.bottiglia = new Attrezzo("bottiglia", 3);
		this.orologio = new Attrezzo("orologio", 5);
		
		stanzaMagica1Attrezzo.addAttrezzo(chiave);
		stanzaMagica2Attrezzi.addAttrezzo(chiave);
		stanzaMagica2Attrezzi.addAttrezzo(cacciavite);
		stanzaMagica3Attrezzi.addAttrezzo(chiave);
		stanzaMagica3Attrezzi.addAttrezzo(cacciavite);
		stanzaMagica3Attrezzi.addAttrezzo(bottiglia);
	}
	
	@Test
	public void testAddAttrezzo_StanzaMagicaVuota() {
		this.stanzaMagicaSenzaAttrezzi.addAttrezzo(chiave);
		assertEquals(chiave, stanzaMagicaSenzaAttrezzi.getAttrezzo("chiave"));
		assertEquals(2, stanzaMagicaSenzaAttrezzi.getAttrezzo("chiave").getPeso());
	}
	
	@Test
	public void testAddAttrezzo_StanzaMagica1Attrezzo() {
		this.stanzaMagica1Attrezzo.addAttrezzo(cacciavite);
		assertEquals(cacciavite, stanzaMagica1Attrezzo.getAttrezzo("cacciavite"));
		assertEquals(3, stanzaMagica1Attrezzo.getAttrezzo("cacciavite").getPeso());
	}
	
	@Test
	public void testAddAttrezzo_StanzaMagica2Attrezzi() {
		this.stanzaMagica2Attrezzi.addAttrezzo(bottiglia);
		assertEquals(bottiglia, stanzaMagica2Attrezzi.getAttrezzo("bottiglia"));
		assertEquals(3, stanzaMagica2Attrezzi.getAttrezzo("bottiglia").getPeso());
	}
	
	@Test
	public void testAddAttrezzo_StanzaMagica3Attrezzi() {
		this.stanzaMagica3Attrezzi.addAttrezzo(orologio);
		assertEquals("oigoloro", stanzaMagica3Attrezzi.getAttrezzo("oigoloro").getNome());
		assertEquals(10, stanzaMagica3Attrezzi.getAttrezzo("oigoloro").getPeso());
	}
	
	
	
	
}
