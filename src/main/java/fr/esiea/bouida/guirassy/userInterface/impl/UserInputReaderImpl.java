package fr.esiea.bouida.guirassy.userInterface.impl;

import java.util.Scanner;

import fr.esiea.bouida.guirassy.userInterface.UserInputReader;

/**
 * Implementation par defaut (singleton)
 */
public class UserInputReaderImpl implements UserInputReader {
	
	private static UserInputReaderImpl instance;

	private Scanner scanner;
	
	public static UserInputReaderImpl getInstance() {
		if (instance == null) {
			instance = new UserInputReaderImpl();
		}
		
		return instance;
	}

	private UserInputReaderImpl() {
		super();
		scanner = new Scanner(System.in);
	}

	@Override
	public String readSingleWord(String message) {
		String result = null;

		System.out.println(message);

		result = this.scanner.nextLine();
		boolean aucuneSaisie = result.trim().equals("");

		if (aucuneSaisie) {
			System.out.println("Vous ne souhaitez rien saisir, confirmez-vous ce choix ? (Appuyer sur ENTREE pour valider ou saisissez votre mot)");
			result = this.scanner.nextLine();
		}
		
		return result;
	}
}
