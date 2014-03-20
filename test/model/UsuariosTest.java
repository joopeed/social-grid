package model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static play.test.Helpers.fakeApplication;
import static play.test.Helpers.inMemoryDatabase;
import static play.test.Helpers.start;

import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import controllers.CadastroUsuario;
import controllers.CadastroUsuarioException;
import controllers.Sistema;

public class UsuariosTest {
	private Usuario usuarioA, usuarioB, usuarioC;
	private CadastroUsuario cadastro;

	@SuppressWarnings("unused")
	private Sistema sistema; // É usado para iniciar a grade.

	@Before
	public void setUp() throws IOException {
		start(fakeApplication(inMemoryDatabase()));
		
		cadastro = new CadastroUsuario();
		
		usuarioA = new Usuario("Foo", "foo@gmail.com", "123456", new Plano());
		usuarioB = new Usuario("Boo", "boo@gmail.com", "654321", new Plano());
		usuarioC = new Usuario("FOo", "foo@gmail.com", "159753", new Plano());
	}
	
	@Test
	public void testEqualsEHashCode() {
		assertFalse(usuarioA.equals(null));
		
		assertFalse(usuarioA.equals(usuarioB));
		assertFalse(usuarioA.hashCode() ==  usuarioB.hashCode());
		
		assertFalse(usuarioB.equals(usuarioC));
		assertFalse(usuarioB.hashCode() == usuarioC.hashCode());
		
		assertTrue(usuarioA.equals(usuarioC));
		assertTrue(usuarioA.hashCode() == usuarioC.hashCode());
	}
	
	@Test
	public void testCadastroEBuscaDeUsuario() {
		assertNull(cadastro.getUsuarioPorEmail(usuarioA.getEmail()));
		
		try {
			cadastro.cadastrarUsuario(usuarioA.getNome(), usuarioA.getEmail(), usuarioA.getSenha());
		} catch (CadastroUsuarioException e) {
			fail();
		}
		
		assertNotNull(cadastro.getUsuarioPorEmail(usuarioA.getEmail()));
		
		assertEquals(usuarioA, cadastro.getUsuarioPorEmail(usuarioA.getEmail()));
		
		try {
			cadastro.cadastrarUsuario(usuarioA.getNome(), usuarioA.getEmail(), usuarioA.getSenha());
			fail();
		} catch (CadastroUsuarioException e) { }
		
		try {
			cadastro.cadastrarUsuario(usuarioB.getNome(), usuarioA.getEmail(), usuarioB.getSenha());
			fail();
		} catch (CadastroUsuarioException e) { }
		
		try {
			cadastro.cadastrarUsuario(usuarioB.getNome(), usuarioB.getEmail(), usuarioB.getSenha());
		} catch (CadastroUsuarioException e) {
			fail();
		}

		assertEquals(usuarioB, cadastro.getUsuarioPorEmail(usuarioB.getEmail()));
	}
	
	@Test
	public void testAutenticar() {
		assertTrue(usuarioA.autenticar("123456"));
		assertFalse(usuarioB.autenticar("123456"));
		assertFalse(usuarioC.autenticar("123456"));
		
		assertFalse(usuarioA.autenticar("654321"));
		assertTrue(usuarioB.autenticar("654321"));
		assertFalse(usuarioC.autenticar("654321"));
		
		assertFalse(usuarioA.autenticar("159753"));
		assertFalse(usuarioB.autenticar("159753"));
		assertTrue(usuarioC.autenticar("159753"));
	}
	
	@After
	public void tearDown() {
		usuarioA = null;
		usuarioB = null;
		usuarioC = null;
	}

}
