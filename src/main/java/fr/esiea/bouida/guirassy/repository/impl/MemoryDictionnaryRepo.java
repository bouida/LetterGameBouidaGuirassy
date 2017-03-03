package fr.esiea.bouida.guirassy.repository.impl;

import java.io.InputStream;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import fr.esiea.bouida.guirassy.repository.DictionnaryRepo;

/**
 * Implementation en mémoire du dictionnaire. 
 * Le fichier du dictionnaire doit être dans le classpath (géré avec Maven)
 * 
 *  Cette classe est un singleton.
 *
 */
public class MemoryDictionnaryRepo implements DictionnaryRepo {
	
	private static final String DICTIONNARY_FILE_NAME = "dico.txt";
	
	/**
	 * Unique instance
	 */
	private static MemoryDictionnaryRepo instance;
	
	private Set<String> words;
	
	private MemoryDictionnaryRepo() {
		super();
		words = new HashSet<>();
		init();
	}
	
	/**
	 * Lecture du fichier dictionnaire
	 */
	private void init() {
		InputStream dicoFileStream = getClass().getClassLoader().getResourceAsStream(DICTIONNARY_FILE_NAME);
		Scanner scanner = null;
		try {
			scanner = new Scanner(dicoFileStream);
		} catch (Exception e) {
			throw new RuntimeException("Impossible de lire le fichier " + DICTIONNARY_FILE_NAME + " pour initialiser le dictionnaire", e);
		}
		
		while(scanner.hasNext()) {
			String currentDicoWords = scanner.next();
			this.words.add(currentDicoWords);
		}
		
		scanner.close();
	}
	
	public static MemoryDictionnaryRepo getInstance() {
		if (instance == null) {
			instance = new MemoryDictionnaryRepo();
		}
		
		return instance;
	}
	
	@Override
	public boolean contient(String motAValider) {
		if (motAValider == null)
			return false;
		
		return this.words.contains(motAValider.trim().toLowerCase());
	}
}
