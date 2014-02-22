package model;

import static play.test.Helpers.*;
import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class UsuariosTest {
	Usuario usuarioA, usuarioB, usuarioC;
	Grade grade;
	CadastroUsuario cadastro;

	@Before
	public void setUp() throws IOException {
		start(fakeApplication(inMemoryDatabase()));
		
		grade = new Grade();
		cadastro = new CadastroUsuario();
		
		usuarioA = new Usuario("Foo", "foo@gmail.com", "123456", new Plano(grade));
		usuarioB = new Usuario("Boo", "boo@gmail.com", "654321", new Plano(grade));
		usuarioC = new Usuario("FOo", "foo@gmail.com", "159753", new Plano(grade));
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
			cadastro.cadastrarUsuario(usuarioA.getNome(), usuarioA.getEmail(), usuarioA.getSenha(), new Plano(grade));
		} catch (CadastroUsuarioException e) {
			fail();
		}
		
		assertNotNull(cadastro.getUsuarioPorEmail(usuarioA.getEmail()));
		
		assertEquals(usuarioA, cadastro.getUsuarioPorEmail(usuarioA.getEmail()));
		
		try {
			cadastro.cadastrarUsuario(usuarioA.getNome(), usuarioA.getEmail(), usuarioA.getSenha(), new Plano(grade));
			fail();
		} catch (CadastroUsuarioException e) { }
		
		try {
			cadastro.cadastrarUsuario(usuarioB.getNome(), usuarioA.getEmail(), usuarioB.getSenha(), new Plano(grade));
			fail();
		} catch (CadastroUsuarioException e) { }
		
		try {
			cadastro.cadastrarUsuario(usuarioB.getNome(), usuarioB.getEmail(), usuarioB.getSenha(), new Plano(grade));
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
