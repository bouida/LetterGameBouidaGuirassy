package fr.esiea.bouida.guirassy;

import org.junit.Assert;

import org.junit.Test;

import fr.esiea.bouida.guirassy.Begin;

public class GameStarterTest {
	
	@Test
	public void testConstructeur() {
		Begin starter = new Begin();
		Assert.assertNotNull("Aucun objet starter", starter);
	}

}
