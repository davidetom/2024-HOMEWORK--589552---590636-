package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import it.uniroma3.diadia.ambienti.*;

class AbstractComandoTest {
	FakeComando fake;
	Partita p;
	
	@BeforeEach
	public void setUp() throws Exception{
		p = new Partita(Labirinto.newBuilder("labirinto.txt").getLabirinto());
		fake = new FakeComando();
	}
	
	@Test
	public void testGetNomeComandoFake() {
		assertEquals("Comando Fake", fake.getNome());
		assertNotEquals("Comando Astratto", fake.getNome());
	}
	
	@Test
	public void testEseguiComandoFake() {
		fake.esegui(p);
		assertTrue(p.isFinita());
	}
	
	@Test
	public void testSetParametroComandoFake() {
		fake.setParametro("sud-est");
		assertEquals("sud-est", fake.getParametro());
	}
}
