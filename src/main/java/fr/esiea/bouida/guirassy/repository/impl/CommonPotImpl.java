package fr.esiea.bouida.guirassy.repository.impl;


import java.util.ArrayList;
import java.util.List;

import fr.esiea.bouida.guirassy.repository.CommonPot;

public class CommonPotImpl implements CommonPot{
	
	/**
	 * Unique instance
	 */
	private static CommonPotImpl instance;
	
	private List<Character> pot;
	
	private CommonPotImpl() {
		super();
		this.pot = new ArrayList<>();
	}

	public static CommonPotImpl getInstance() {
		if (instance == null) {
			instance = new CommonPotImpl();
		}
		
		return instance;
	}
	
	@Override
	public void ajoutLettre(char a) {
		this.pot.add(a);
		System.out.println();
		System.out.println("Une nouvelle lettre est ajouté au pot commun. Voici les lettres du pot : ");
		System.out.println(this.pot);
	}
	
	@Override
	public boolean estComposeDeLettresDuPot(String mot) {
		// Un filtre est appliqué pour retourné toutes les lettre du pot commun
		long nbFiltrer = mot.chars().filter(l -> this.pot.contains((char)l)).count();
		return nbFiltrer == mot.length();
	}
}
