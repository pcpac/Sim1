package Sim1Test;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Test;

import Sim1.Clavier;

public class ClavierTest {

	
	@Test
	public void lireStringTest()  {
		String str = "Test\n";
		System.setIn(new ByteArrayInputStream(str.getBytes()));
		
		
		assertEquals("Test", Clavier.lireString());
	}
	
	@Test
	public void lireStringTestAccents() {
		String str = "Deuxième test\n";
		System.setIn(new ByteArrayInputStream(str.getBytes()));
		
		assertEquals("Deuxième test", Clavier.lireString());
	}
	
	@Test
	public void lireStringTestTresLong() {
		String str = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla elit justo, viverra id justo sed, rhoncus tempor velit. Morbi in neque nec sapien interdum lobortis. Nullam venenatis purus non eros vestibulum lacinia. Sed ornare, mauris sed vestibulum commodo, nibh libero convallis quam, eget venenatis mi diam auctor mi. Cras ac ornare turpis. Praesent sed nunc et orci eleifend imperdiet sed ut est. Pellentesque non turpis molestie, viverra purus ullamcorper, maximus purus. Praesent at egestas massa, sed iaculis dui. Pellentesque ac ipsum eu ante accumsan consectetur sed eget sem. Interdum et malesuada fames ac ante ipsum primis in faucibus. Pellentesque viverra quam nulla, at fringilla lorem iaculis vitae. Sed eu ante mi. Maecenas non ipsum a nisl sodales pellentesque. Etiam gravida massa ut risus placerat interdum sit amet in libero. Etiam nisl justo, lobortis eu vehicula vel, varius vel sem.  Nullam elementum a est eget condimentum. Donec gravida mauris sit amet ligula euismod bibendum. Fusce a ex ipsum. Sed varius neque vel sem tristique, in porta leo volutpat. Integer pharetra, sem vitae elementum tempor, lectus augue pretium nibh, ut condimentum ipsum elit elementum diam. Fusce efficitur metus elit, vitae consectetur ante dictum quis. Mauris blandit ut felis quis pellentesque. Sed eu massa nisl. Proin pellentesque, urna sed varius viverra, sapien nulla aliquet dui, ac cursus odio dolor at quam. Morbi posuere faucibus tincidunt. Ut sit amet risus sed turpis volutpat congue. Sed at turpis sagittis, dignissim leo vel, vehicula lorem. Fusce hendrerit congue mauris, at consequat ex suscipit ut. Ut eu porta lectus. Fusce ut eros in dolor tristique congue at ac velit. Maecenas scelerisque turpis rutrum, feugiat mauris sit amet, viverra lacus. Nulla facilisi. Praesent fringilla, mauris varius lacinia vestibulum, lacus erat hendrerit odio, id finibus nisi odio quis justo. Suspendisse dictum viverra est quis ultrices. Quisque efficitur arcu ac pretium egestas. Maecenas mollis mauris sed egestas fermentum. Sed nec laoreet magna. Vestibulum est neque, interdum eu ornare nec, faucibus ac quam. Sed in neque lobortis, viverra mauris eu, viverra urna. Sed eu pharetra turpis, eu pretium quam. Nulla placerat augue ac nisi posuere, ut porta purus laoreet. Curabitur quis tristique ante. Maecenas accumsan et turpis ac consectetur. Donec dapibus in magna nec efficitur. Morbi sit amet posuere dui, a fringilla nisi. Nullam pellentesque accumsan sapien in pellentesque. Mauris ac dapibus nulla, eu ornare leo. Duis vulputate augue ligula, eu gravida augue facilisis quis.\n";
		System.setIn(new ByteArrayInputStream(str.getBytes()));
		
		assertNotEquals(str.substring(0, str.length()) , Clavier.lireString());
	}
	
	@Test
	public void lireStringTestFail() {
		String str = "Troisième test\n";
		System.setIn(new ByteArrayInputStream(str.getBytes()));
		
		assertNotEquals("Coucou", Clavier.lireString());
	}
	
	@Test 
	public void lireCharTest() {
		String carac = "B";
		System.setIn(new ByteArrayInputStream(carac.getBytes()));
		
		assertEquals('B', Clavier.lireChar());
		
	}
	
	@Test 
	public void lireCharFail() {
		String carac = "G";
		System.setIn(new ByteArrayInputStream(carac.getBytes()));
		
		assertEquals('B', Clavier.lireChar());
		
	}
	
	@Test
	public void viderTamponTest() throws IOException {
		String str = "Un texte";
		System.setIn(new ByteArrayInputStream(str.getBytes()));
		
		Clavier.viderTampon();
		assertEquals(0, System.in.available());
	}
	
}
