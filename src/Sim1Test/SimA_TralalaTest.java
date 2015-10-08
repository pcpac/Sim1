package Sim1Test;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.nio.ByteBuffer;

import org.junit.Test;

import Sim1.Clavier;
import Sim1.Sim1_Tralala;

public class SimA_TralalaTest {
	
	// Calcul Gain
	
	@Test
	public void calculGainTestPaire() {
		assertEquals(100, Sim1_Tralala.calculGain(1, 25, 8));
	}
	@Test
	public void calculGainTestSuite() {
		assertEquals(50, Sim1_Tralala.calculGain(2, 25, 8));
	}
	@Test
	public void calculGainTestCouleur() {
		assertEquals(25, Sim1_Tralala.calculGain(3, 25, 8));
	}
	@Test
	public void calculGainTestSomme() {
		assertEquals(200, Sim1_Tralala.calculGain(4, 25, 8));
	}
	
	// Chaine Couleur
	
	@Test
	public void chaineCouleurTestPique() {
		assertEquals("pique", Sim1_Tralala.chaineCouleur(51));
	}
	@Test
	public void chaineCouleurTestCoeur() {
		assertEquals("coeur", Sim1_Tralala.chaineCouleur(4));
	}
	@Test
	public void chaineCouleurTestTrefle() {
		assertEquals("trefle", Sim1_Tralala.chaineCouleur(35));
	}
	@Test
	public void chaineCouleurTestCarreau() {
		assertEquals("carreau", Sim1_Tralala.chaineCouleur(15));
	}
	
	// Chaine Sorte
	
	@Test
	public void chaineSorteTestRoi() {
		assertEquals("roi", Sim1_Tralala.chaineSorte(12));
	}
	@Test
	public void chaineSorteTestValet() {
		assertEquals("valet", Sim1_Tralala.chaineSorte(10));
	}
	@Test
	public void chaineSorteTestAs() {
		assertEquals("as", Sim1_Tralala.chaineSorte(0));
	}
	@Test
	public void chaineSorteTestDame() {
		assertEquals("dame", Sim1_Tralala.chaineSorte(11));
	}
	@Test
	public void chaineSorteTestNuméro() {
		assertEquals("8", Sim1_Tralala.chaineSorte(7));
	}

	// Est une paire
	
	@Test
	public void estUnePaireTest() {
		Boolean estPaire = Sim1_Tralala.estUnePaire(10,23);
		assertTrue(estPaire);
	}
	@Test
	public void estUnePaireTestFail() {
		Boolean estPaire = Sim1_Tralala.estUnePaire(13,38);
		assertFalse(estPaire);
	}
	
	// Est une séquence
	
	@Test
	public void estUneSequenceTest() {
		Boolean estSequence = Sim1_Tralala.estUneSequence(26,27);
		assertTrue(estSequence);
	}
	@Test
	public void estUneSequenceTest2() {
		Boolean estSequence = Sim1_Tralala.estUneSequence(25,13); // roi et as
		assertTrue(estSequence);
	}
	@Test
	public void estUneSequenceTestFail() {
		Boolean estSequence = Sim1_Tralala.estUneSequence(13,39);
		assertFalse(estSequence);
	}
	
	// La couleur
	
	@Test
	public void laCouleurTest() {
		assertEquals(2, Sim1_Tralala.laCouleur(28));
	}
	@Test
	public void laCouleurTestFail() {
		assertEquals(3, Sim1_Tralala.laCouleur(42));
	}
	
	// La sorte
	
	@Test
	public void laSorteTest() {
		assertEquals(2, Sim1_Tralala.laSorte(28));
	}
	@Test
	public void laSorteTestFail() {
		assertEquals(10, Sim1_Tralala.laSorte(23));
	}
	
	// Lire oui ou non
	
	@Test
	public void lireOuiNonTest() {
		String str = "oui\n";
		System.setIn(new ByteArrayInputStream(str.getBytes()));
		
		assertEquals('o', Sim1_Tralala.lireOuiNon());
	}
	
	@Test
	public void lireOuiNonTestDeuxiemeEssai() {
		String str = "Blabla\n";
		System.setIn(new ByteArrayInputStream(str.getBytes()));
		str = "non\n";
		System.setIn(new ByteArrayInputStream(str.getBytes()));
		assertEquals('n', Sim1_Tralala.lireOuiNon());
	}
	
	// Somme deux cartes
	
	@Test
	public void somme2CartesTest() {
		assertEquals(20, Sim1_Tralala.somme2Cartes(11, 24));
	}
	@Test
	public void somme2CartesTestDeux() {
		assertEquals(7, Sim1_Tralala.somme2Cartes(15, 29));
	}
	
	// Sont inférieur à 7
	
	public void sontInferieurA7Test() {
		assertTrue(Sim1_Tralala.sontInferieurA7(2, 13));
	}
	@Test
	public void sontInferieurA7TestFail() {
		assertFalse(Sim1_Tralala.sontInferieurA7(8, 22));
	}
	
	// Sont meme couleur
	
	public void sontMemeCouleurTest() {
		assertTrue(Sim1_Tralala.sontMemeCouleur(3, 12));
	}
	@Test
	public void sontMemeCouleurTestFail() {
		assertFalse(Sim1_Tralala.sontMemeCouleur(26, 25));
	}
	
	// Verifier victoire
	
	@Test
	public void verifierVictoireTestPaire() {
		assertTrue(Sim1_Tralala.verifierVictoire(4, 17, 1));
	}
	public void verifierVictoireTestPaireFail() {
		assertTrue(Sim1_Tralala.verifierVictoire(4, 18, 1));
	}
	public void verifierVictoireTestSequence() {
		assertTrue(Sim1_Tralala.verifierVictoire(13, 14, 2));
	}
	public void verifierVictoireTestSequenceFail() {
		assertTrue(Sim1_Tralala.verifierVictoire(8, 9, 2));
	}
	public void verifierVictoireTestCouleur() {
		assertTrue(Sim1_Tralala.verifierVictoire(28, 35, 3));
	}
	public void verifierVictoireTestCouleurFail() {
		assertTrue(Sim1_Tralala.verifierVictoire(13, 14, 3));
	}
	public void verifierVictoireTestInferieur7() {
		assertTrue(Sim1_Tralala.verifierVictoire(14, 29, 4));
	}
	public void verifierVictoireTestInferieur7Fail() {
		assertTrue(Sim1_Tralala.verifierVictoire(28, 32, 4));
	}
		
}
