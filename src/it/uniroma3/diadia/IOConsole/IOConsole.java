package it.uniroma3.diadia.IOConsole;

import java.util.Scanner;

public class IOConsole implements IO {
	
	private Scanner scannerDiLinee;
	
	public IOConsole(Scanner scannerDiLinee) {
		this.scannerDiLinee = scannerDiLinee;
	}
	@Override
	public void mostraMessaggio(String msg) {
		System.out.println(msg);
	}
	
	@Override
	public String leggiRiga() {
		String riga = this.scannerDiLinee.nextLine();
		return riga;
	}
}
