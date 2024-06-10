package it.uniroma3.diadia.ambienti;
//NEW
public enum Direzione {
	nord, sud, est, ovest;
	
	public static Direzione stringaToDirezione(String direzione) {
		switch(direzione.toLowerCase()) {
			case "nord":
				return nord;
			case "sud":
				return sud;
			case "est":
				return est;
			case "ovest":
				return ovest;
			default:
				throw new IllegalArgumentException("Direzione non valida: "+direzione);
		}
	}
}
