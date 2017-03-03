package fr.esiea.bouida.guirassy.repository;

/**
 * 
 * Interface representant le dictionnaire
 * Est utilisé pour vérifier qu'un mot est valide.
 * 
 */
public interface DictionnaryRepo {
	
	/**
	 * Retourne true si le mot en parametre existe bien.
	 * 
	 * @param motAValider
	 * @return
	 */
	public boolean contient(String motAValider);

}
