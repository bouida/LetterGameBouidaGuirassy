package fr.esiea.bouida.guirassy.core;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

import fr.esiea.bouida.guirassy.repository.DictionnaryRepo;

/**
 * Prend les mots d'un joueur adverse 
 * lorsque le joueur a trouvé un nouveau mot. 
 *
 */
public class WordTaker implements Observer {
	
	private Player joueur1;
	private Player joueur2;
	private DictionnaryRepo dico;
	
	/**
	 * Les deux joueurs de la partie
	 * 
	 * @param joueur1
	 * @param joueur2
	 */
	public WordTaker(Player joueur1, Player joueur2, DictionnaryRepo dico) {
		super();
		this.joueur1 = joueur1;
		this.joueur2 = joueur2;
		this.dico = dico;
		
		joueur1.addObserver(this);
		joueur2.addObserver(this);
	}


	@Override
	public void update(Observable o, Object arg) {
		if (arg == null || !(arg instanceof String)) {
			System.out.println("PAS de mot au Preneur de mot lors de la notification");
			return;
		}
		
		String nouveauMot = (String) arg;
		
		if (o == joueur1) {
			// Le joueur 1 a trouvé un mot
			prendreMot(nouveauMot, joueur2);
		} else if (o == joueur2) {
			// Le joueur 2 a trouvé un mot
			prendreMot(nouveauMot, joueur1);
		} else {
			System.out.println("Erreur de notification : Le joueur (ou l'objet) " + o + " n'est pas géré par l'observeur PreneurDeMot");
		}
	}
	
	/**
	 * Enlève le mot motAPrendre au joueur j si les conditions sont vérifiées :
	 * - L'un des mots de joueur j commence par motAPrendre
	 * - Les autres caractères sont soit une lettre ou un autre mot valide
	 * 
	 * @param motAPrendre
	 * @param j
	 */
	private void prendreMot(String motAPrendre, Player j) {
		List<String> motsJoueurs = j.getMotsValidseTrouves();
		
		if (motsJoueurs == null || motsJoueurs.isEmpty()) {
			return;
		}
		
		for (String mot : motsJoueurs) {
			int indexOfMotAPrendre = mot.indexOf(motAPrendre);
			
			if (indexOfMotAPrendre >= 0 && !motAPrendre.equalsIgnoreCase(mot)) {
				int indexOfOtherPart = indexOfMotAPrendre + motAPrendre.length();
				String otherPart = mot.substring(indexOfOtherPart);
				boolean otherPartInDico = dico.contient(otherPart);
				
				if (otherPart.length() == 1 || otherPartInDico) {
					j.retirerMotValide(mot);
					System.out.println(j.getNom() + " vous avez pris le mot " + mot + " à votre adversaire.");
				}
			}
		}
	}

}
