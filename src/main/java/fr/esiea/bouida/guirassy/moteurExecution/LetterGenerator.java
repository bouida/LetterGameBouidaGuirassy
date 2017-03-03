package fr.esiea.bouida.guirassy.moteurExecution;

import java.util.Random;

/**
 * Genere des lettres de manière aléatoire
 * 
 * Cette classe est un singleton
 * 
 *
 */
public class LetterGenerator {
	
	/**
	 * Unique instance
	 */
	private static LetterGenerator instance;
	
	/**
	 * Nombre maximum de lettre dans l'alphabet
	 */
	private static final int NB_LETTER_ALPHABET = 26;
	
	private Random rdn;
	
	/**
	 * Constructeur privé
	 */
	private LetterGenerator() {
		super();
		rdn = new Random();
	}
	
	/**
	 * Retourne l'unique instance
	 * 
	 * @return
	 */
	public static LetterGenerator getInstance() {
		if (instance == null) {
			instance = new LetterGenerator();
		}
		
		return instance;
	}
	
	/**
	 * Génère une lettre au hasard
	 * 
	 * @return
	 */
	public Character generateLetter() {
		return (char)('a' + rdn.nextInt(NB_LETTER_ALPHABET));
	}
}

