package model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static play.test.Helpers.fakeApplication;
import static play.test.Helpers.inMemoryDatabase;
import static play.test.Helpers.start;

import java.io.IOException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXException;

import controllers.Grade;
import play.db.ebean.Model.Finder;

public class PersistenciaTest {

	@Before
	public void setUp() throws Exception {
        start(fakeApplication(inMemoryDatabase()));
	}

	@Test
	public void testDica() {
		Finder<Integer, Dica> dicaFinder = new Finder<Integer, Dica>(Integer.class, Dica.class);
		Usuario usuarioA = new Usuario("Foo", "foo@gmail.com", "123456", new Plano());

		for (int i = 1; i <= 5; i++) {
			Dica dica = new Dica(usuarioA, "Esta é uma dica! (" + String.valueOf(i) + ")");
		
			assertEquals(i - 1, dicaFinder.findRowCount());
			assertNull(dica.id);
			
			dica.save();
			assertTrue(i == dica.id);
			assertNotNull(dica.id);
			assertEquals(i, dicaFinder.findRowCount());
			
			for (int j = 0; j < i; j++) {
				dica.adicionaLike(usuarioA);
				dica.update();
			}	
		}
		
		
		List<Dica> dicas = dicaFinder.all();
		assertEquals(5, dicas.size());
		
		for (int i = 1; i <= dicaFinder.findRowCount(); i++) {
			Dica dica = dicaFinder.byId(i);
			
			assertTrue(i == dica.id);
			assertEquals("Esta é uma dica! (" + String.valueOf(i) + ")", dica.getTexto());
			assertEquals(i, dica.getQuantidadeDeLikes());
		}
	}

	@Test
	public void testGrade() throws IOException, ParserConfigurationException, SAXException {
		Finder<Integer, Grade> gradeFinder = new Finder<Integer, Grade>(Integer.class, Grade.class);
		
		assertEquals(0, gradeFinder.findRowCount());
		new Grade();
		
		assertEquals(1, gradeFinder.findRowCount());
		
		
	}
}
