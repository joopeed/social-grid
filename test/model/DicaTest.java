package model;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class DicaTest {

	private Disciplina disciplina;
	
	@Before
	public void SetUp() {
		disciplina = new Disciplina("d1", 4);
	}
	
	@Test
	public void AdicionaDicasEmDisciplina() {
		Usuario usuarioA = new Usuario("Foo", "foo@gmail.com", "123456", new Plano());
		Usuario usuarioB = new Usuario("Boo", "boo@gmail.com", "654321", new Plano());
		Usuario usuarioC = new Usuario("FOo", "foo@gmail.com", "159753", new Plano());

		Dica dica1 = new Dica(usuarioA, "Trancar!");
		Dica dica2 = new Dica(usuarioB, "Estudar pelo livro.");
		Dica dica3 = new Dica(usuarioC, "Tirar dúvidas pela lista.");
		
		List<Dica> dicas = new ArrayList<Dica>(); 
				
		assertTrue(dicas.equals(disciplina.getDicas()));
		
		disciplina.addDica(dica2);
		disciplina.addDica(dica3);
		disciplina.addDica(dica1);
		
		dicas.add(dica2);
		dicas.add(dica3);
		dicas.add(dica1);
		
		assertTrue(dicas.equals(disciplina.getDicas()));
	}
	
	@Test
	public void AdicionaERemoveLikeEmDica(){
		Usuario usuarioA = new Usuario("Foo", "foo@gmail.com", "123456", new Plano());
		Usuario usuarioB = new Usuario("Boo", "boo@gmail.com", "654321", new Plano());
		Dica dica1 = new Dica(usuarioA, "Procurar tutoriais.");
		
		assertEquals(0, dica1.getQuantidadeDeLikes());
		
		dica1.adicionaLike(usuarioA);
		dica1.adicionaLike(usuarioB);
		
		assertEquals(2, dica1.getQuantidadeDeLikes());
		
		dica1.removeLike(usuarioA);
		
		assertEquals(1, dica1.getQuantidadeDeLikes());
		
	}

}
