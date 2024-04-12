package it.uniroma3.diadia;

/**
 * Questa classe modella un comando.
 * Un comando consiste al piu' di due parole:
 * il nome del comando ed un parametro
 * su cui si applica il comando.
 * (Ad es. alla riga digitata dall'utente "vai nord"
 *  corrisponde un comando di nome "vai" e parametro "nord").
 *
 * @author  docente di POO
 * @version base
 */

public class Comando {

    private String nome;
    private String parametro;

    public Comando(String istruzione) {
    	
    	String[] parti = istruzione.split(" ");
    	
    	/*prima parola: nome del comando*/
    	if(parti.length > 0)
    		this.nome = parti[0];
    	else
    		this.nome = null;
    	
    	/*seconda parola: eventuale parametro*/
    	if(parti.length > 1)
    		this.parametro = parti[1];
    }


    public String getNome() {
        return this.nome;
    }

    public String getParametro() {
        return this.parametro;
    }

    public boolean sconosciuto() {
        return (this.nome == null);
    }
}