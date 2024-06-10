package it.uniroma3.diadia;

import it.uniroma3.diadia.ambienti.*;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import it.uniroma3.diadia.attrezzi.*;
import java.io.*;
class CaricatoreLabirintoTest {
	private final String monolocale =
			"Stanze:atrio\n"+
			"Magica:\n"+
			"Buia:\n"+
			"Bloccata:\n"+
			"Inizio:atrio\n"+
			"Vincente:atrio\n"+
			"Mago:\n"+
			"Cane: atrio fifo baubau osso 2\n"+
			"Strega:\n"+
			"Attrezzi:\n"+
			"Uscite:\n";
	
	private final String bilocale = 
			"Stanze:biblioteca,N11\n"+
			"Magica:\n"+
			"Buia:\n"+
			"Bloccata:\n"+
			"Inizio:N11\n"+
			"Vincente:biblioteca\n"+
			"Mago:\n"+
			"Cane:\n"+
			"Strega:\n"+
			"Attrezzi:osso 2 N11\n"+
			"Uscite:N11 nord biblioteca\n";
	
	private final String trilocale =
			"Stanze:biblioteca,N11,N12\n"+
			"Magica:\n"+
			"Buia:\n"+
			"Bloccata:\n"+
			"Inizio:N11\n"+
			"Vincente:biblioteca\n"+
			"Mago:N12 merlino magicabula bacchetta 2\n"+
			"Cane:N11 fifo baubau osso 2\n"+
			"Strega:biblioteca befana mueheheh\n"+
			"Attrezzi:osso 2 N11\n"+
			"Uscite:N11 nord biblioteca, biblioteca sud N11, N11 est N12, N12 ovest N11\n";
	
	CaricatoreLabirinto caricatore;
	private Attrezzo expected;
	
	@BeforeEach
	public void setUp() throws Exception{
		this.expected = new Attrezzo("osso", 2);
	}
	
	@Test
	public void testCaricatoreLabirintoMonolocaleConCane() throws FormatoFileNonValidoException, FileNotFoundException{
		caricatore = new CaricatoreLabirinto(new StringReader(monolocale));
		caricatore.carica();
		assertEquals("atrio", this.caricatore.getStanzaVincente().getNome());
		assertEquals("atrio", this.caricatore.getStanzaIniziale().getNome());
		assertEquals("fifo", this.caricatore.getStanzaIniziale().getPersonaggio().getNome());
	}
	
	@Test
	public void testCaricatoreLabirintoBilocaleConAttrezzi() throws FormatoFileNonValidoException, FileNotFoundException{
		caricatore = new CaricatoreLabirinto(new StringReader(bilocale));
		caricatore.carica();
		assertEquals("N11", this.caricatore.getStanzaIniziale().getNome());
		assertEquals("biblioteca", this.caricatore.getStanzaVincente().getNome());
		assertEquals(expected, this.caricatore.getStanzaIniziale().getAttrezzo("osso"));
	}
	
	@Test
	public void testCaricatoreLabirintoTrilocaleConTuttiPersonaggi() throws FormatoFileNonValidoException, FileNotFoundException {
		caricatore = new CaricatoreLabirinto(new StringReader(trilocale));
		caricatore.carica();
		assertEquals("fifo", this.caricatore.getStanzaIniziale().getPersonaggio().getNome());
		assertEquals("befana", this.caricatore.getStanzaVincente().getPersonaggio().getNome());
		assertEquals("merlino", this.caricatore.getStanzaIniziale().getStanzaAdiacente(Direzione.est).getPersonaggio().getNome());
		assertEquals("N11", this.caricatore.getStanzaVincente().getStanzaAdiacente(Direzione.sud).getNome());
	}
	
}
