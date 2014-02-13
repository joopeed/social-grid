package model;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class UsuariosTest {
	Usuario usuarioA, usuarioB, usuarioC;
	CadastroUsuario cadastro;

	@Before
	public void setUp() {
		cadastro = new CadastroUsuario();
		
		usuarioA = new Usuario("Foo", "foo@gmail.com", "123456");
		usuarioB = new Usuario("Boo", "boo@gmail.com", "654321");
		usuarioC = new Usuario("FOo", "foo@gmail.com", "159753");
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
		assertTrue(cadastro.cadastrarUsuario(usuarioA.getNome(), usuarioA.getEmail(), usuarioA.getSenha()));
		assertNotNull(cadastro.getUsuarioPorEmail(usuarioA.getEmail()));
		
		assertEquals(usuarioA, cadastro.getUsuarioPorEmail(usuarioA.getEmail()));
		
		assertFalse(cadastro.cadastrarUsuario(usuarioA.getNome(), usuarioA.getEmail(), usuarioA.getSenha()));
		assertFalse(cadastro.cadastrarUsuario(usuarioB.getNome(), usuarioA.getEmail(), usuarioB.getSenha()));
		
		assertTrue(cadastro.cadastrarUsuario(usuarioB.getNome(), usuarioB.getEmail(), usuarioB.getSenha()));
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
