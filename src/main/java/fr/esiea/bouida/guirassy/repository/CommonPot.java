package fr.esiea.bouida.guirassy.repository;


/**
 * 
 * Interface représentant le Pot Commun
 * Est utilisé pour contenir des lettres utilisable pour des mots
 *
 */
public interface CommonPot {

	void ajoutLettre(char a);
	
	/**
	 * Vérifie que le mot en paramètre est composé de lettres du pot
	 * 
	 * @param mot
	 * @return
	 */
	boolean estComposeDeLettresDuPot(String mot);
	
}
