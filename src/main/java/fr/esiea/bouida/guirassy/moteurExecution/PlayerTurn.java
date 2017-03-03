package fr.esiea.bouida.guirassy.moteurExecution;

import fr.esiea.bouida.guirassy.core.Player;
import fr.esiea.bouida.guirassy.repository.DictionnaryRepo;
import fr.esiea.bouida.guirassy.repository.CommonPot;
import fr.esiea.bouida.guirassy.userInterface.UserInputReader;

public class PlayerTurn {
	
	private Player joueur;
	private DictionnaryRepo dico;
	private UserInputReader inputReader;
	private CommonPot pot;
	
	public PlayerTurn(Player joueur, DictionnaryRepo dico, UserInputReader inputReader, CommonPot pot) {
		this.joueur = joueur;
		this.dico = dico;
		this.inputReader = inputReader;
		this.pot = pot;
	}
	
	/**
	 * Retourne true si le joueur à gagné la partie.
	 * 
	 * @param j
	 * @return
	 */
	public boolean demarrerTour() {
		String nomDuJoueur = joueur.getNom();
		
		// Tirage de deux lettres par le joueur
		System.out.println(nomDuJoueur + " merci de tirer deux lettres");
		char lettre1 = joueur.tirerLettre();
		char lettre2 = joueur.tirerLettre();
		
		// Ajout des deux lettres au pots communs
		this.pot.ajoutLettre(lettre1);
		this.pot.ajoutLettre(lettre2);
		
		String motComposeParJoueur = inputReader.readSingleWord(nomDuJoueur + " merci de composer un mot (Appuyer Entrer si vous n'avez pas de mot)");
		
		return false;
	}

}
