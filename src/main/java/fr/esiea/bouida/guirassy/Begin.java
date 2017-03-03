package fr.esiea.bouida.guirassy;

import fr.esiea.bouida.guirassy.core.Player;
import fr.esiea.bouida.guirassy.core.WordTaker;
import fr.esiea.bouida.guirassy.moteurExecution.PlayerTurn;
import fr.esiea.bouida.guirassy.repository.DictionnaryRepo;
import fr.esiea.bouida.guirassy.repository.CommonPot;
import fr.esiea.bouida.guirassy.repository.impl.MemoryDictionnaryRepo;
import fr.esiea.bouida.guirassy.repository.impl.CommonPotImpl;
import fr.esiea.bouida.guirassy.userInterface.UserInputReader;
import fr.esiea.bouida.guirassy.userInterface.impl.UserInputReaderImpl;
import java.util.Scanner;

public class Begin {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		intialiserPartie();
		
	}
	
	public static void acceuil(Player j1, Player j2) {
		String prenomJoueur1 = j1.getNom();
		String prenomJoueur2 = j2.getNom();
		System.out.println("Qui remportera la parie entre " + prenomJoueur1 + " et " + prenomJoueur2 + " .");
	}
	
	public static void intialiserPartie() {
		System.out.println("********************************Bienvenue à Lettergame , un jeu pas comme les autres !!!!!!!!********************************");
		
		// Lecture du nom des joueurs
		UserInputReader reader = UserInputReaderImpl.getInstance();
		
		System.out.print("Appuyer sur la touche entrer de votre clavier\n");
		Scanner sc = new Scanner(System.in);
		
		sc.nextLine();
		
		

		
		
		String prenomJoueur1 = reader.readSingleWord("Saisir votre nom joueur numéro 1");
		String prenomJoueur2 = reader.readSingleWord("Saisir votre nom joueur numéro 2");
		
		// Creation des joueurs
		Player joueur1 = new Player(prenomJoueur1);
		Player joueur2 = new Player(prenomJoueur2);
		
		// Acceuil des joueurs
		acceuil(joueur1, joueur2);
		
		// Initialisation du dictionnaire
		DictionnaryRepo dico = MemoryDictionnaryRepo.getInstance();
		
		// Creation du preneur de mot
		new WordTaker(joueur1, joueur2, dico);
		
		// Demander aux joueurs de tirer une lettre au hasard
		Character lettreJoueur1 = joueur1.tirerLettre();
		Character lettreJoueur2 = joueur2.tirerLettre();
		
		// Ajout des lettres aux lettres tirées par les joueurs
		joueur1.ajouterLettreUtilisateur(lettreJoueur1);
		joueur2.ajouterLettreUtilisateur(lettreJoueur2);
		
		// Ajout des lettres dans le pot commun
		CommonPot pot = CommonPotImpl.getInstance();
		pot.ajoutLettre(lettreJoueur1);
		pot.ajoutLettre(lettreJoueur2);
		
		// Determination du premier à commencer
		Player joueurEnCours = trouvePremierADemarrer(joueur1, joueur2);
		boolean joueurEnCoursAGagne = false;
		
		while (!joueurEnCoursAGagne) {
			// On demarre un nouveau tour
			System.out.println("À vous " + joueurEnCours.getNom());
			PlayerTurn t = new PlayerTurn(joueurEnCours, dico, reader, pot);
			joueurEnCoursAGagne = t.demarrerTour();
			
			if (joueurEnCours == joueur1) {
				joueurEnCours = joueur2;
			} else {
				joueurEnCours = joueur1;
			}
		}
	}
	
	/**
	 * Permet de déterminer qui commence en premier
	 * @param j1
	 * @param j2
	 * @return
	 */
	public static Player trouvePremierADemarrer(Player j1, Player j2) {
		Character lettrej1 = j1.getLettreUtilisateur().get(0);
		Character lettrej2 = j2.getLettreUtilisateur().get(0);
		
		Player premier = null;
		if (lettrej1.compareTo(lettrej2) <= 0) {
			premier = j1;
		} else {
			premier = j2;
		}
		
		return premier;
	}
	
}
